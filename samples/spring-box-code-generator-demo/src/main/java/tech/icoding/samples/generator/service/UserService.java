package tech.icoding.samples.generator.service;

import java.lang.Long;
import org.springframework.stereotype.Service;
import tech.icoding.samples.generator.entity.User;
import tech.icoding.samples.generator.repository.UserRepository;
import tech.icoding.scb.core.service.BaseService;

@Service
public class UserService extends BaseService<UserRepository, User, Long> {
}
