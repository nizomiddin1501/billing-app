package zeroone.developers.billingapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zeroone.developers.billingapp.entity.Category;
import zeroone.developers.billingapp.exceptions.CategoryException;
import zeroone.developers.billingapp.exceptions.ResourceNotFoundException;
import zeroone.developers.billingapp.payload.CategoryDto;
import zeroone.developers.billingapp.repository.CategoryRepository;
import zeroone.developers.billingapp.service.CategoryService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    //private final CategoryMapper categoryMapper;
    private final ModelMapper modelMapper;

    @Override
    public Page<CategoryDto> getAllCategories(int page, int size) {
        Page<Category> categoriesPage = categoryRepository.findAll(PageRequest.of(page, size));
        return categoriesPage.map(this::categoryToDto);
    }

    @Override
    public Optional<CategoryDto> getCategoryById(Long categoryId) {
        Category room = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", " Id ", categoryId));
        return Optional.of(categoryToDto(room));
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws CategoryException {
        Category category = dtoToCategory(categoryDto);
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new CategoryException("Category name cannot be empty");
        }
        boolean existsByName = categoryRepository.existsByName(category.getName());
        if (existsByName) {
            throw new CategoryException("Category name already exists");
        }
        return categoryToDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", " Id ", categoryId));
        existingCategory.setName(categoryDto.getName());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return categoryToDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", " Id ", categoryId));
        categoryRepository.delete(category);
    }


    // DTO to Entity conversion
    public Category dtoToCategory(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    // Entity to DTO conversion
    public CategoryDto categoryToDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
