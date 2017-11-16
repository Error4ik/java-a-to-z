package ru.job4j.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * User.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */
@Data
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    @JsonIgnore
    private List<Advert> adverts;
}
