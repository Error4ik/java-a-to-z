package ru.job4j.jdbc_test;


import org.apache.log4j.Logger;

import java.sql.*;

/**
 * TODO: comment.
 *
 * @author Alexey Voronin.
 * @since 30.05.2017.
 */
public class SQLStorage {
    private static final Logger LOGGER = Logger.getLogger(SQLStorage.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/car_factory";
        String userName = "postgres";
        String password = "error4ik1984";

        Connection con = null;
        try {
            con = DriverManager.getConnection(url, userName, password);
//            Statement st = con.createStatement();
//            ResultSet rs = st.executeQuery("SELECT car.id AS id, car.name AS car, car_body.name AS car_body, " +
//                    "engine.name AS engine, transmition.name AS transmission FROM car LEFT JOIN car_body ON car_body.id" +
//                    "= car.car_body_id LEFT JOIN engine ON engine.id = car.engine_id LEFT JOIN transmition ON " +
//                    "transmition.id = car.transmition_id;");
            PreparedStatement ps = con.prepareStatement("SELECT car.id AS id, car.name AS car, car_body.name AS car_body, " +
                    "engine.name AS engine, transmition.name AS transmission FROM car LEFT JOIN car_body ON car_body.id" +
                    "= car.car_body_id LEFT JOIN engine ON engine.id = car.engine_id LEFT JOIN transmition ON " +
                    "transmition.id = car.transmition_id WHERE car.id = ?;");
            ps.setInt(1, 1);

            ResultSet rs = ps.executeQuery();
            //LOGGER.info("id car car_body engine transmission");
            while (rs.next()) {
                LOGGER.info(String.format("%s %s %s %s %s", rs.getInt(1), rs.getString("car"),
                        rs.getString("car_body"), rs.getString("engine"), rs.getString("transmission")));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }
}
