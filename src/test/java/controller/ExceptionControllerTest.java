package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.api.controllers.ExceptionController;
import ru.api.entity.Product;
import service.mock.MockProductService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ExceptionController.class, MockProductService.class})
public class ExceptionControllerTest {

    private final static String URL = "http://localhost:8080/api/v1/product";
    ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private ExceptionController exceptionController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(exceptionController).build();
    }

    @Test
    public void findByIdException() throws Exception {
        mockMvc.perform(get(URL + "/3"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createException() throws Exception {
        Product product = new Product(1, "bicycle");
        String requestJSON = mapper.writeValueAsString(product);
        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateTest() throws Exception {
        Product product = new Product(3, "testProduct");
        String requestJSON = mapper.writeValueAsString(product);
        mockMvc.perform(put(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete(URL + "/3"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteEntityTest() throws Exception {
        mockMvc.perform(delete(URL + "/1"))
                .andExpect(status().isNotFound());
    }
}
