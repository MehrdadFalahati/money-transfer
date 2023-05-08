package com.github.mehrdad.falahati.money.transfer.domain;

import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.CreateTransactionCommand;
import com.github.mehrdad.falahati.money.transfer.domain.dto.transaction.CreateTransactionResponse;
import com.github.mehrdad.falahati.money.transfer.domain.entity.Account;
import com.github.mehrdad.falahati.money.transfer.domain.entity.TransactionHistory;
import com.github.mehrdad.falahati.money.transfer.domain.exception.TransactionDomainException;
import com.github.mehrdad.falahati.money.transfer.domain.mapper.TransactionHistoryDataMapper;
import com.github.mehrdad.falahati.money.transfer.domain.port.input.service.TransactionHistoryApplicationService;
import com.github.mehrdad.falahati.money.transfer.domain.port.output.repository.AccountRepository;
import com.github.mehrdad.falahati.money.transfer.domain.port.output.repository.TransactionHistoryRepository;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.AccountId;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.Money;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.TransactionHistoryId;
import com.github.mehrdad.falahati.money.transfer.domain.valueobject.TransactionStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = TestConfiguration.class)
public class TransactionHistoryApplicationServiceTest {

    @Autowired
    private TransactionHistoryApplicationService transactionHistoryApplicationService;
    @Autowired
    private TransactionHistoryDataMapper transactionHistoryDataMapper;
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionHistoryDomainService transactionHistoryDomainService;

    private final UUID TRANSACTION_HISTORY_ID = UUID.fromString("fe69b111-85ab-47f1-b8c2-51615619d455");
    private final UUID ACCOUNT_ID_ONE = UUID.fromString("d3ac41ae-889f-4cb4-9168-0a23df087f5e");
    private final String ACCOUNT_NUMBER_ONE = "123456789";
    private final UUID ACCOUNT_ID_TWO = UUID.fromString("fe69b111-85ab-47f1-b8c2-51615619d422");
    private final String ACCOUNT_NUMBER_TWO = "987654321";
    private final UUID ACCOUNT_ID_THREE = UUID.fromString("fe69b111-85ab-47f1-b8c2-51615619d444");
    private final String ACCOUNT_NUMBER_THREE = "741852963";
    private final UUID ACCOUNT_ID_FOUR = UUID.fromString("fe69b111-85ab-47f1-b8c2-51615619d433");
    private final String ACCOUNT_NUMBER_FOUR = "963852741";

    private CreateTransactionCommand createTransactionCommand;
    private CreateTransactionCommand createTransactionCommandWrongAccountMoney;
    private CreateTransactionCommand createTransactionCommandWrongAccountZeroMoney;
    @BeforeAll
    public void init() {
        Account accountOne = Account.builder()
                .id(new AccountId(ACCOUNT_ID_ONE))
                .currentBalance(new Money(new BigDecimal("1000.00")))
                .accountNumber(ACCOUNT_NUMBER_ONE)
                .build();

        Account accountTwo = Account.builder()
                .id(new AccountId(ACCOUNT_ID_TWO))
                .currentBalance(new Money(new BigDecimal("1000.00")))
                .accountNumber(ACCOUNT_NUMBER_TWO)
                .build();
        Account accountThree = Account.builder()
                .id(new AccountId(ACCOUNT_ID_THREE))
                .currentBalance(new Money(new BigDecimal("100.00")))
                .accountNumber(ACCOUNT_NUMBER_THREE)
                .build();
        Account accountFour = Account.builder()
                .id(new AccountId(ACCOUNT_ID_FOUR))
                .currentBalance(new Money(new BigDecimal("200.00")))
                .accountNumber(ACCOUNT_NUMBER_FOUR)
                .build();

        createTransactionCommand = CreateTransactionCommand.builder()
                .amount(new BigDecimal("100.00"))
                .fromAccountNumber(ACCOUNT_NUMBER_ONE)
                .toAccountNumber(ACCOUNT_NUMBER_TWO)
                .build();

        createTransactionCommandWrongAccountMoney = CreateTransactionCommand.builder()
                .amount(new BigDecimal("1000.00"))
                .fromAccountNumber(ACCOUNT_NUMBER_THREE)
                .toAccountNumber(ACCOUNT_NUMBER_TWO)
                .build();

        createTransactionCommandWrongAccountZeroMoney = CreateTransactionCommand.builder()
                .amount(new BigDecimal("0.00"))
                .fromAccountNumber(ACCOUNT_NUMBER_FOUR)
                .toAccountNumber(ACCOUNT_NUMBER_TWO)
                .build();
        TransactionHistory transactionHistory = transactionHistoryDataMapper.createTransactionToTransactionHistory(createTransactionCommand, accountOne, accountTwo);
        transactionHistory.setId(new TransactionHistoryId(TRANSACTION_HISTORY_ID));

        when(accountRepository.findByAccountNumber(ACCOUNT_NUMBER_ONE)).thenReturn(Optional.of(accountOne));
        when(accountRepository.findByAccountNumber(ACCOUNT_NUMBER_TWO)).thenReturn(Optional.of(accountTwo));
        when(accountRepository.findByAccountNumber(ACCOUNT_NUMBER_THREE)).thenReturn(Optional.of(accountThree));
        when(accountRepository.findByAccountNumber(ACCOUNT_NUMBER_FOUR)).thenReturn(Optional.of(accountFour));

        when(transactionHistoryRepository.save(any(TransactionHistory.class))).thenReturn(transactionHistory);

    }

    @Test
    public void testCreateTransactionGivenCorrectDataThenReturnOk() {
        CreateTransactionResponse createTransactionResponse = transactionHistoryApplicationService.createTransaction(createTransactionCommand);
        assertNotNull(createTransactionResponse);
        assertEquals(TransactionStatus.SUCCESS, createTransactionResponse.information().status());
    }

    @Test
    public void testCreateTransactionGivenNotCorrectDataThenReturnException() {
        TransactionDomainException transactionDomainException = assertThrows(TransactionDomainException.class,
                () -> transactionHistoryApplicationService.createTransaction(createTransactionCommandWrongAccountMoney));
        assertEquals("Account doesn't have enough money", transactionDomainException.getMessage());
    }

    @Test
    public void testCreateTransactionGivenZeroDataThenReturnException() {
        TransactionDomainException transactionDomainException = assertThrows(TransactionDomainException.class,
                () -> transactionHistoryApplicationService.createTransaction(createTransactionCommandWrongAccountZeroMoney));
        assertEquals("Amount must be greater than zero!", transactionDomainException.getMessage());
    }
}
