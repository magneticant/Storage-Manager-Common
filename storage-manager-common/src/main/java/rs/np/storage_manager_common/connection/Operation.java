//package connection;
package rs.np.storage_manager_common.connection;

import java.io.Serializable;

/**
 *
 * @author Milan
 */
public enum Operation implements Serializable{
    LOGIN, INSERT_PRODUCT, INSERT_NOTE, SELECT_ALL_PRODUCTS,
    SELECT_PRODUCT, DELETE_PRODUCT, SELECT_NOTE, DELETE_NOTE, 
    UPDATE_PRODUCT, INSERT_REPORT, SELECT_ALL_PARTNERS,
    SELECT_ALL_FIRMS, SELECT_ALL_NATURAL_PERSONS,
    SELECT_ALL_LEGAL_PERSONS, SELECT_ALL_PRODUCTS_PARAM,
    INSERT_BILL, SELECT_ALL_REPORTS, SELECT_ALL_REPORTS_PARAM
}
