package ru.job4j.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.domain.User;
import ru.job4j.repository.RoleRepository;
import ru.job4j.repository.UserRepository;
import ru.job4j.service.RoleService;
import ru.job4j.service.SecurityService;
import ru.job4j.service.UserDetailsService;
import ru.job4j.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * RegController test.
 *
 * @author Alexey Voronin.
 * @since 02.02.2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(RegController.class)
public class RegControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RoleService roleService;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private BCryptPasswordEncoder encoder;
    @MockBean
    private SecurityService securityService;
    @MockBean
    private UserDetailsService detailsService;


    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsRegisterThenReturnRegisterView() throws Exception {
        this.mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenPostMappingEqualsRegisterThenReturnIndexView() throws Exception {
        Mockito.when(userService.getByEmail("test@test.ru")).thenReturn(null);

        this.mockMvc.perform(post("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenPostMappingEqualsRegisterWithErrorShouldReturnRegisterView() throws Exception {
        User u = new User();
        u.setName("test");
        Mockito.when(userService.getByEmail(u.getEmail())).thenReturn(u);

        this.mockMvc.perform(post("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }
}
