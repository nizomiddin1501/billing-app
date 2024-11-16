package zeroone.developers.billingapp.service;

import org.springframework.data.domain.Page;
import zeroone.developers.billingapp.payload.ProductDto;

import java.util.Optional;

public interface ProductService {

    Page<ProductDto> getAllProducts(int page, int size);

    Optional<ProductDto> getProductById(Long productId);

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(Long productId, ProductDto productDto);

    void deleteProduct(Long productId);

}
