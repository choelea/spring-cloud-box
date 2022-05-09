package tech.icoding.samples.generator;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : Joe
 * @date : 2022/5/9
 */
@Slf4j
public class AbstractMvcTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected <T> T  create(String uri,Class<T> resClass, Serializable form) throws Exception {
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(JSON.toJSONString(form))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"));
        return objectMapper.readValue(contentAsString, resClass);
    }
    protected <T> T update(String uri, Class<T> resClass, String content, Object ...uriVars) throws Exception {
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .put(uri,uriVars)
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"));
        return objectMapper.readValue(contentAsString, resClass);
    }

    /**
     *
     * @param uri
     * @param resClass
     * @param uriVars
     * @return
     * @throws Exception
     */
    protected <T> T get(String uri, Class<T> resClass, Object ...uriVars) throws Exception {
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(uri,uriVars)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"));
        return objectMapper.readValue(contentAsString, resClass);
    }

    protected <T> Page<T> find(String uri, Class<T> resClass, MultiValueMap<String, String> params) throws Exception {
        final MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(uri).params(params)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(Charset.forName("UTF-8"));
        log.info(contentAsString);
        return objectMapper.readValue(contentAsString, RestResponsePage.class);
    }

    protected MultiValueMap<String, String> emptyMap(){
        return CollectionUtils.toMultiValueMap(Collections.EMPTY_MAP);
    }
    static class RestResponsePage<T> extends PageImpl<T> {

        private static final long serialVersionUID = 3248189030448292002L;

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public RestResponsePage(@JsonProperty("content") List<T> content, @JsonProperty("number") int number, @JsonProperty("size") int size,
                                @JsonProperty("totalElements") Long totalElements, @JsonProperty("pageable") JsonNode pageable, @JsonProperty("last") boolean last,
                                @JsonProperty("totalPages") int totalPages, @JsonProperty("sort") JsonNode sort, @JsonProperty("first") boolean first,
                                @JsonProperty("numberOfElements") int numberOfElements) {
            super(content, PageRequest.of(number, size), totalElements);
        }

        public RestResponsePage(List<T> content, Pageable pageable, long total) {
            super(content, pageable, total);
        }

        public RestResponsePage(List<T> content) {
            super(content);
        }

        public RestResponsePage() {
            super(new ArrayList<T>());
        }

    }
}
