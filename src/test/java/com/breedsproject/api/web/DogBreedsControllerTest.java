package com.breedsproject.api.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.breedsproject.api.service.DogBreedsService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DogBreedsController.class)
class DogBreedsControllerTest {

  @Autowired private DogBreedsController controller;

  @Autowired private MockMvc mockMvc;

  @MockBean private DogBreedsService service;

  @Test
  void when_searchByBreedNameEndpointCalled_shouldReturnListOfRecordsContainsBreedName()
      throws Exception {
    when(service.getAllDogBreeds()).thenReturn(List.of("papillon", "finnish-lapphund"));

    this.mockMvc
        .perform(get("/breeds/dog/names"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json("{'data':['papillon','finnish-lapphund']}"));
  }
}
