package tech.icoding.samples.jpademo.controller;

import java.lang.Long;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.icoding.samples.jpademo.data.CategoryData;
import tech.icoding.samples.jpademo.facade.CategoryFacade;
import tech.icoding.samples.jpademo.form.CategoryForm;
import tech.icoding.scb.core.data.PageData;

@RestController
@RequestMapping("/categories")
public class CategoryController {
  private CategoryFacade categoryFacade;

  public CategoryController(CategoryFacade categoryFacade) {
    this.categoryFacade = categoryFacade;
  }

  /**
   * Get by ID
   */
  @GetMapping("/{id}")
  public CategoryData get(@PathVariable final Long id) {
    final CategoryData categoryData = categoryFacade.get(id);
    return categoryData;
  }

  /**
   * Find pageable data
   */
  @GetMapping
  public PageData<CategoryData> find(@RequestParam(defaultValue = "1") int pageNumber,
      @RequestParam(defaultValue = "10") int pageSize) {
    final Page<CategoryData> dataPage = categoryFacade.find(pageNumber-1, pageSize);
    return new PageData<>(dataPage);
  }

  /**
   * Create
   */
  @PostMapping
  public CategoryData create(@RequestBody final CategoryForm categoryForm) {
    return categoryFacade.create(categoryForm);
  }

  /**
   * Create
   */
  @PutMapping("/{id}")
  public CategoryData update(@PathVariable final Long id,
      @RequestBody final CategoryForm categoryForm) {
    return categoryFacade.update(id, categoryForm);
  }

  /**
   * Delete by ID
   */
  @DeleteMapping("/{id}")
  public void delete(@PathVariable final Long id) {
    categoryFacade.delete(id);
  }
}
