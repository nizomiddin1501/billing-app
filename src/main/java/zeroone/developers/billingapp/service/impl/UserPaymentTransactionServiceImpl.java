package zeroone.developers.billingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import zeroone.developers.billingapp.entity.User;
import zeroone.developers.billingapp.entity.UserPaymentTransaction;
import zeroone.developers.billingapp.exceptions.ResourceNotFoundException;
import zeroone.developers.billingapp.exceptions.UserPaymentTransactionException;
import zeroone.developers.billingapp.payload.UserPaymentTransactionDto;
import zeroone.developers.billingapp.repository.UserPaymentTransactionRepository;
import zeroone.developers.billingapp.repository.UserRepository;
import zeroone.developers.billingapp.service.UserPaymentTransactionService;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserPaymentTransactionServiceImpl implements UserPaymentTransactionService {

    private final UserPaymentTransactionRepository userPaymentTransactionRepository;
    private final UserRepository userRepository;
    //private final UserPaymentTransactionMapper userPaymentTransactionMapper;
    private final ModelMapper modelMapper;


    @Override
    public Page<UserPaymentTransactionDto> getAllUserPaymentTransactions(int page, int size) {
        Page<UserPaymentTransaction> roomsPage = userPaymentTransactionRepository.findAll(PageRequest.of(page, size));
        return roomsPage.map(this::userPaymentTransactionToDto);
    }

    @Override
    public Optional<UserPaymentTransactionDto> getUserPaymentTransactionById(Long userPaymentTransactionId) {
        UserPaymentTransaction userPaymentTransaction = userPaymentTransactionRepository.findById(userPaymentTransactionId)
                .orElseThrow(() -> new ResourceNotFoundException("UserPaymentTransaction", " Id ", userPaymentTransactionId));
        return Optional.of(userPaymentTransactionToDto(userPaymentTransaction));
    }

    //Process 3.
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public UserPaymentTransactionDto createUserPaymentTransaction(UserPaymentTransactionDto userPaymentTransactionDto) {
        UserPaymentTransaction userPaymentTransaction = dtoToUserPaymentTransaction(userPaymentTransactionDto);

        User user = userRepository.findByUserId(userPaymentTransaction.getUser().getId())
                .orElseThrow(() -> new UserPaymentTransactionException("Such user not found"));

        // User balansini tekshirish
        if (user.getBalance().compareTo(userPaymentTransaction.getAmount()) < 0) {
            throw new UserPaymentTransactionException("Insufficient balance");
        }

        // Balansni kamaytirish
        user.setBalance(user.getBalance().subtract(userPaymentTransaction.getAmount()));
        userRepository.save(user);

        UserPaymentTransaction savedTransactionUserPayment = userPaymentTransactionRepository.save(userPaymentTransaction);
        return userPaymentTransactionToDto(savedTransactionUserPayment);
    }

    @Override
    public UserPaymentTransactionDto updateUserPaymentTransaction(Long userPaymentTransactionId, UserPaymentTransactionDto userPaymentTransactionDto) {
        return null;
    }

    @Override
    public void deleteUserPaymentTransaction(Long userPaymentTransactionId) {
        UserPaymentTransaction userPaymentTransaction = userPaymentTransactionRepository.findById(userPaymentTransactionId)
                .orElseThrow(() -> new ResourceNotFoundException("UserPaymentTransaction", " Id ", userPaymentTransactionId));
        userPaymentTransactionRepository.delete(userPaymentTransaction);
    }

    // DTO to Entity conversion
    public UserPaymentTransaction dtoToUserPaymentTransaction(UserPaymentTransactionDto userPaymentTransactionDto) {
        return modelMapper.map(userPaymentTransactionDto, UserPaymentTransaction.class);
    }

    // Entity to DTO conversion
    public UserPaymentTransactionDto userPaymentTransactionToDto(UserPaymentTransaction userPaymentTransaction) {
        return modelMapper.map(userPaymentTransaction, UserPaymentTransactionDto.class);
    }
}
