package com.aurora.backend.config;

import com.aurora.backend.service.RagService;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RagConfig {
    @Value("${langchain4j.google-ai-gemini.embedding-model.output-dimensionality:768}")
    private int outputDimension;

    @Bean
    public RagService geminiRagService(ContentRetriever contentRetriever, GoogleAiGeminiChatModel googleAiGeminiChatModel) {
        return AiServices.builder(RagService.class)
                .chatModel(googleAiGeminiChatModel)
                .contentRetriever(contentRetriever)
                .build();
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore(DataSourceProperties dataSourceProperties) {
        String jdbcUrl = dataSourceProperties.getUrl();

        if (jdbcUrl == null || !jdbcUrl.startsWith("jdbc:postgresql://")) {
            log.warn("RAG fallback: unsupported datasource URL '{}'. Using in-memory embedding store.", jdbcUrl);
            return new InMemoryEmbeddingStore<>();
        }

        try {
            String cleanUrl = jdbcUrl.replace("jdbc:postgresql://", "").split("\\?")[0];
            String[] parts = cleanUrl.split("/");
            String[] hostPort = parts[0].split(":");

            if (parts.length < 2 || hostPort.length == 0 || hostPort[0].isBlank()) {
                throw new IllegalArgumentException("Invalid PostgreSQL JDBC URL: " + jdbcUrl);
            }

            String host = hostPort[0];
            int port = hostPort.length > 1 ? Integer.parseInt(hostPort[1]) : 5432;
            String database = parts[1];

            EmbeddingStore<TextSegment> pgVectorStore = PgVectorEmbeddingStore
                    .builder()
                    .host(host)
                    .port(port)
                    .database(database)
                    .user(dataSourceProperties.getUsername())
                    .password(dataSourceProperties.getPassword())
                    .table("langchain_store")
                    .dropTableFirst(false)
                    .createTable(true)
                    .dimension(outputDimension)
                    .build();

            log.info("RAG initialized with PgVectorEmbeddingStore on {}:{}/{}", host, port, database);
            return pgVectorStore;
        } catch (Exception e) {
            log.warn("RAG fallback: pgvector initialization failed, using in-memory embedding store. Cause: {}", e.getMessage());
            log.debug("RAG fallback details", e);
            return new InMemoryEmbeddingStore<>();
        }
    }

    @Bean
    public ContentRetriever contentRetriever(EmbeddingStore<TextSegment> embeddingStore, EmbeddingModel embeddingModel) {
        return EmbeddingStoreContentRetriever
                .builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .maxResults(5)
                .minScore(0.7)
                .build();
    }

    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(10)
                .build();
    }
}
