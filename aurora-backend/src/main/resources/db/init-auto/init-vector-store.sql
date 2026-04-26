-- Vector store setup is handled programmatically in RagConfig with automatic
-- fallback to in-memory embedding store when pgvector is unavailable.
-- Keep this SQL script non-failing for managed PostgreSQL environments.
SELECT 1;