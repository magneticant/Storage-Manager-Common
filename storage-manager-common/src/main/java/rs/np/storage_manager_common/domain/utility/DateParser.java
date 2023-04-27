//package domain.utility;
package rs.np.storage_manager_common.domain.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Milan
 */
public class DateParser {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    
    public static String resolveDateFormat(Date date) {
        return (date == null? "NULL" : format.format(date));
    }
    
    public static java.util.Date sqlDateToUtilDate(java.sql.Date date){
        return new java.util.Date(date.getTime());
    }
}
