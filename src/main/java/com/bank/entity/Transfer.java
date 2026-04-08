package com.bank.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "from_card_id", nullable = false)
    private Card fromCard;
    
    @ManyToOne
    @JoinColumn(name = "to_card_id", nullable = false)
    private Card toCard;
    
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;
    
    @Column(nullable = false)
    private LocalDateTime transferDate;
    
    private String description;
    
    @PrePersist
    protected void onCreate() {
        transferDate = LocalDateTime.now();
    }
}