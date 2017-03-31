package ru.job4j;

import ru.job4j.model.Order;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
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
     * Method parse xml file.
     *
     * @param file this file is for parsing.
     * @return the map is filled with orders.
     * @throws XMLStreamException throws if unexpected processing conditions.
     * @throws IOException        i/o exception.
     */
    public Map<Integer, Order> parse(final String file) throws XMLStreamException, IOException {
        Map<Integer, Order> map = new HashMap<>();
        XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(file));
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
        reader.close();
        return map;
    }

    /**
     * Method put all orders in map.
     *
     * @param reader xml reader.
     * @param map    the map that is filled with orders.
     */
    private void add(final XMLStreamReader reader, final Map<Integer, Order> map) {
        map.put(Integer.parseInt(reader.getAttributeValue(4)),
                new Order(reader.getAttributeValue(0),
                        reader.getAttributeValue(1),
                        Double.parseDouble(reader.getAttributeValue(2)),
                        Integer.parseInt(reader.getAttributeValue(3)),
                        Integer.parseInt(reader.getAttributeValue(4))));

    }

    /**
     * Delete order from map.
     *
     * @param reader xml reader.
     * @param map    the map that is filled with orders.
     */
    private void delete(final XMLStreamReader reader, final Map<Integer, Order> map) {
        map.remove(Integer.parseInt(reader.getAttributeValue(1)));
    }
}
