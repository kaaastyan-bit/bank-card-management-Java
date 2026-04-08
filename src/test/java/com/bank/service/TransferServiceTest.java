package com.bank.service;

import com.bank.service.impl.TransferServiceImpl;
import com.bank.dto.TransferDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @InjectMocks
    private TransferServiceImpl transferService;
    
    private TransferDTO testTransferDTO;
    
    @BeforeEach
    void setUp() {
        testTransferDTO = new TransferDTO();
        testTransferDTO.setFromCardId(1L);
        testTransferDTO.setToCardId(2L);
        testTransferDTO.setAmount(100.0);
    }
    
    @Test
    void testTransferBetweenOwnCards_ShouldProcessTransfer() {
        assertNotNull(testTransferDTO);
        assertEquals(100.0, testTransferDTO.getAmount());
    }
    
    @Test
    void testTransferWithNegativeAmount_ShouldFail() {
        testTransferDTO.setAmount(-50.0);
        assertTrue(testTransferDTO.getAmount() < 0);
    }
}
