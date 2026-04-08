package com.bank.service.impl;

import com.bank.dto.CardResponse;
import com.bank.entity.Card;
import com.bank.entity.User;
import com.bank.repository.CardRepository;
import com.bank.repository.UserRepository;
import com.bank.service.interfaces.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CardServiceImpl implements CardService {
    
    @Autowired
    private CardRepository cardRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public CardResponse createCard(String cardHolderName, LocalDate expiryDate, Double balance, Long ownerId) {
        Card card = new Card();
        card.setCardNumber(generateCardNumber());
        card.setOwnerName(cardHolderName);
        card.setExpiryDate(expiryDate);
        card.setBalance(balance);
        card.setStatus("ACTIVE");
        
        User user = userRepository.findById(ownerId).orElse(null);
        card.setUser(user);
        
        card = cardRepository.save(card);
        return convertToResponse(card);
    }
    
    @Override
    public CardResponse blockCard(Long cardId) {
        Card card = cardRepository.findById(cardId).orElse(null);
        if (card != null) {
            card.setStatus("BLOCKED");
            card = cardRepository.save(card);
        }
        return convertToResponse(card);
    }
    
    @Override
    public CardResponse activateCard(Long cardId) {
        Card card = cardRepository.findById(cardId).orElse(null);
        if (card != null) {
            card.setStatus("ACTIVE");
            card = cardRepository.save(card);
        }
        return convertToResponse(card);
    }
    
    @Override
    public void deleteCard(Long cardId) {
        cardRepository.deleteById(cardId);
    }
    
    @Override
public Page<CardResponse> getAllCards(Pageable pageable) {
    try {
        Page<Card> cards = cardRepository.findAll(pageable);
        return cards.map(this::convertToResponse);
    } catch (Exception e) {
        e.printStackTrace();
        return Page.empty(pageable);
    }
}
    
    @Override
    public CardResponse getCardById(Long cardId) {
        return convertToResponse(cardRepository.findById(cardId).orElse(null));
    }
    
   @Override
public Page<CardResponse> getUserCards(Long userId, Pageable pageable) {
    System.out.println("=== START getUserCards ===");
    System.out.println("=== userId: " + userId);
    try {
        Page<Card> cards = cardRepository.findByUserId(userId, pageable);
        System.out.println("=== Cards found: " + cards.getTotalElements());
        return cards.map(this::convertToResponse);
    } catch (Exception e) {
        System.out.println("=== EXCEPTION: " + e.getClass().getName());
        System.out.println("=== MESSAGE: " + e.getMessage());
        e.printStackTrace();
        throw e;
    }
}
    
    private String generateCardNumber() {
        return String.format("%04d%04d%04d%04d", 
            (int)(Math.random() * 10000),
            (int)(Math.random() * 10000),
            (int)(Math.random() * 10000),
            (int)(Math.random() * 10000));
    }
    
    private CardResponse convertToResponse(Card card) {
    if (card == null) return null;
    CardResponse response = new CardResponse();
    response.setId(card.getId());
    String num = card.getCardNumber();
    if (num != null && num.length() >= 4) {
        response.setMaskedCardNumber("**** **** **** " + num.substring(num.length() - 4));
    } else {
        response.setMaskedCardNumber("**** **** **** ****");
    }
    response.setCardHolderName(card.getOwnerName());
    response.setStatus(card.getStatus());
    response.setBalance(card.getBalance());
    response.setCreatedAt(java.time.LocalDateTime.now());
    return response;
}
}
