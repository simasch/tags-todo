package ch.transgourmet.todo.api;

import ch.transgourmet.todo.TestcontainersConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createAndCompleteTodo() throws Exception {
        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "text": "Test"
                                }"""))
                .andExpect(status().isCreated());

        var mvcResult = mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andReturn();

        var todos = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TodoDTO[].class);
        assertThat(todos).hasSize(2);

        mockMvc.perform(delete("/todos/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/todos/1"))
                .andExpect(status().isNotFound());
    }
}