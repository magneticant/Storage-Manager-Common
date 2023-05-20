//package domain;
package rs.np.storage_manager_common.domain;

/**
 * Enumeracija po kojoj se odredjuje tip uslova za WHERE klauzulu.
 * 
 * @author Milan
 * 
 * @since 1.0.0
 */
public enum WhereClauseMode {
	/**
	 * pretraga po ID-ju
	 */
    BY_ID,
    /**
     * pretraga po nazivu ili delu naziva
     */
    BY_NAME, 
    /**
     * pretraga po korisnickom imenu i lozinki (koristi se za User klasu)
     */
    BY_USERNAME_PASSWORD
}
