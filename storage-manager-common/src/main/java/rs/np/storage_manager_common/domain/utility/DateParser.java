//package domain.utility;
package rs.np.storage_manager_common.domain.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Utility klasa koja sadrzi staticke metode za rad sa datumskim tipovima podataka
 * @author Milan
 * @since 1.0.0
 */
public class DateParser {
	/**
	 * privatni staticki konstantni atribut format, tipa {@link SimpleDateFormat} kojim se definise 
	 * nacin prikaza datuma (koristi se ISO 8601 standard za datume)
	 */
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * javna staticka metoda koja vraca datum kao {@link String}
     * @param date datumska promenljiva tipa {@link Date}
     * @return reprezentaciju datog {@link Date}-a kao {@link String} vrednost
     */
    public static String resolveDateFormat(Date date) {
        return (date == null? "NULL" : format.format(date));
    }
    /**
     * javna staticka metoda koja vraca {@link Date} (java.util) na osnovu {@link java.sql.Date} parametra 
     * (java.sql)
     * @param date datumski tip kao {@link java.sql.Date} (java.sql)
     * @return datum kao {@link Date} (java.util)
     */
    public static java.util.Date sqlDateToUtilDate(java.sql.Date date){
        return new java.util.Date(date.getTime());
    }
}
