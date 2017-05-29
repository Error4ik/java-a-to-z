package ru.job4j;

import java.util.Map;

/**
 * Simple request generator.
 *
 * @author Alexey Voronin.
 * @since 28.05.2017.
 */
public class RequestGenerator {

    /**
     * @param request a collection containing a user request.
     * @return sql request string.
     */
    public String createRequest(Map<String, String> request) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;

        sb.append("SELECT").append(" ");

        if (!request.containsValue("field")) {
            sb.append("*");
        }

        for (Map.Entry<String, String> entry : request.entrySet()) {
            if (entry.getValue().equals("field")) {
                if (!flag) {
                    sb.append(entry.getKey());
                    flag = true;
                } else {
                    sb.append(", ").append(entry.getKey());
                }
            }
        }

        sb.append(" ").append("FROM");

        for (Map.Entry<String, String> entry : request.entrySet()) {
            if (entry.getValue().equals("table")) {
                sb.append(" ").append(entry.getKey());
            }
        }

        if (request.containsValue("condition")) {
            sb.append(" ").append("WHERE");
        }

        for (Map.Entry<String, String> entry : request.entrySet()) {
            if (entry.getValue().equals("condition")) {
                sb.append(" ").append(entry.getKey());
            }
        }

        return sb.append(";").toString();
    }
}
