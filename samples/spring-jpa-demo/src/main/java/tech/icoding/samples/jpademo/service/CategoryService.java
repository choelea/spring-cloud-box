package tech.icoding.samples.jpademo.service;

import java.lang.Long;
import org.springframework.stereotype.Service;
import tech.icoding.samples.jpademo.entity.Category;
import tech.icoding.samples.jpademo.repository.CategoryRepository;
import tech.icoding.scb.core.service.BaseService;

@Service
public class CategoryService extends BaseService<CategoryRepository, Category, Long> {
}
