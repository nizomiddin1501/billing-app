package zeroone.developers.billingapp.mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import zeroone.developers.billingapp.entity.Transaction;
import zeroone.developers.billingapp.entity.User;
import zeroone.developers.billingapp.payload.TransactionDto;

@Mapper(componentModel = "spring",
        uses = {UserMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {


//    @Mapping(source = "user", target = "userId", qualifiedByName = "mapUserToUserId")
//    TransactionDto transactionToDto(Transaction transaction);
//
//
//    @Mapping(source = "userId", target = "user.id")
//    Transaction dtoToTransaction(TransactionDto transactionDTO);
//
//    // Helper method to map User to userId
//    @Named("mapUserToUserId")
//    default Long mapUserToUserId(User user) {
//        return user != null ? user.getId() : null;
//    }

}
