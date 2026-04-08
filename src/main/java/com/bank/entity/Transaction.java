package com.bank.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "from_card_id")
    private Card fromCard;
    
    @ManyToOne
    @JoinColumn(name = "to_card_id")
    private Card toCard;
    
    private Double amount;
    private LocalDateTime transactionDate;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Card getFromCard() { return fromCard; }
    public void setFromCard(Card fromCard) { this.fromCard = fromCard; }
    public Card getToCard() { return toCard; }
    public void setToCard(Card toCard) { this.toCard = toCard; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
