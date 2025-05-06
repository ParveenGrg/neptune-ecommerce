package com.neptune.neptune.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class VerificationToken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private LocalDateTime expiryDate;

    private boolean verified = false;

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getToken() {
        return this.token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public LocalDateTime getExpiryDate() {
        return this.expiryDate;
    }
    
    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public boolean getVerified() {
        return this.verified;
    }
    
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public VerificationToken(String token, User user, LocalDateTime expiryDate, boolean verified) {
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
        this.verified = verified;
    }

    @Override
public String toString() {
    return "VerificationToken{" +
            "id=" + id +
            ", token='" + token + '\'' +
            ", user=" + (user != null ? user.getId() : null) +
            ", expiryDate=" + expiryDate +
            ", verified=" + verified +
            '}';
}
}

