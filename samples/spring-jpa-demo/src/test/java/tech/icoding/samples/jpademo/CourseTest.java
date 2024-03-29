package tech.icoding.samples.jpademo;

import com.alibaba.fastjson.JSON;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import tech.icoding.samples.jpademo.data.CourseData;
import tech.icoding.samples.jpademo.form.CourseForm;
import tech.icoding.samples.jpademo.repository.CourseRepository;
import tech.icoding.scb.core.data.PageData;


/**
 * @author : Joe
 * @date : 2022/5/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CourseTest extends AbstractMvcTest{

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void curd() throws Exception
    {
        EasyRandomParameters easyRandomParameters = new EasyRandomParameters();
        EasyRandom easyRandom = new EasyRandom(easyRandomParameters.stringLengthRange(3,10));

        // 新增
        CourseForm courseForm = easyRandom.nextObject(CourseForm.class);
        CourseData courseData = create("/courses",CourseData.class, courseForm);
        Assert.assertEquals(courseForm.getName(), courseData.getName());

        // 修改
        courseData  = get("/courses/{id}",CourseData.class, courseData.getId());
        Assert.assertEquals(courseForm.getName(), courseData.getName());

        // 详情
        courseForm = easyRandom.nextObject(CourseForm.class);
        courseData = update("/courses/{id}",CourseData.class, JSON.toJSONString(courseForm),courseData.getId());
        Assert.assertEquals(courseForm.getName(), courseData.getName());

        // 列表
        PageData<CourseData> courseDataPage = find("/courses", CourseData.class, emptyMap());
        Assert.assertTrue(!courseDataPage.isEmpty());

    }

    @After
    public void clean(){
        courseRepository.deleteAll();
    }

}
