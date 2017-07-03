package ru.job4j.parser;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.dataBase.VacancyToDataBase;
import ru.job4j.model.Vacancy;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Disassembles the site, searches for jobs that match the template and adds to the worksheet.
 *
 * @author Alexey Voronin.
 * @since 26.06.2017.
 */
public class Parser {

    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(VacancyToDataBase.class);

    /**
     * User agent.
     */
    private final String userAgent = "Chrome";

    /**
     * Vacancies.
     */
    private final List<Vacancy> vacancies = new ArrayList<>();

    /**
     * Pattern.
     */
    private final Pattern pattern = Pattern.compile("\\b[Jj]ava\\b");


    /**
     * Looking for vacancies from the beginning of the year and adding them to the sheet.
     *
     * @param firstStart first start to parser or not.
     * @param date       Date of the newest job from the database to milliseconds.
     */
    public void parseToSite(final boolean firstStart, final long date) {
        LOG.info("Search started.");
        boolean stopSearch = false;
        int i = 0;
        while (!stopSearch) {
            try {
                Document doc = Jsoup.connect(String.format("%s%s", "http://www.sql.ru/forum/job/", i++))
                        .userAgent(userAgent).get();
                Elements elements = doc.getElementsByClass("postslisttopic");
                Vacancy vacancy;
                for (Element element : elements) {
                    Element elem = element.child(0);
                    String text = elem.text();
                    String ref = elem.attr("href");
                    Matcher matcher = pattern.matcher(text);
                    while (matcher.find()) {
                        vacancy = this.parseToVacancy(text, ref);
                        addVacancyToList(firstStart, date, vacancy);
                        stopSearch = this.check(vacancy.getCreateDate().getYear());
                    }
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        LOG.info("Search Finished.");
        LOG.info(String.format("Pages viewed: %s", i));
        LOG.info(String.format("Vacancies found: %s", this.vacancies.size()));
    }

    /**
     * Add vacancy to list.
     *
     * @param firstStart first start program or not.
     * @param date       Date of the newest job from the database to milliseconds.
     * @param vacancy    vacancy.
     */
    private void addVacancyToList(boolean firstStart, long date, Vacancy vacancy) {
        if (firstStart) {
            if (vacancy.getCreateDate().getYear() >= new Date().getYear()) {
                this.vacancies.add(vacancy);
            }
        } else {
            if (vacancy.getCreateDate().getTime() > date) {
                vacancies.add(vacancy);
            }
        }
    }

    /**
     * Check date to vacancy.
     *
     * @param year Year of posting on the site.
     * @return If year valid returned false.
     */
    private boolean check(final int year) {
        boolean stopSearch = false;
        if (year < new Date().getYear() - 3) {
            stopSearch = true;
        }
        return stopSearch;
    }

    /**
     * Parses out references and getting the body and date of the vacancy.
     *
     * @param text title vacancy.
     * @param ref  link.
     * @return vacancy.
     */
    private Vacancy parseToVacancy(final String text, final String ref) {
        Vacancy vacancy = null;
        try {
            Document doc = Jsoup.connect(ref).userAgent(userAgent).get();
            Element element = doc.getElementsByClass("msgTable").first();
            Element el = element.child(0);
            String body = el.getElementsByClass("msgBody").last().text();
            Date date = this.getDate(el.getElementsByClass("msgFooter").last().text());
            vacancy = new Vacancy(text, body, date);
        } catch (IOException | ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        return vacancy;
    }

    /**
     * Parses the incoming line and receives a date from it.
     *
     * @param string string.
     * @return the date the topic was published.
     * @throws ParseException Signals that an error has been reached unexpectedly while parsing.
     */
    private Date getDate(final String string) throws ParseException {
        final long day = 60 * 60 * 24 * 1000;
        SimpleDateFormat spd = new SimpleDateFormat("dd MMM yy HH:mm");
        long milliseconds;
        String result = string.replaceAll("\u00a0", "").replaceAll(",", "").split("\\[")[0];
        if (result.contains("сегодня")) {
            milliseconds = getTime(result.split(" ")[1]);
        } else if (result.contains("вчера")) {
            milliseconds = getTime(result.split(" ")[1]) - day;
        } else {
            milliseconds = spd.parse(result).getTime();
        }
        return new Date(milliseconds);
    }

    /**
     * Parses the incoming line and receives a time from it.
     *
     * @param s string.
     * @return date to milliseconds.
     */
    private long getTime(final String s) {
        int hour;
        int minute;
        String[] tmp = s.split(":");
        hour = Integer.parseInt(tmp[0]);
        minute = Integer.parseInt(tmp[1]);
        Date currentDate = new Date();
        return new Date(currentDate.getYear() + 1900, currentDate.getMonth(), currentDate.getDate(),
                hour, minute).getTime();
    }

    /**
     * Get.
     *
     * @return list vacancy.
     */
    public List<Vacancy> getVacancies() {
        return vacancies;
    }
}
