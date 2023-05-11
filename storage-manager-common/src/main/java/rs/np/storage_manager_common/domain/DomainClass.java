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
     * Metoda koja vraca naziv tabele u bazi podataka (za datu domensku klasu), kao {@link String}.
     * @return naziv tabele, {@link String}
     */
    String getTableName();
    /**
     * Metoda koja vraca imena svih kolona date tabele u bazi podataka, kao {@link String}
     * @return sve nabrojane tabele u odgovarajucoj tabeli, spremno za integraciju u SQL upit.
     */
    String getColumnNames();
    /**
     * Metoda koja vraca nazive kolona u tabeli, i vrednosti domenskog objekta (atributa) koje im je 
     * potrebno dodeliti kao novi entry (unos) u bazu podataka.
     * @return vrednosti koje je potrebno dodeliti u tabeli baze, i nazivi kolona baza, zajedno sa zarezima, 
     * znakovima jednakosti, zagradama itd., kao {@link String} vrednost.
     */
    String getValues();
    /**
     * Metoda koja vraca deo koda za SQL upit gde se specificiraju konkretne vrednosti za unos.
     * @return deo koda (snippet) gde se specificiraju konkretne vrednosti za unos, kao {@link String}.
     */
    String getInsertValues();
    /**
     * Metoda koja na osnovu zadatog moda generise kod koji sluzi za WHERE klauzulu.
     * @param mode mod po kojem se generise kod za WHERE klauzulu (npr. BY_ID, BY_USERNAME_PASSWORD). Tip {@link WhereClauseMode}
     * @return deo koda za uslov WHERE, kao {@link String}.
     */
    String getWhereCondition(WhereClauseMode mode);
    /**
     * set metoda za ID.
     * @param id kao {@link Integer}.
     */
    public void setID(Integer id);
    /**
     * Slicna metoda kao getColumnNames ove klase, samo bez ID 
     * @return spisak svih kolona tabele u bazi, unutar zagrada, odvojeni zarezima, u {@link String} formatu.
     */
    String getColumnsWithoutID();
    /**
     * get metoda za mode
     * @return mod za WHERE klauzulu, kao tip {@link WhereClauseMode}.
     */
    public WhereClauseMode getMode();
    /**
     * Metoda kojom se potpomaze SELECT naredba domenske klase. Neophodno je da metoda vrati
     * domenski objekat iz baze podataka prilikom SELECT naredbe, ispunjen relevantnim podacima.
     * @param rs kao Tip {@link ResultSet}, resultSet sa kojim se vrsi prikupljanje podataka iz baze.
     * @return domenska klasa koja se uzima iz baze, tip {@link DomainClass} (konkretni tip koji implementira ovaj interfejs).
     * @throws SQLException u slucaju da je nemoguce izvrsiti prikupljanje podataka iz baze.
     */
    DomainClass selectObject(ResultSet rs) throws SQLException;
}
