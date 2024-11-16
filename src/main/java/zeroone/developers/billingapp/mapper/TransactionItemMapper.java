package zeroone.developers.billingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import zeroone.developers.billingapp.entity.Product;
import zeroone.developers.billingapp.entity.Transaction;
import zeroone.developers.billingapp.entity.TransactionItem;
import zeroone.developers.billingapp.payload.TransactionItemDto;

@Mapper(componentModel = "spring",
        uses = {ProductMapper.class,TransactionMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionItemMapper {

//    @Mapping(source = "product", target = "productId", qualifiedByName = "mapProductToProductId")
//    @Mapping(source = "transaction", target = "transactionId", qualifiedByName = "mapTransactionToTransactionId")
//    TransactionItemDto transactionItemToDto(TransactionItem transactionItem);
//
//
//    @Mapping(source = "productId", target = "product.id")
//    @Mapping(source = "transactionId", target = "transaction.id")
//    TransactionItem dtoToTransactionItem(TransactionItemDto transactionItemDTO);
//
//    // Helper method to map Product to productId
//    @Named("mapProductToProductId")
//    default Long mapProductToProductId(Product product) {
//        return product != null ? product.getId() : null;
//    }
//
//    // Helper method to map Transaction to userId
//    @Named("mapTransactionToTransactionId")
//    default Long mapTransactionToTransactionId(Transaction transaction) {
//        return transaction != null ? transaction.getId() : null;
//    }
}
