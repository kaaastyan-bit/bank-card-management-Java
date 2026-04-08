package com.bank.controller;

import com.bank.dto.CardResponse;
import com.bank.dto.CardRequest;
import com.bank.service.interfaces.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    
    private final CardService cardService;
    
    @PostMapping("/cards")
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponse createCard(@Valid @RequestBody CardRequest request) {
        return cardService.createCard(
            request.getCardHolderName(),
            request.getExpiryDate(),
            request.getBalance(),
            request.getOwnerId()
        );
    }
    
    @PutMapping("/cards/{cardId}/block")
    public CardResponse blockCard(@PathVariable Long cardId) {
        return cardService.blockCard(cardId);
    }
    
    @PutMapping("/cards/{cardId}/activate")
    public CardResponse activateCard(@PathVariable Long cardId) {
        return cardService.activateCard(cardId);
    }
    
    @DeleteMapping("/cards/{cardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCard(@PathVariable Long cardId) {
        cardService.deleteCard(cardId);
    }
    
    @GetMapping("/cards")
    public Page<CardResponse> getAllCards(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return cardService.getAllCards(pageable);
    }
    
    @GetMapping("/cards/{cardId}")
    public CardResponse getCardById(@PathVariable Long cardId) {
        return cardService.getCardById(cardId);
    }
}
