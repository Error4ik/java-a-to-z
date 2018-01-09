package ru.job4j.models;

import java.util.Objects;

/**
 * Image.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */
public class Image {

    /**
     * Id.
     */
    private int id;
    /**
     * Name.
     */
    private String name;
    /**
     * image url.
     */
    private String imageUrl;

    /**
     * Get.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set.
     * @param id id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set.
     * @param name name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get.
     * @return image url.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Set.
     * @param imageUrl image url.
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Image)) {
            return false;
        }
        Image photo = (Image) o;
        return getId() == photo.getId()
                && Objects.equals(getName(), photo.getName())
                && Objects.equals(getImageUrl(), photo.getImageUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getImageUrl());
    }

    @Override
    public String toString() {
        return String.format("Photo {id=%s name=%s imageUrl=%s}", getId(), getName(), getImageUrl());
    }
}
