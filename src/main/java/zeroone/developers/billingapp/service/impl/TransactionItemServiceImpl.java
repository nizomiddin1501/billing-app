package zeroone.developers.billingapp.service.impl;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zeroone.developers.billingapp.entity.TransactionItem;
import zeroone.developers.billingapp.exceptions.ResourceNotFoundException;
import zeroone.developers.billingapp.exceptions.TransactionItemException;
import zeroone.developers.billingapp.payload.TransactionItemDto;
import zeroone.developers.billingapp.repository.ProductRepository;
import zeroone.developers.billingapp.repository.TransactionItemRepository;
import zeroone.developers.billingapp.repository.TransactionRepository;
import zeroone.developers.billingapp.service.TransactionItemService;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class TransactionItemServiceImpl implements TransactionItemService {

    private final TransactionItemRepository transactionItemRepository;
    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    //private final TransactionItemMapper transactionItemMapper;
    private final ModelMapper modelMapper;

    @Override
    public Page<TransactionItemDto> getAllTransactionItems(int page, int size) {
        Page<TransactionItem> transactionItemsPage = transactionItemRepository.findAll(PageRequest.of(page, size));
        return transactionItemsPage.map(this::transactionItemToDto);
    }

    @Override
    public Optional<TransactionItemDto> getTransactionItemById(Long transactionItemId) {
        TransactionItem transactionItem = transactionItemRepository.findById(transactionItemId)
                .orElseThrow(() -> new ResourceNotFoundException("TransactionItem", " Id ", transactionItemId));
        return Optional.of(transactionItemToDto(transactionItem));
    }

    //Process 2.
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TransactionItemDto createTransactionItem(TransactionItemDto transactionItemDto) {
        TransactionItem transactionItem = dtoToTransactionItem(transactionItemDto);
        boolean transactionExists = transactionRepository.existsByTransactionId(transactionItem.getTransaction().getId());
        if (!transactionExists) {
            throw new TransactionItemException("Such transaction not found");
        }
        boolean productExists = productRepository.existsByProductId(transactionItem.getProduct().getId());
        if (!productExists) {
            throw new TransactionItemException("Such product not found");
        }
        TransactionItem savedTransactionItem = transactionItemRepository.save(transactionItem);
        return transactionItemToDto(savedTransactionItem);
    }

    @Override
    public TransactionItemDto updateTransactionItem(Long transactionItemId, TransactionItemDto transactionItemDto) {
        return null;
    }

    @Override
    public void deleteTransactionItem(Long transactionItemId) {
        TransactionItem transactionItem = transactionItemRepository.findById(transactionItemId)
                .orElseThrow(() -> new ResourceNotFoundException("TransactionItem", " Id ", transactionItemId));
        transactionItemRepository.delete(transactionItem);
    }


    // DTO to Entity conversion
    public TransactionItem dtoToTransactionItem(TransactionItemDto transactionItemDto) {
        return modelMapper.map(transactionItemDto, TransactionItem.class);
    }

    // Entity to DTO conversion
    public TransactionItemDto transactionItemToDto(TransactionItem transactionItem) {
        return modelMapper.map(transactionItem, TransactionItemDto.class);
    }
}
