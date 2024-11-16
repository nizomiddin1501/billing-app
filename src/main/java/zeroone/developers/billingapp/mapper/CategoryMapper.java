package zeroone.developers.billingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import zeroone.developers.billingapp.entity.Category;
import zeroone.developers.billingapp.payload.CategoryDto;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {


//    CategoryDto categoryToDto(Category category);
//
//
//    Category dtoToCategory(CategoryDto categoryDTO);


}
