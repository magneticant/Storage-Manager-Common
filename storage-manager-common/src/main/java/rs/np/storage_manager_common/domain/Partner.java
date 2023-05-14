//package domain;
package rs.np.storage_manager_common.domain;

//import domain.abstraction.SecondParticipant;
import rs.np.storage_manager_common.domain.abstraction.SecondParticipant;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Domenska klasa koja predstavlja poslovnog partnera. Sadrzi podatke o partneru.
 * @author Milan
 * @since 1.0.0
 */
public class Partner implements DomainClass, SecondParticipant {
	/**
	 * privatni atribut ID klase {@link Partner}, kao tip {@link Integer}
	 */
    private Integer ID;
    /**
     * privatni atribut ime poslovnog partnera, kao {@link String}
     */
    private String businessPartnerName;
    /**
     * privatni atribut adresa partnera, kao {@link String}
     */
    private String businessPartnerAddress;
    /**
     * privatni atribut koji predstavlja mod za WHERE sql klauzulu, kao enum.
     * Nacin na koji se odredjuje jednakost WHERE klauzule.
     */
    private WhereClauseMode mode;
    /**
     * neparametarski konstruktor
     */
    public Partner() {
    }
    /**
     * all-params konstruktor
     * @param ID identifikator kao {@link Integer}
     * @param businessPartnerName ime poslovnog partnera kao {@link String}
     * @param businessPartnerAddress adresa poslovnog partnera kao {@link String}
     */
    public Partner(Integer ID, String businessPartnerName, String businessPartnerAddress) {
        this.ID = ID;
        this.businessPartnerName = businessPartnerName;
        this.businessPartnerAddress = businessPartnerAddress;
    }
    /**
     * get metoda za ID
     * @return identifikator kao {@link Integer}
     */
    public Integer getID() {
        return ID;
    }
    /**
     * set metoda za identifikator (ID)
     * @param ID {@link Integer} vrednost ID-ja
     * @throws NullPointerException ako je ID unet kao null vrednost.
     * @throws IllegalArgumentException ako je ID unet kao broj manji od 0 ili veci od milion.
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
     * get metoda za ime poslovnog partnera
     * @return ime poslovnog partnera, kao {@link String}.
     */
    public String getBusinessPartnerName() {
        return businessPartnerName;
    }
    /**
     * set metoda za ime poslovnog partnera
     * @param businessPartnerName ime poslovnog partnera u {@link String} formatu.
     * @throws NullPointerException ako je uneta vrednost za dodelu imenu poslovnog partnera ili null ili prazan {@link String}.
     * @throws IllegalArgumentException ako je uneta vrednost za dodelu imenu poslovnog partnera ili manja od 5 karaktera ili veca od 30 karaktera.
     */
    public void setBusinessPartnerName(String businessPartnerName) {
    	if(businessPartnerName == null || businessPartnerName.isEmpty()) {
    		throw new NullPointerException("Business partner name cannot be empty.");
    	}
    	if(businessPartnerName.length() < 5 || businessPartnerName.length() > 30) {
    		throw new IllegalArgumentException("Business partner name cannot be "
    				+ "shorter than 2 or longer than 30 characters.");
    	}
        this.businessPartnerName = businessPartnerName;
    }
    /**
     * get metoda za adresu poslovnog partnera
     * @return adresa poslovnog partnera kao {@link String}.
     */
    public String getBusinessPartnerAddress() {
        return businessPartnerAddress;
    }
    /**
     * set metoda za adresu poslovnog partnera
     * @param businessPartnerAddress adresa poslovnog partnera kao {@link String}
     * @throws NullPointerException ako je uneta vrednost null ili prazan {@link String}.
     * @throws IllegalArgumentException ako je uneta vrednost adrese partnera ili kraca od 5 karaktera ili duza od 30.
     */
    public void setBusinessPartnerAddress(String businessPartnerAddress) {
    	if(businessPartnerAddress == null || businessPartnerAddress.isEmpty()) {
    		throw new NullPointerException("Business partner address cannot be empty.");
    	}
    	if(businessPartnerAddress.length() < 5 || businessPartnerAddress.length() > 30) {
    		throw new IllegalArgumentException("Business partner address cannot be "
    				+ "shorter than 2 or longer than 30 characters.");
    	}
        this.businessPartnerAddress = businessPartnerAddress;
    }
    /**
     * hashCode se racuna za ID, businessPartnerName i businessPartnerAddress
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.ID);
        hash = 19 * hash + Objects.hashCode(this.businessPartnerName);
        hash = 19 * hash + Objects.hashCode(this.businessPartnerAddress);
        return hash;
    }
    /**
     * equals se vrsi po vrednostima ID, businessPartnerName i businessPartnerAddress.
     */
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
        final Partner other = (Partner) obj;
        if (!Objects.equals(this.businessPartnerName, other.businessPartnerName)) {
            return false;
        }
        if (!Objects.equals(this.businessPartnerAddress, other.businessPartnerAddress)) {
            return false;
        }
        return Objects.equals(this.ID, other.ID);
    }
    /**
     * toString metoda vraca samo businessPartnerName vrednost.
     */
    @Override
    public String toString() {
//        return "Partner{" + "ID=" + ID + ", businessPartnerName=" + businessPartnerName + ", businessPartnerAddress=" + businessPartnerAddress + '}';
          return businessPartnerName;
    }

	@Override
	public String getTableName() {
        return "partner";
    }

	@Override
	public String getColumnNames() {
        return "(IDPartnera, nazivFirmePart, adresaPP)";
    }

	@Override
	public String getValues() {
        return "(IDPatnera = " + ID + ", nazivFirmePart = " + businessPartnerName + ", adresaPP = " + businessPartnerAddress + ")";
    }

	@Override
	public String getInsertValues() {
        return "(" + businessPartnerName + ", " + businessPartnerAddress + ")";
    }

	@Override
	public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_ID)
        return "(IDPartnera=" + ID + ")";
        else 
            return "true";
    }

	@Override
	public String getColumnsWithoutID() {
        return "(nazivFirmePart, adresaPP)";
    }

	@Override
	public WhereClauseMode getMode() {
        return mode;
    }

	@Override
	public DomainClass selectObject(ResultSet rs) throws SQLException {
        return new Partner(
                rs.getInt("IDPartnera"), 
                rs.getString("nazivFirmePart"), 
                rs.getString("adresaPP"));
    }
}
