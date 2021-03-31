package motoworld.project.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "support_messages")
public class SupportMessages extends BaseEntity{

    private String supportRequest;
    private String email;
    private LocalDateTime date = LocalDateTime.now();


    public SupportMessages() {
    }


    @Column(name = "support_request", columnDefinition = "TEXT",nullable = false)
    public String getSupportRequest() {
        return supportRequest;
    }

    public SupportMessages setSupportRequest(String supportRequest) {
        this.supportRequest = supportRequest;
        return this;
    }


    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public SupportMessages setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public SupportMessages setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }
}
