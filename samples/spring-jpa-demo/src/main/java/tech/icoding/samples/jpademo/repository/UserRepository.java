package tech.icoding.samples.jpademo.repository;

import java.lang.Long;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import tech.icoding.samples.jpademo.entity.User;

@Repository
public interface UserRepository extends JpaSpecificationExecutor<Long>, JpaRepository<User, Long> {
}
