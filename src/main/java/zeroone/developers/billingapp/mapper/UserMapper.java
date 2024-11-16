package zeroone.developers.billingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import zeroone.developers.billingapp.entity.User;
import zeroone.developers.billingapp.payload.UserDto;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {


//    UserDto userToDto(User user);
//
//
//    User dtoToUser(UserDto userDTO);

}
