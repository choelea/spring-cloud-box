package tech.icoding.samples.generator.service;

import java.lang.Long;
import org.springframework.stereotype.Service;
import tech.icoding.samples.generator.entity.Course;
import tech.icoding.samples.generator.repository.CourseRepository;
import tech.icoding.scb.core.service.BaseService;

@Service
public class CourseService extends BaseService<CourseRepository, Course, Long> {
}
