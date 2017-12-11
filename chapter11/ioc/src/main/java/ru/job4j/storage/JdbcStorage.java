package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.job4j.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Jdbc storage, save to object to database with spring jdbcTemplate.
 *
 * @author Alexey Voronin.
 * @since 04.12.2017.
 */

//@Component
public class JdbcStorage implements Storage<User> {

    /**
     * Spring jdbcTemplate.
     */
    private JdbcTemplate jdbcTemplate;

    /**
     * Constructor.
     *
     * @param jdbcTemplate spring jdbcTemplate.
     */
    @Autowired
    public JdbcStorage(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public User save(final User entity) {
        final String query = "insert into users (name, login, password) values (?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                final PreparedStatement statement = con.prepareStatement(query, new String[]{"id"});
                statement.setString(1, entity.getName());
                statement.setString(2, entity.getLogin());
                statement.setString(3, entity.getPassword());
                return statement;
            }
        }, keyHolder);
        entity.setId((Integer) keyHolder.getKey());
        return entity;
    }

    @Override
    public User getById(final int id) {
        final String query = "select * from users where id = ?";
        User returnUser = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(User.class), id);
        return returnUser;
    }

    @Override
    public List<User> getAllEntity() {
        final String query = "select * from users";
        return this.jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public int updateEntity(final User entity) {
        final String query = "update users set name = ?, login = ?, password = ? where id = ?";
        return this.jdbcTemplate.update(query, entity.getName(), entity.getLogin(), entity.getPassword(), entity.getId());
    }

    @Override
    public int deleteEntity(final User entity) {
        final String query = "delete from users where id = ?";
        return this.jdbcTemplate.update(query, entity.getId());
    }
}
