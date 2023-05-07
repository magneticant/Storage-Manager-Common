package rs.np.storage_manager_common.domain.utility;

import org.apache.commons.lang3.StringEscapeUtils;

public class JSONPurifier {
	public static String removeQuotesAndUnescape(String uncleanJson) {
        String noQuotes = uncleanJson.replaceAll("^\"|\"$", "");

        return StringEscapeUtils.unescapeJava(noQuotes);
    }
}
