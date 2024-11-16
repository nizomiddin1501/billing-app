package zeroone.developers.billingapp.service.impl;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zeroone.developers.billingapp.entity.Transaction;
import zeroone.developers.billingapp.exceptions.ResourceNotFoundException;
import zeroone.developers.billingapp.exceptions.TransactionException;
import zeroone.developers.billingapp.payload.TransactionDto;
import zeroone.developers.billingapp.repository.TransactionRepository;
import zeroone.developers.billingapp.repository.UserRepository;
import zeroone.developers.billingapp.service.TransactionService;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    //private final TransactionMapper transactionMapper;
    private final ModelMapper modelMapper;

    @Override
    public Page<TransactionDto> getAllTransactions(int page, int size) {
        Page<Transaction> transactionsPage = transactionRepository.findAll(PageRequest.of(page, size));
        return transactionsPage.map(this::transactionToDto);
    }

    @Override
    public Optional<TransactionDto> getTransactionById(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", " Id ", transactionId));
        return Optional.of(transactionToDto(transaction));
    }

    //Process 1.
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Transaction transaction = dtoToTransaction(transactionDto);
        boolean userExists = userRepository.existsByUserId(transaction.getUser().getId());
        if (!userExists) {
            throw new TransactionException("Such user not found");
        }
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionToDto(savedTransaction);
    }

    @Override
    public TransactionDto updateTransaction(Long transactionId, TransactionDto transactionDto) {
        return null;
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction", " Id ", transactionId));
        transactionRepository.delete(transaction);
    }

    // DTO to Entity conversion
    public Transaction dtoToTransaction(TransactionDto transactionDto) {
        return modelMapper.map(transactionDto, Transaction.class);
    }

    // Entity to DTO conversion
    public TransactionDto transactionToDto(Transaction transaction) {
        return modelMapper.map(transaction, TransactionDto.class);
    }
}
