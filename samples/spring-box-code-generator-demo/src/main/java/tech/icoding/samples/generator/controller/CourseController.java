package tech.icoding.samples.generator.controller;

import java.lang.Long;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.icoding.samples.generator.data.CourseData;
import tech.icoding.samples.generator.facade.CourseFacade;
import tech.icoding.samples.generator.form.CourseForm;
import tech.icoding.scb.core.data.PageData;

@RestController
@RequestMapping("/courses")
public class CourseController {
  private CourseFacade courseFacade;

  public CourseController(CourseFacade courseFacade) {
    this.courseFacade = courseFacade;
  }

  /**
   * Get by ID
   */
  @GetMapping("/{id}")
  public CourseData get(@PathVariable final Long id) {
    final CourseData courseData = courseFacade.get(id);
    return courseData;
  }

  /**
   * Find pageable data
   */
  @GetMapping
  public PageData<CourseData> find(@RequestParam(defaultValue = "1") int pageNumber,
      @RequestParam(defaultValue = "10") int pageSize) {
    final Page<CourseData> dataPage = courseFacade.find(pageNumber-1, pageSize);
    return new PageData<>(dataPage);
  }

  /**
   * Create
   */
  @PostMapping
  public CourseData create(@RequestBody final CourseForm courseForm) {
    return courseFacade.create(courseForm);
  }

  /**
   * Create
   */
  @PutMapping("/{id}")
  public CourseData update(@PathVariable final Long id, @RequestBody final CourseForm courseForm) {
    return courseFacade.update(id, courseForm);
  }

  /**
   * Delete by ID
   */
  @DeleteMapping("/{id}")
  public void delete(@PathVariable final Long id) {
    courseFacade.delete(id);
  }
}
