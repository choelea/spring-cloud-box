package tech.icoding.samples.generator.service;

import java.lang.Long;
import org.springframework.stereotype.Service;
import tech.icoding.samples.generator.entity.Company;
import tech.icoding.samples.generator.repository.CompanyRepository;
import tech.icoding.scb.core.service.BaseService;

@Service
public class CompanyService extends BaseService<CompanyRepository, Company, Long> {
}
