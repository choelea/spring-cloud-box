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
import tech.icoding.samples.jpademo.data.ProductData;
import tech.icoding.samples.jpademo.facade.ProductFacade;
import tech.icoding.samples.jpademo.form.ProductForm;
import tech.icoding.scb.core.data.PageData;

@RestController
@RequestMapping("/products")
public class ProductController {
  private ProductFacade productFacade;

  public ProductController(ProductFacade productFacade) {
    this.productFacade = productFacade;
  }

  /**
   * Get by ID
   */
  @GetMapping("/{id}")
  public ProductData get(@PathVariable final Long id) {
    final ProductData productData = productFacade.get(id);
    return productData;
  }

  /**
   * Find pageable data
   */
  @GetMapping
  public PageData<ProductData> find(@RequestParam(defaultValue = "1") int pageNumber,
      @RequestParam(defaultValue = "10") int pageSize) {
    final Page<ProductData> dataPage = productFacade.find(pageNumber-1, pageSize);
    return new PageData<>(dataPage);
  }

  /**
   * Create
   */
  @PostMapping
  public ProductData create(@RequestBody final ProductForm productForm) {
    return productFacade.create(productForm);
  }

  /**
   * Create
   */
  @PutMapping("/{id}")
  public ProductData update(@PathVariable final Long id,
      @RequestBody final ProductForm productForm) {
    return productFacade.update(id, productForm);
  }

  /**
   * Delete by ID
   */
  @DeleteMapping("/{id}")
  public void delete(@PathVariable final Long id) {
    productFacade.delete(id);
  }
}
