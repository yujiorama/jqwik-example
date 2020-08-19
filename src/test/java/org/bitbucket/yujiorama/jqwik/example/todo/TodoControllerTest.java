package org.bitbucket.yujiorama.jqwik.example.todo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySources({
        @TestPropertySource(locations = {"classpath:application-test.properties"})
})
public class TodoControllerTest {

    @DisplayName("/todo/{good id} の GET リクエスト")
    @Test
    public void get_success(@Autowired MockMvc mockMvc) throws Exception {

        mockMvc.perform(//
                get("/todo/1001")//
        )//
                .andExpect(status().isOk())//
                .andExpect(jsonPath("$.id", equalTo(1001)))//
                .andExpect(jsonPath("$.title", equalTo("testtitle")))//
                .andExpect(jsonPath("$.note", equalTo("testnote")));
    }

    @DisplayName("/todo/ の PUT リクエスト")
    @Test
    public void put_success(@Autowired MockMvc mockMvc) throws Exception {

        var newItem = "{\"title\":\"put_success\",\"note\":\"put_success\"}";

        mockMvc.perform(//
                put("/todo/")//
                        .contentType(MediaType.APPLICATION_JSON_VALUE)//
                        .content(newItem)//
        )//
                .andExpect(status().isCreated())//
                .andExpect(jsonPath("$.title", equalTo("put_success")))//
                .andExpect(jsonPath("$.note", equalTo("put_success")));
    }

    @DisplayName("/todo/{bad id} の GET リクエスト")
    @Test
    public void get_notfound(@Autowired MockMvc mockMvc) throws Exception {

        mockMvc.perform(//
                get("/todo/1374")//
        )//
                .andExpect(status().isNotFound());
    }
}
