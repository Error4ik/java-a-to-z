package ru.job4j.util;

import org.springframework.web.multipart.MultipartFile;
import ru.job4j.domain.Image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Class contains method for write photo to disk.
 *
 * @author Alexey Voronin.
 * @since 06.01.2018.
 */
public class WritePhotoToDisk {

    /**
     * Writing photo to disk.
     *
     * @param multipartFile file.
     * @return photo.
     */
    public Image writePhotoToDisk(final MultipartFile multipartFile) {
        Image photo = new Image();
        File dir = new File("C:/upload/test/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir.getAbsolutePath() + "/" + multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
            fos.flush();
            photo.setName(file.getName());
            photo.setImageUrl(file.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return photo;
    }
}
