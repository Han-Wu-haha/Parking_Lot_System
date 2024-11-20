import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.SortedMap;

/**
 * The Date class provides utility methods for date formatting, parsing, and calculations.
 * It helps in converting date objects to formatted strings and vice versa according to specified patterns.
 * Additionally, it offers a method to calculate the difference in days between two dates.
 * The class is particularly useful in scenarios where date manipulations are frequent and crucial for business logic.
 *
 * @author Han Wu, whw10@student.unimelb.edu.au, 1468664
 */
 
public class Date {

    // Constants to represent the date and datetime format patterns
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DATE     = "yyyy-MM-dd";
    
    /**
     * Formats the given Date object to a String representation according to the specified pattern.
     * @param date The Date object to be formatted.
     * @param pattern The pattern to format the date string.
     * @return A formatted date string or null if the date object is null.
     */
    public static String getDateFormat(java.util.Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        String str = null;
        sdf.applyPattern(pattern);
        if (date != null) {
            str = sdf.format(date);
        }
        return str;
    }
    
    /**
     * Parses the given date string to a Date object according to the specified pattern.
     * @param strDate The date string to be parsed.
     * @param pattern The pattern to parse the date string.
     * @return A Date object representing the parsed date string or null if the string is null or empty.
     */
    public static java.util.Date parseDateFormat(String strDate, String pattern) {
        java.util.Date date = null;
        if (strDate == null || strDate.trim().length() <= 0) { return date; }
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(pattern);
        try {
            date = sdf.parse(strDate);
        } catch (Exception e) {
            // Exception handling can be enhanced here for specific scenarios.
        }
        return date;
    }
    
    /**
     * Calculates the difference in days between two date strings.
     * @param startDate The start date string.
     * @param endDate The end date string.
     * @return The difference in days between the two dates.
     */
    public static long getDiffDays(String startDate, String endDate) {
        long days = 0;
        DateFormat dft = new SimpleDateFormat(FORMAT_DATE);
        try {
            java.util.Date star = dft.parse(startDate);
            java.util.Date endDay = dft.parse(endDate);
            Long starTime = star.getTime() / 1000;
            Long endTime = endDay.getTime() / 1000;
            long num = endTime - starTime;
            days = num / 24 / 60 / 60;
        } catch (Exception e) {
            // Enhance exception handling here, possibly with logging or user notification.
        }
        return days;
    }
}

