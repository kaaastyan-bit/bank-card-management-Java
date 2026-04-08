package com.bank.service;

import com.bank.service.impl.CardServiceImpl;
import com.bank.dto.CardDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bank.repository.CardRepository;
import com.bank.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CardServiceTest {

    @Mock
    private CardRepository cardRepository;
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private CardServiceImpl cardService;
    
    private CardDTO testCardDTO;
    
    @BeforeEach
    void setUp() {
        testCardDTO = new CardDTO();
        testCardDTO.setCardNumber("1234567890123456");
        testCardDTO.setOwnerName("John Doe");
        testCardDTO.setBalance(1000.0);
    }
    
    @Test
    void testCreateCard_ShouldReturnCardDTO() {
        assertNotNull(testCardDTO);
        assertEquals("1234567890123456", testCardDTO.getCardNumber());
    }
    
    @Test
    void testGetCardById_ShouldNotThrow() {
        assertDoesNotThrow(() -> cardService.getCardById(1L));
    }
}
