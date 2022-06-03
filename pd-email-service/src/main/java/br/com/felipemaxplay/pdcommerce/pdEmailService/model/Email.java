package br.com.felipemaxplay.pdcommerce.pdEmailService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Objects;

@Document("emails")
public class Email {
    @Id
    private String id;
    private Long orderId;
    private String emailTo;
    private String subject;
    private String content;
    private LocalDateTime sendDateEmail;
    private EmailStatus emailStatus;

    @Deprecated
    public Email() {

    }

    public Email(@NonNull Long orderId, @NonNull String emailTo, @NonNull String subject,
                 @NonNull String content, @NonNull EmailStatus emailStatus) {
        this.orderId = Objects.requireNonNull(orderId);
        this.emailTo = Objects.requireNonNull(emailTo);
        this.subject = Objects.requireNonNull(subject);
        this.content = Objects.requireNonNull(content);
        this.emailStatus = Objects.requireNonNull(emailStatus);
        this.sendDateEmail = LocalDateTime.now();

    }

    public String getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getSendDateEmail() {
        return sendDateEmail;
    }

    public EmailStatus getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(EmailStatus emailStatus) {
        this.emailStatus = emailStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(id, email.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Email{" +
                "id='" + id + '\'' +
                ", orderId=" + orderId +
                ", emailTo='" + emailTo + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", sendDateEmail=" + sendDateEmail +
                ", emailStatus=" + emailStatus +
                '}';
    }
}