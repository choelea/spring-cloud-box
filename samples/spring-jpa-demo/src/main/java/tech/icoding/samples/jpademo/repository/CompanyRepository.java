package tech.icoding.samples.jpademo.repository;

import java.lang.Long;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tech.icoding.samples.jpademo.entity.Company;

@Repository
public interface CompanyRepository extends JpaSpecificationExecutor<Long>, JpaRepository<Company, Long> {
}
