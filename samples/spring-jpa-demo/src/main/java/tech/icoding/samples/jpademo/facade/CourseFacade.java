package tech.icoding.samples.jpademo.facade;

import java.lang.Long;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import tech.icoding.samples.jpademo.data.CourseData;
import tech.icoding.samples.jpademo.entity.Course;
import tech.icoding.samples.jpademo.form.CourseForm;
import tech.icoding.samples.jpademo.service.CourseService;

@Component
public class CourseFacade {
  private CourseService courseService;

  public CourseFacade(CourseService courseService) {
    this.courseService = courseService;
  }

  /**
   * Get by ID
   */
  public CourseData get(Long id) {
    final Course course = courseService.find(id);
    final CourseData courseData = convert(course);
    return courseData;
  }

  /**
   * Find pageable data
   */
  public Page<CourseData> find(int page, int size) {
    final PageRequest pageRequest = PageRequest.of(page, size);
    final Page<Course> entityPage = courseService.find(pageRequest);
    final List<CourseData> dataList = entityPage.getContent().stream().map(entity -> {
                return convert(entity);
            }).collect(Collectors.toList());
    final PageImpl<CourseData> dataPage = new PageImpl<CourseData>(dataList, entityPage.getPageable(), entityPage.getTotalElements());
    return dataPage;
  }

  /**
   * Create Entity and save to database
   */
  public CourseData create(CourseForm courseForm) {
    Course course = new Course();
    convert(courseForm,course);
    course = courseService.save(course);
    return convert(course);
  }

  /**
   * Update Entity  to database
   */
  public CourseData update(Long id, CourseForm courseForm) {
    Course course = courseService.find(id);
    convert(courseForm, course);
    course = courseService.update(course);
    return convert(course);
  }

  /**
   * Delete by ID
   */
  public void delete(Long id) {
    courseService.delete(id);
  }

  /**
   * Convert form to entity object
   */
  private void convert(CourseForm courseForm, Course course) {
    BeanUtils.copyProperties(courseForm, course);
    // TODO Override logic. (Copy properties is not the best solution, but an convenient one, for special logic, add below here )
  }

  /**
   * Convert entity to data object
   */
  private CourseData convert(Course course) {
    final CourseData courseData = new CourseData();
    BeanUtils.copyProperties(course, courseData);
    // TODO Override logic. (Copy properties is not the best solution, but an convenient one, for special logic, add below here )
    return courseData;
  }
}
