package com.bank.controller;

import com.bank.dto.CardResponse;
import com.bank.dto.TransferDTO;
import com.bank.dto.TransactionDTO;
import com.bank.service.interfaces.CardService;
import com.bank.service.interfaces.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    private CardService cardService;
    
    @Autowired
    private TransferService transferService;
    
    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return email.equals("user@bank.com") ? 2L : 1L;
    }
    
    @GetMapping("/cards")
    public Page<CardResponse> getMyCards(@PageableDefault(size = 10) Pageable pageable) {
        Long userId = getCurrentUserId();
        return cardService.getUserCards(userId, pageable);
    }
    
    @GetMapping("/cards/{cardId}/balance")
    public Double getCardBalance(@PathVariable Long cardId) {
        CardResponse card = cardService.getCardById(cardId);
        return card != null ? card.getBalance() : null;
    }
    
    @PostMapping("/cards/{cardId}/block")
    public CardResponse blockMyCard(@PathVariable Long cardId) {
        return cardService.blockCard(cardId);
    }
    
    @PostMapping("/transfers/internal")
    public TransactionDTO transferBetweenMyCards(@RequestBody TransferDTO transferDTO) {
        return transferService.transferBetweenOwnCards(transferDTO);
    }
}