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
import tech.icoding.samples.jpademo.data.CompanyData;
import tech.icoding.samples.jpademo.form.CompanyForm;
import tech.icoding.samples.jpademo.repository.CompanyRepository;
import tech.icoding.scb.core.data.PageData;


/**
 * @author : Joe
 * @date : 2022/5/9
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CompanyTest extends AbstractMvcTest{

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void curd() throws Exception
    {
        EasyRandom easyRandom = new EasyRandom();

        // 新增
        CompanyForm companyForm = easyRandom.nextObject(CompanyForm.class);
        CompanyData companyData = create("/companies",CompanyData.class, companyForm);
        Assert.assertEquals(companyForm.getName(), companyData.getName());

        // 修改
        companyData  = get("/companies/{id}",CompanyData.class, companyData.getId());
        Assert.assertEquals(companyForm.getName(), companyData.getName());

        // 详情
        companyForm = easyRandom.nextObject(CompanyForm.class);
        companyData = update("/companies/{id}",CompanyData.class, JSON.toJSONString(companyForm),companyData.getId());
        Assert.assertEquals(companyForm.getName(), companyData.getName());

        // 列表
        PageData<CompanyData> companyDataPage = find("/companies", CompanyData.class, emptyMap());
        Assert.assertTrue(!companyDataPage.isEmpty());

    }

    @After
    public void clean(){
        companyRepository.deleteAll();
    }

}
