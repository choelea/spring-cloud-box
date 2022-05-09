package tech.icoding.scb.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : Joe
 * @date : 2022/5/9
 */
public class AbstractMvcTest {
    @Autowired
    protected MockMvc mockMvc;
    private String create(String uri, String content) throws Exception {
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"));
        return contentAsString;
    }
    private String update(String uri, String content, Object ...uriVars) throws Exception {
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .put(uri,uriVars)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"));
        return contentAsString;
    }

    private String get(String uri, String content, Object ...uriVars) throws Exception {
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(uri,uriVars)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"));
        return contentAsString;
    }

    private String find(String uri, String name, String... values) throws Exception {
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(uri).param(name, values)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"));
        return contentAsString;
    }
}
