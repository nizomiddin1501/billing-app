package zeroone.developers.billingapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import zeroone.developers.billingapp.entity.Category;
import zeroone.developers.billingapp.entity.Product;
import zeroone.developers.billingapp.payload.ProductDto;

@Mapper(componentModel = "spring",
        uses = {CategoryMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {



//    @Mapping(source = "category", target = "categoryId", qualifiedByName = "mapCategoryToCategoryId")
//    ProductDto productToDto(Product product);
//
//
//    @Mapping(source = "categoryId", target = "category.id")
//    Product dtoToProduct(ProductDto productDTO);
//
//    // Helper method to map Category to categoryId
//    @Named("mapCategoryToCategoryId")
//    default Long mapCategoryToCategoryId(Category category) {
//        return category != null ? category.getId() : null;
//    }

}
