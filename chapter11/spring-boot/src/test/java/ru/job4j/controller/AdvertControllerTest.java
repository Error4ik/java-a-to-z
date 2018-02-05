package ru.job4j.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.domain.Advert;
import ru.job4j.domain.ModelForFillingAdverts;
import ru.job4j.repository.*;
import ru.job4j.service.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


/**
 * Test.
 *
 * @author Alexey Voronin.
 * @since 31.01.2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AdvertController.class)
public class AdvertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarBrandService carBrandService;
    @MockBean
    private CarBrandRepository carBrandRepository;
    @MockBean
    private CarBodyService bodyService;
    @MockBean
    private CarBodyRepository bodyRepository;
    @MockBean
    private CarModelService modelService;
    @MockBean
    private CarModelsRepository modelsRepository;
    @MockBean
    private DriveUnitService driveUnitService;
    @MockBean
    private DriveUnitRepository driveUnitRepository;
    @MockBean
    private EngineService engineService;
    @MockBean
    private EngineRepository engineRepository;
    @MockBean
    private TransmissionService transmissionService;
    @MockBean
    private TransmissionRepository transmissionRepository;
    @MockBean
    private CityService cityService;
    @MockBean
    private CityRepository cityRepository;
    @MockBean
    private ImageService imageService;
    @MockBean
    private ImageRepository imageRepository;
    @MockBean
    private AdvertService advertService;
    @MockBean
    private AdvertRepository advertRepository;
    @MockBean
    private SecurityService securityService;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private BCryptPasswordEncoder encoder;
    @MockBean
    private RoleService roleService;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private CarService carService;
    @MockBean
    private CarRepository carRepository;
    @MockBean
    private MultipartFile file;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingRootThenReturnIndexPage() throws Exception {
        this.mockMvc.perform(
                get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingListThenReturnListOfAdverts() throws Exception {
        this.mockMvc.perform(
                get("/list"))
                .andExpect(status().isOk());

        verify(this.advertService, times(1)).getAll();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenPostMappingAddThenReturnAddAdvertPage() throws Exception {
        this.mockMvc.perform(
                get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add_advert"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenPostMappingUpdateThenRedirectRoot() throws Exception {
        final Advert advert = new Advert();
        advert.setSale(false);
        Mockito.when(this.advertService.getById(1)).thenReturn(advert);
        Mockito.when(this.advertService.save(advert)).thenReturn(advert);
        this.mockMvc.perform(
                post("/update").param("id", "1"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingCreateThenRedirectRoot() throws Exception {
        MockMultipartFile mockMultipartFile = new MockMultipartFile("data", "12345".getBytes());
        Advert advert = new Advert();
        Mockito.when(this.advertService.prepareAdvert(new ModelForFillingAdverts(), mockMultipartFile)).thenReturn(advert);
        Mockito.when(this.advertService.save(advert)).thenReturn(advert);

        this.mockMvc.perform(
                post("/create")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().is3xxRedirection());
    }
}
