package com.aurora.backend.service.impl;

import com.aurora.backend.entity.Booking;
import com.aurora.backend.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.mail.from-email:${SMTP_USERNAME:noreply@aurorahotel.com}}")
    private String fromEmail;

    @Value("${app.mail.from-name:Aurora Hotel System}")
    private String fromName;
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    public void sendBookingConfirmation(Booking booking) {
        try {
            log.info("=== EMAIL SERVICE: Starting email send for booking: {}", booking.getBookingCode());
            
            // Get customer email
            String customerEmail = booking.getCustomer() != null 
                ? booking.getCustomer().getEmail() 
                : booking.getGuestEmail();
            
            String customerName = booking.getCustomer() != null
                ? (booking.getCustomer().getFirstName() != null && booking.getCustomer().getLastName() != null 
                    ? booking.getCustomer().getFirstName() + " " + booking.getCustomer().getLastName()
                    : booking.getCustomer().getUsername())
                : booking.getGuestFullName();
            
            log.info("=== EMAIL SERVICE: Customer email: {}, Customer name: {}", customerEmail, customerName);
                
            if (customerEmail == null || customerEmail.isBlank()) {
                log.warn("=== EMAIL SERVICE: No email address found for booking: {}", booking.getBookingCode());
                return;
            }

            log.info("=== EMAIL SERVICE: Generating HTML content...");
            // Generate HTML content using Thymeleaf
            String htmlContent = generateBookingConfirmationHtml(booking);
            log.info("=== EMAIL SERVICE: HTML content generated, length: {}", htmlContent.length());
            
            // Load banner image to embed inline in email
            log.info("=== EMAIL SERVICE: Loading banner image...");
            ClassPathResource bannerImage = loadBannerImage();

            log.info("=== EMAIL SERVICE: Building SMTP email message...");
            log.info("=== EMAIL SERVICE: From: {} <{}>, To: {} <{}>", 
                fromName, fromEmail, customerName, customerEmail);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                message,
                true,
                StandardCharsets.UTF_8.name()
            );

            helper.setFrom(fromEmail, fromName);
            helper.setTo(customerEmail);
            helper.setSubject("Xác nhận đặt phòng - " + booking.getBookingCode());
            helper.setText(htmlContent, true);

            if (bannerImage != null && bannerImage.exists()) {
                helper.addInline("aurora-banner", bannerImage, "image/jpeg");
            }

            log.info("=== EMAIL SERVICE: Sending email via Gmail SMTP...");
            mailSender.send(message);
            log.info("=== EMAIL SERVICE: Email sent successfully to: {} for booking: {}",
                customerEmail, booking.getBookingCode());
            booking.setEmailSent(true);
            
        } catch (Exception e) {
            log.error("=== EMAIL SERVICE: Exception while sending email for booking: {}", 
                booking.getBookingCode(), e);
        }
    }
    
    private ClassPathResource loadBannerImage() {
        ClassPathResource imageResource = new ClassPathResource("images/aurora-banner.jpg");
        if (!imageResource.exists()) {
            log.warn("Banner image not found, email will be sent without banner");
            return null;
        }
        return imageResource;
    }

    private String generateBookingConfirmationHtml(Booking booking) {
        // Create Thymeleaf template engine
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/email/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);

        TemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        // Prepare template context
        Context context = new Context();
        context.setVariable("booking", booking);
        context.setVariable("bookingCode", booking.getBookingCode());
        
        // Customer info
        String customerName = booking.getCustomer() != null 
            ? (booking.getCustomer().getFirstName() != null && booking.getCustomer().getLastName() != null 
                ? booking.getCustomer().getFirstName() + " " + booking.getCustomer().getLastName()
                : booking.getCustomer().getUsername())
            : booking.getGuestFullName();
        String customerEmail = booking.getCustomer() != null 
            ? booking.getCustomer().getEmail() 
            : booking.getGuestEmail();
        String customerPhone = booking.getCustomer() != null 
            ? booking.getCustomer().getPhone() 
            : booking.getGuestPhone();
            
        context.setVariable("customerName", customerName);
        context.setVariable("customerEmail", customerEmail);
        context.setVariable("customerPhone", customerPhone);
        
        // Dates
        context.setVariable("checkinDate", booking.getCheckin().format(DATE_FORMATTER));
        context.setVariable("checkoutDate", booking.getCheckout().format(DATE_FORMATTER));
        context.setVariable("bookingDate", booking.getCreatedAt().format(DATETIME_FORMATTER));
        
        // Branch info
        context.setVariable("branchName", booking.getBranch().getName());
        context.setVariable("branchAddress", booking.getBranch().getAddress());
        context.setVariable("branchPhone", booking.getBranch().getPhone());
        context.setVariable("branchEmail", booking.getBranch().getEmail());
        
        // Room details
        List<Map<String, Object>> roomDetails = booking.getRooms().stream()
            .map(bookingRoom -> {
                Map<String, Object> room = new HashMap<>();
                room.put("name", bookingRoom.getRoom().getRoomType().getName());
                room.put("number", bookingRoom.getRoom().getRoomNumber());
                room.put("price", formatCurrency(bookingRoom.getPricePerNight()));
                room.put("nights", bookingRoom.getNights());
                room.put("total", formatCurrency(
                    bookingRoom.getPricePerNight().multiply(
                        BigDecimal.valueOf(bookingRoom.getNights()))));
                return room;
            })
            .collect(Collectors.toList());
        context.setVariable("rooms", roomDetails);
        
        // Pricing
        context.setVariable("subtotal", formatCurrency(booking.getSubtotalPrice()));
        context.setVariable("discount", formatCurrency(
            booking.getDiscountAmount() != null ? booking.getDiscountAmount() : BigDecimal.ZERO));
        context.setVariable("total", formatCurrency(booking.getTotalPrice()));
        
        // Payment info
        context.setVariable("paymentStatus", getPaymentStatusText(booking.getPaymentStatus()));
        context.setVariable("bookingStatus", getBookingStatusText(booking.getStatus()));
        
        // Special request
        context.setVariable("specialRequest", 
            booking.getSpecialRequest() != null ? booking.getSpecialRequest() : "Không có");

        // Process template
        return templateEngine.process("booking-confirmation", context);
    }

    private String formatCurrency(BigDecimal amount) {
        return String.format("%,d VNĐ", amount.longValue());
    }

    private String getPaymentStatusText(Booking.PaymentStatus status) {
        return switch (status) {
            case PENDING -> "Chờ thanh toán";
            case DEPOSIT_PAID -> "Đã đặt cọc";
            case PARTIALLY_PAID -> "Thanh toán một phần";
            case PAID -> "Đã thanh toán";
            case REFUNDED -> "Đã hoàn tiền";
        };
    }

    private String getBookingStatusText(Booking.BookingStatus status) {
        return switch (status) {
            case PENDING -> "Chờ xác nhận";
            case CONFIRMED -> "Đã xác nhận";
            case CHECKED_IN -> "Đã check-in";
            case CHECKED_OUT -> "Đã check-out";
            case COMPLETED -> "Hoàn thành";
            case CANCELLED -> "Đã hủy";
            case NO_SHOW -> "Không đến";
        };
    }
}
