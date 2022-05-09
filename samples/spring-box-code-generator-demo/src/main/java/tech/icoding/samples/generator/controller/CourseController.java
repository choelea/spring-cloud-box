package tech.icoding.samples.generator.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import tech.icoding.samples.generator.data.CourseData;
import tech.icoding.samples.generator.facade.CourseFacade;
import tech.icoding.samples.generator.form.CourseForm;

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
  public Page<CourseData> find(@RequestParam(defaultValue = "1") int pageNumber,
      @RequestParam(defaultValue = "10") int pageSize) {
    return courseFacade.find(pageNumber-1, pageSize);
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
