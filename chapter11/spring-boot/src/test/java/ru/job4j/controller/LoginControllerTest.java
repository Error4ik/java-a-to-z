package ru.job4j.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Login controller test.
 *
 * @author Alexey Voronin.
 * @since 02.02.2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingLoginWithoutParameterThenReturnLoginPageWithoutMessage() throws Exception {
        this.mockMvc.perform(
                get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingLoginWithErrorParameterThenReturnLoginPageWithErrorMessage() throws Exception {
        MvcResult result = this.mockMvc.perform(
                get("/login").param("error", "error"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andReturn();

        assertTrue(result.getModelAndView().getModel().values().contains("Invalid Credentials provided."));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingLoginWithLogoutParameterThenReturnLoginPageWithLogOutMessage() throws Exception {
        MvcResult result = this.mockMvc.perform(
                get("/login").param("logout", "logout"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andReturn();

        assertTrue(result.getModelAndView().getModel().values().contains("Logged out successfully."));
    }

}