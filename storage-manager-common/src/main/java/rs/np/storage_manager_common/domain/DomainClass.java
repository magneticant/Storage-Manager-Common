//package domain;
package rs.np.storage_manager_common.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Domenski interfejs koji implementiraju sve domenske klase projekta.
 * U ovom domenskom interfejsu se nalaze standardne metode za rad sa generickim repozitorijumom.
 * 
 * @author Milan
 * 
 * @since 1.0.0
 */
public interface DomainClass extends Serializable{
    /**
     * Metoda koja vraca naziv tabele u bazi podataka (za datu domensku klasu), kao String.
     * @return naziv tabele, String
     */
    String getTableName();
    /**
     * Metoda koja vraca imena svih kolona date tabele u bazi podataka, kao String
     * @return sve nabrojane tabele u odgovarajucoj tabeli, spremno za integraciju u SQL upit.
     */
    String getColumnNames();
    /**
     * Metoda koja vraca nazive kolona u tabeli, i vrednosti domenskog objekta (atributa) koje im je 
     * potrebno dodeliti kao novi entry (unos) u bazu podataka.
     * @return vrednosti koje je potrebno dodeliti datoj klasi 
     */
    String getValues();
    String getInsertValues();
    String getWhereCondition(WhereClauseMode mode);
    public void setID(Integer id);
    String getColumnsWithoutID();
    public WhereClauseMode getMode();
    DomainClass selectObject(ResultSet rs) throws SQLException;
}
