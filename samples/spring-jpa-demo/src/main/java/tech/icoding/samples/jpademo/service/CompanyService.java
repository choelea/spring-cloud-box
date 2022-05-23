package tech.icoding.samples.jpademo.service;

import org.springframework.stereotype.Service;
import tech.icoding.samples.jpademo.entity.Company;
import tech.icoding.samples.jpademo.repository.CompanyRepository;
import tech.icoding.scb.core.service.BaseService;

@Service
public class CompanyService extends BaseService<CompanyRepository, Company, Long> {
}
