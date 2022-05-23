package tech.icoding.samples.jpademo;

import com.alibaba.fastjson.JSON;
import org.jeasy.random.EasyRandom;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import tech.icoding.samples.jpademo.data.UserData;
import tech.icoding.samples.jpademo.form.UserForm;
import tech.icoding.samples.jpademo.repository.UserRepository;
import tech.icoding.scb.core.data.PageData;


/**
 * @author : Joe
 * @date : 2022/5/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserTest extends AbstractMvcTest{

    @Autowired
    private UserRepository userRepository;

    @Test
    public void curd() throws Exception
    {
        EasyRandom easyRandom = new EasyRandom();

        // 新增
        UserForm userForm = easyRandom.nextObject(UserForm.class);
        UserData userData = create("/users",UserData.class, userForm);
        Assert.assertEquals(userForm.getName(), userData.getName());

        // 修改
        userData  = get("/users/{id}",UserData.class, userData.getId());
        Assert.assertEquals(userForm.getName(), userData.getName());

        // 详情
        userForm = easyRandom.nextObject(UserForm.class);
        userData = update("/users/{id}",UserData.class, JSON.toJSONString(userForm),userData.getId());
        Assert.assertEquals(userForm.getName(), userData.getName());

        // 列表
        PageData<UserData> userDataPage = find("/users", UserData.class, emptyMap());
        Assert.assertTrue(!userDataPage.isEmpty());

    }

    @After
    public void clean(){
        userRepository.deleteAll();
    }

}
