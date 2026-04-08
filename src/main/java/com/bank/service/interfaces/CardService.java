/**
 * Service interface for managing bank cards.
 * Provides methods for CRUD operations, blocking, activating and viewing cards.
 * 
 * @author Bank System
 * @version 1.0
 */
package com.bank.service.interfaces;

import com.bank.dto.CardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

public interface CardService {
    CardResponse createCard(String cardHolderName, LocalDate expiryDate, Double balance, Long ownerId);
    CardResponse blockCard(Long cardId);
    CardResponse activateCard(Long cardId);
    void deleteCard(Long cardId);
    Page<CardResponse> getAllCards(Pageable pageable);
    CardResponse getCardById(Long cardId);
    Page<CardResponse> getUserCards(Long userId, Pageable pageable);
}

