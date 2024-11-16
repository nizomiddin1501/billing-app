package zeroone.developers.billingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import zeroone.developers.billingapp.entity.User;
import zeroone.developers.billingapp.entity.UserPaymentTransaction;
import zeroone.developers.billingapp.payload.UserPaymentTransactionDto;

@Mapper(componentModel = "spring",
        uses = {UserMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserPaymentTransactionMapper {


//    @Mapping(source = "user", target = "userId", qualifiedByName = "mapUserToUserId")
//    UserPaymentTransactionDto userPaymentTransactionToDto(UserPaymentTransaction userPaymentTransaction);
//
//
//    @Mapping(source = "userId", target = "user.id")
//    UserPaymentTransaction dtoToUserPaymentTransaction(UserPaymentTransactionDto userPaymentTransactionDTO);
//
//    // Helper method to map User to userId
//    @Named("mapUserToUserId")
//    default Long mapUserToUserId(User user) {
//        return user != null ? user.getId() : null;
//    }

}
