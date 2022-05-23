package tech.icoding.samples.jpademo.service;

import java.lang.Long;
import org.springframework.stereotype.Service;
import tech.icoding.samples.jpademo.entity.Product;
import tech.icoding.samples.jpademo.repository.ProductRepository;
import tech.icoding.scb.core.service.BaseService;

@Service
public class ProductService extends BaseService<ProductRepository, Product, Long> {
}
