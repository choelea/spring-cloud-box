package tech.icoding.samples.jpademo.facade;

import java.lang.Long;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import tech.icoding.samples.jpademo.data.CategoryData;
import tech.icoding.samples.jpademo.entity.Category;
import tech.icoding.samples.jpademo.form.CategoryForm;
import tech.icoding.samples.jpademo.service.CategoryService;

@Component
public class CategoryFacade {
  private CategoryService categoryService;

  public CategoryFacade(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  /**
   * Get by ID
   */
  public CategoryData get(Long id) {
    final Category category = categoryService.find(id);
    final CategoryData categoryData = convert(category);
    return categoryData;
  }

  /**
   * Find pageable data
   */
  public Page<CategoryData> find(int page, int size) {
    final PageRequest pageRequest = PageRequest.of(page, size);
    final Page<Category> entityPage = categoryService.find(pageRequest);
    final List<CategoryData> dataList = entityPage.getContent().stream().map(entity -> {
                return convert(entity);
            }).collect(Collectors.toList());
    final PageImpl<CategoryData> dataPage = new PageImpl<CategoryData>(dataList, entityPage.getPageable(), entityPage.getTotalElements());
    return dataPage;
  }

  /**
   * Create Entity and save to database
   */
  public CategoryData create(CategoryForm categoryForm) {
    Category category = new Category();
    convert(categoryForm,category);
    category = categoryService.save(category);
    return convert(category);
  }

  /**
   * Update Entity  to database
   */
  public CategoryData update(Long id, CategoryForm categoryForm) {
    Category category = categoryService.find(id);
    convert(categoryForm, category);
    category = categoryService.update(category);
    return convert(category);
  }

  /**
   * Delete by ID
   */
  public void delete(Long id) {
    categoryService.delete(id);
  }

  /**
   * Convert form to entity object
   */
  private void convert(CategoryForm categoryForm, Category category) {
    BeanUtils.copyProperties(categoryForm, category);
    // TODO Override logic. (Copy properties is not the best solution, but an convenient one, for special logic, add below here )
  }

  /**
   * Convert entity to data object
   */
  private CategoryData convert(Category category) {
    final CategoryData categoryData = new CategoryData();
    BeanUtils.copyProperties(category, categoryData);
    // TODO Override logic. (Copy properties is not the best solution, but an convenient one, for special logic, add below here )
    return categoryData;
  }
}
