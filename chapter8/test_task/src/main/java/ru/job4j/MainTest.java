package ru.job4j;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO: comment.
 *
 * @author Alexey Voronin.
 * @since 23.06.2017.
 */
public class MainTest {
    public static void main(String[] args) throws IOException, ParseException {
//        Date currentDate = new Date();
//        long currentTime = currentDate.getTime();
//
//        Date date = new Date(116, 5, 1);
//        long time = date.getTime();
//
//        long result = currentTime - time;
//
//        System.out.println(currentDate.getTime());
//        System.out.println(date.getTime());
//
//
//        System.out.println(currentDate.getTime() < date.getTime());


//        Document document = Jsoup.connect("http://www.sql.ru/forum/job/1").userAgent("Chrome").get();
//        Elements elements = document.getElementsByAttributeValue("class", "altCol");
//        List<Date> list = new ArrayList<>();
//        SimpleDateFormat spd = new SimpleDateFormat("dd MMM yy HH:mm");
//        elements.forEach(element -> {
//            Element text = element.lastElementSibling();
//            String time = text.text().replaceAll(",", "");
//            try {
//                list.add(new Date(spd.parse(time).getTime()));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        });
//
//
//        list.forEach(System.out::println);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        Date startDate = simpleDateFormat.parse("22:48");
        System.out.println(date);
        System.out.println(startDate);
        System.out.println("---" + new Date(date.getTime() - 60 * 60 * 24));

//        String time = list.get(1).replace(",", "");
//        System.out.println(time);
//        SimpleDateFormat spd = new SimpleDateFormat("dd MMM yy HH:mm");
//        Date date = new Date(spd.parse(time).getTime());
//        System.out.println(date);
    }
}
