package tech.icoding.samples.jpademo.service;

import java.lang.Long;
import org.springframework.stereotype.Service;
import tech.icoding.samples.jpademo.entity.User;
import tech.icoding.samples.jpademo.repository.UserRepository;
import tech.icoding.scb.core.service.BaseService;

@Service
public class UserService extends BaseService<UserRepository, User, Long> {
}
