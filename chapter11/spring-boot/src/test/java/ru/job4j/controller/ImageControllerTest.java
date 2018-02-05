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
import ru.job4j.domain.Image;
import ru.job4j.repository.ImageRepository;
import ru.job4j.service.ImageService;

import java.io.File;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * ImageController test.
 *
 * @author Alexey Voronin.
 * @since 02.02.2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ImageController.class)
public class ImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageService service;
    @MockBean
    private ImageRepository repository;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetMappingEqualsImageAndIdParameterThenImageServiceCallMethodGetById() throws Exception {
        File tempFile = File.createTempFile("test", "jpg");
        Image image = new Image();
        image.setImageUrl(tempFile.getAbsolutePath());
        Mockito.when(service.getById(1)).thenReturn(image);

        this.mockMvc.perform(
                get("/image/1"))
                .andExpect(status().isOk());

        verify(this.service, times(1)).getById(1);
    }

}