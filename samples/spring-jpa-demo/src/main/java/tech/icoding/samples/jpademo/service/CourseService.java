package tech.icoding.samples.jpademo.service;

import java.lang.Long;
import org.springframework.stereotype.Service;
import tech.icoding.samples.jpademo.entity.Course;
import tech.icoding.samples.jpademo.repository.CourseRepository;
import tech.icoding.scb.core.service.BaseService;

@Service
public class CourseService extends BaseService<CourseRepository, Course, Long> {
}
