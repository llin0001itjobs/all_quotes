package org.llin.twelvequotes.test.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llin.twelvequotes.controller.HomeController;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import jakarta.servlet.http.HttpSession;

public class HomeControllerTest {
    private MockMvc mockMvc;

    @Mock
    private HttpSession session;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void testShowInstruction() throws Exception {
        mockMvc.perform(get("/home/instruction"))
            .andExpect(status().isOk())
            .andExpect(view().name("home"));
    }

    @Test
    public void testHandleFormSubmission() throws Exception {
        mockMvc.perform(post("/home/submit").session(new MockHttpSession()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/country/list"));
    }
}
