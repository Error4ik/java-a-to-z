package ru.job4j.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.domain.CarBrand;
import ru.job4j.repository.*;
import ru.job4j.service.*;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * FillFieldsController test.
 *
 * @author Alexey Voronin.
 * @since 01.02.2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(FillFieldsController.class)
public class FillFieldsControllerTest {

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


    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsCarBrandThenCallCarBrandServiceMethodGetAll() throws Exception {
        this.mockMvc.perform(
                get("/carBrand"))
                .andExpect(status().isOk());

        verify(this.carBrandService, times(1)).getAll();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsCarModelThenCallCarBrandServiceMethodGetAll() throws Exception {
        final CarBrand brand = new CarBrand();
        brand.setId(10);
        brand.setName("ford");

        Mockito.when(this.carBrandService.getByName(brand.getName())).thenReturn(brand);

        this.mockMvc.perform(
                get("/carModel").param("brand", "ford"))
                .andExpect(status().isOk());

        verify(this.modelService, times(1)).getModelsByBrandId(brand.getId());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsEngineThenCallEngineServiceMethodGetAll() throws Exception {
        this.mockMvc.perform(
                get("/engine"))
                .andExpect(status().isOk());

        verify(this.engineService, times(1)).getAll();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsTransmissionCallTransmissionServiceMethodGetAll() throws Exception {
        this.mockMvc.perform(
                get("/transmission"))
                .andExpect(status().isOk());

        verify(this.transmissionService, times(1)).getAll();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsCarBodyThenCallCarBodyServiceMethodGetAll() throws Exception {
        this.mockMvc.perform(
                get("/carBody"))
                .andExpect(status().isOk());

        verify(this.bodyService, times(1)).getAll();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsDriveUnitThenCallDriveUnitServiceMethodGetAll() throws Exception {
        this.mockMvc.perform(
                get("/driveUnit"))
                .andExpect(status().isOk());

        verify(this.driveUnitService, times(1)).getAll();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsCityThenCallCityServiceMethodGetAll() throws Exception {
        this.mockMvc.perform(
                get("/city"))
                .andExpect(status().isOk());

        verify(this.cityService, times(1)).getAll();
    }
}
