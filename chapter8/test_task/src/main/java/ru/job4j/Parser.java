package ru.job4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: comment.
 *
 * @author Alexey Voronin.
 * @since 21.06.2017.
 */
public class Parser {

    private static final Date startDate = new Date(2017, 0, 1, 0, 0);


    public static void main(String[] args) {
        Set<Vacancy> vacancies = new HashSet<>();
        final String USER_AGENT = "Chrome";
        final String sep = "/";

        Pattern pattern = Pattern.compile("\\b[Jj]ava\\b");

        try {
//            Document document = Jsoup.connect("http://www.sql.ru/forum/job").userAgent(USER_AGENT).get();
//            Elements elements = document.getElementsByAttributeValue("class", "postslisttopic");
//
//            elements.forEach(element -> {
//                Element elem = element.child(0);
//                String reference = elem.attr("href");
//                String text = elem.text();
//                if (text.contains("java")) {
//                    vacancies.add(new Vacancy(text, reference));
//                }
//
//                for (Vacancy vacancy : vacancies) {
//                    System.out.println(vacancy);
//                }
//            });

            // Elements el = document.getElementsByAttributeValue("class", "sort_options");

//            el.forEach(element -> {
//                Element elem = element.child(0);
//                System.out.println(elem);
//                System.out.println(elem.text());
//            });

            // vacancies.forEach(System.out::println);

//            Document document = Jsoup.connect("http://www.sql.ru/forum/1263463/vakansiya-razrabotchik-java")
//                    .userAgent(USER_AGENT).get();
//            Elements el = document.getElementsByAttributeValue("class", "msgFooter");
//
            //List<String> list = new ArrayList<>();
//            el.forEach(element -> {
//                Elements e = element.getAllElements();
//                list.add(e.first().text());
//            });

            SimpleDateFormat spd = new SimpleDateFormat("dd MMM yy HH:mm");
            for (int i = 1; i <= 932; i++) {
                Document doc = Jsoup.connect("http://www.sql.ru/forum/job" + sep + i).userAgent(USER_AGENT).get();
                Elements elements = doc.getElementsByAttributeValue("class", "postslisttopic");
                elements.forEach(element -> {
                    Element elem = element.child(0);
                    String text = elem.text();
                    String reference = elem.attr("href");
                    Date date = new Date();
                    Matcher matcher = pattern.matcher(text);
                    while (matcher.find()) {
                        Vacancy vacancy = new Vacancy(text, reference, date);
                        vacancies.add(vacancy);
                    }
                });
            }

            vacancies.forEach(System.out::println);

            System.out.println(vacancies.size());

            Date date = null;

//            for (String s : list) {
//                String result = getDate(s);
//                if (s.contains("вчера")){
//                    date = new Date(new Date().getTime() - 60 * 1000 * 60 * 24);
//                } else if (s.contains("сегодня")) {
//                    date = new Date();
//                }
//                System.out.println(spd.parse(result));
//            }

        } catch (IOException e) {
            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
        }
    }

    public static String getDate(final String line) {
        char[] array = line.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '[') {
                break;
            }
            sb.append(array[i]);
        }
        return sb.toString().replaceAll("\u00a0", "").replaceAll(",", " ");
    }
}
