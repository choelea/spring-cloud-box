package tech.icoding.samples.jpademo.facade;

import java.lang.Long;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import tech.icoding.samples.jpademo.data.ProductData;
import tech.icoding.samples.jpademo.entity.Product;
import tech.icoding.samples.jpademo.form.ProductForm;
import tech.icoding.samples.jpademo.service.ProductService;

@Component
public class ProductFacade {
  private ProductService productService;

  public ProductFacade(ProductService productService) {
    this.productService = productService;
  }

  /**
   * Get by ID
   */
  public ProductData get(Long id) {
    final Product product = productService.find(id);
    final ProductData productData = convert(product);
    return productData;
  }

  /**
   * Find pageable data
   */
  public Page<ProductData> find(int page, int size) {
    final PageRequest pageRequest = PageRequest.of(page, size);
    final Page<Product> entityPage = productService.find(pageRequest);
    final List<ProductData> dataList = entityPage.getContent().stream().map(entity -> {
                return convert(entity);
            }).collect(Collectors.toList());
    final PageImpl<ProductData> dataPage = new PageImpl<ProductData>(dataList, entityPage.getPageable(), entityPage.getTotalElements());
    return dataPage;
  }

  /**
   * Create Entity and save to database
   */
  public ProductData create(ProductForm productForm) {
    Product product = new Product();
    convert(productForm,product);
    product = productService.save(product);
    return convert(product);
  }

  /**
   * Update Entity  to database
   */
  public ProductData update(Long id, ProductForm productForm) {
    Product product = productService.find(id);
    convert(productForm, product);
    product = productService.update(product);
    return convert(product);
  }

  /**
   * Delete by ID
   */
  public void delete(Long id) {
    productService.delete(id);
  }

  /**
   * Convert form to entity object
   */
  private void convert(ProductForm productForm, Product product) {
    BeanUtils.copyProperties(productForm, product);
    // TODO Override logic. (Copy properties is not the best solution, but an convenient one, for special logic, add below here )
  }

  /**
   * Convert entity to data object
   */
  private ProductData convert(Product product) {
    final ProductData productData = new ProductData();
    BeanUtils.copyProperties(product, productData);
    // TODO Override logic. (Copy properties is not the best solution, but an convenient one, for special logic, add below here )
    return productData;
  }
}
