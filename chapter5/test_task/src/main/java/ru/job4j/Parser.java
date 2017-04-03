package ru.job4j;

import ru.job4j.model.Order;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.HashMap;

/**
 * The class for parsing the XML file.
 *
 * @author Alexey Voronin.
 * @since 27.03.2017.
 */
public class Parser {

    /**
     * Book value.
     */
    private static final int BOOK = 0;

    /**
     * Operation type value.
     */
    private static final int OPERATION_TYPE = 1;

    /**
     * Price.
     */
    private static final int PRICE = 2;

    /**
     * Volume.
     */
    private static final int VOLUME = 3;

    /**
     * Order id.
     */
    private static final int ORDER_ID = 4;

    /**
     * Delete order id.
     */
    private static final int DELETE_ORDER_ID = 1;

    /**
     * Method parse xml file.
     *
     * @param file this file is for parsing.
     * @return the map is filled with orders.
     */
    public Map<Integer, Order> parse(final String file) {
        Map<Integer, Order> map = new HashMap<>();

        XMLStreamReader reader = null;
        try {
            reader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(file));
            if (reader != null) {
                while (reader.hasNext()) {
                    reader.next();
                    if (reader.isStartElement()) {
                        if ("AddOrder".equals(reader.getLocalName())) {
                            this.add(reader, map);
                        } else if ("DeleteOrder".equals(reader.getLocalName())) {
                            this.delete(reader, map);
                        }
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    /**
     * Method put all orders in map.
     *
     * @param reader xml reader.
     * @param map    the map that is filled with orders.
     */
    private void add(final XMLStreamReader reader, final Map<Integer, Order> map) {
        map.put(Integer.parseInt(reader.getAttributeValue(ORDER_ID)),
                new Order(reader.getAttributeValue(BOOK),
                        reader.getAttributeValue(OPERATION_TYPE),
                        Double.parseDouble(reader.getAttributeValue(PRICE)),
                        Integer.parseInt(reader.getAttributeValue(VOLUME)),
                        Integer.parseInt(reader.getAttributeValue(ORDER_ID))));

    }

    /**
     * Delete order from map.
     *
     * @param reader xml reader.
     * @param map    the map that is filled with orders.
     */
    private void delete(final XMLStreamReader reader, final Map<Integer, Order> map) {
        map.remove(Integer.parseInt(reader.getAttributeValue(DELETE_ORDER_ID)));
    }
}
