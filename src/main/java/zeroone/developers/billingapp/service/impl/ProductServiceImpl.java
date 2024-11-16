package zeroone.developers.billingapp.service.impl;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zeroone.developers.billingapp.entity.Product;
import zeroone.developers.billingapp.exceptions.ProductException;
import zeroone.developers.billingapp.exceptions.ResourceNotFoundException;
import zeroone.developers.billingapp.payload.ProductDto;
import zeroone.developers.billingapp.repository.ProductRepository;
import zeroone.developers.billingapp.service.ProductService;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    //private final ProductMapper productMapper;
    private final ModelMapper modelMapper;


    @Override
    public Page<ProductDto> getAllProducts(int page, int size) {
        Page<Product> productsPage = productRepository.findAll(PageRequest.of(page, size));
        return productsPage.map(this::productToDto);
    }

    @Override
    public Optional<ProductDto> getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", " Id ", productId));
        return Optional.of(productToDto(product));
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = dtoToProduct(productDto);
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new ProductException("Product name cannot be null or empty");
        }
        boolean exists = productRepository.existsByName(product.getName());
        if (exists) {
            throw new ProductException("Product with this name already exists");
        }
        Product savedProduct = productRepository.save(product);
        return productToDto(savedProduct);
    }

    //Process 4.
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", " Id ", productId));
        existingProduct.setName(productDto.getName());
        existingProduct.setCount(productDto.getCount());
        Product updatedProduct = productRepository.save(existingProduct);
        return productToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", " Id ", productId));
        productRepository.delete(product);
    }

    // DTO to Entity conversion
    public Product dtoToProduct(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

    // Entity to DTO conversion
    public ProductDto productToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}
