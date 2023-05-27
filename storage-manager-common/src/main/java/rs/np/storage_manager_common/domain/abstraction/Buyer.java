//package domain.abstraction;
package rs.np.storage_manager_common.domain.abstraction;

//import domain.DomainClass;
//import domain.WhereClauseMode;
import rs.np.storage_manager_common.domain.DomainClass;
import rs.np.storage_manager_common.domain.WhereClauseMode;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Domenska klasa koja sadrzi podatke o kupcima.
 * 
 * @author Milan
 * @since 1.0.0
 */
public class Buyer implements DomainClass, Serializable, SecondParticipant {
	/**
	 * privatni staticki atribut, serijski broj generisan na zahtev Serializable interfejsa.
	 */
	private static final long serialVersionUID = -6037762367668771544L;
	/**
	 * protected (zasticeni) atribut jedinstvenog identifikatora kupca kao {@link Integer}
	 */
    protected Integer ID;
    /**
     * privatni atribut adresa kupca kao {@link String}
     */
    private String buyerAddress;
    /**
     * privatni atribut mod po kojem se odredjuje WHERE klauzula u SQL-u
     */
    private WhereClauseMode mode;
    /**
     * get metoda za ID.
     * @return jedinstveni identifikator kao {@link Integer}
     */
    public Integer getID() {
        return ID;
    }
    /**
     * set metoda za ID.
     * @param ID jedinstveni identifikator za kupca kao {@link Integer}
     * @throws NullPointerException ako je uneta vrednost null za ID
     * @throws IllegalArgumentException ako je uneta vrednost ili manja od 0 ili veca od 1000000
     */
    public void setID(Integer ID) {
    	if(ID == null) {
    		throw new NullPointerException("ID cannot be null.");
    	}
    	if(ID < 0 || ID > 1000000) {
    		throw new IllegalArgumentException("ID must be within range of 0 and 1000000.");
    	}
    	
        this.ID = ID;
    }
    /**
     * get metoda za adresu kupca
     * @return adresa kupca kao tip {@link String}
     */
    public String getBuyerAddress() {
        return buyerAddress;
    }
    /**
     * set metoda za adresu kupca
     * @param buyerAddress adresa kupca kao String
     * @throws NullPointerException ako je uneta prazna ili null adresa.
     * @throws IllegalArgumentException ako je uneta adresa kraca od 5 karaktera ili duza od 50.
     */
    public void setBuyerAddress(String buyerAddress) {
    	if(buyerAddress == null || buyerAddress.isBlank()) {
    		throw new NullPointerException("Buyer address cannot be set to null or blank.");
    	}
    	if(buyerAddress.length() < 5 
    			|| buyerAddress.length() > 50) {
    		throw new IllegalArgumentException("Buyer address cannot be set to "
    				+ "a length less than 5 or greater than 50");
    	}
        this.buyerAddress = buyerAddress;
    }
    /**
     * get metoda za mod po kojem se odredjuje WHRERE klauzula u SQL-u
     * @return mod tipa {@link WhereClauseMode}
     */
    public WhereClauseMode getMode() {
        return mode;
    }
    /**
     * set metoda za mod po kojem se odredjuje WHRERE klauzula u SQL-u
     * @param mode tipa {@link WhereClauseMode}
     */
    public void setMode(WhereClauseMode mode) {
    	if(mode == null) {
    		throw new NullPointerException("Mode must be set with this method.");
    	}
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "Buyer{" + "ID=" + ID + ", buyerAddress=" + buyerAddress + ", mode=" + mode + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.ID);
        hash = 79 * hash + Objects.hashCode(this.buyerAddress);
        hash = 79 * hash + Objects.hashCode(this.mode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Buyer other = (Buyer) obj;
        if (!Objects.equals(this.buyerAddress, other.buyerAddress)) {
            return false;
        }
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return this.mode == other.mode;
    }

    @Override
    public String getTableName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getColumnNames() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getInsertValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getColumnsWithoutID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
