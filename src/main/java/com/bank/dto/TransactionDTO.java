package com.bank.dto;

import java.time.LocalDateTime;

public class TransactionDTO {
    private Long id;
    private Long fromCardId;
    private Long toCardId;
    private Double amount;
    private LocalDateTime transactionDate;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getFromCardId() { return fromCardId; }
    public void setFromCardId(Long fromCardId) { this.fromCardId = fromCardId; }
    public Long getToCardId() { return toCardId; }
    public void setToCardId(Long toCardId) { this.toCardId = toCardId; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
