//package domain;
package rs.np.storage_manager_common.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Domenska klasa koja predstavlja (nasu/e) firmu. Ona sadrzi relevantne podatke za izradu poslovnih dokumenata.
 * @author Milan
 * 
 * @since 1.0.0
 */
public class Firm implements DomainClass{
	/**
	 * privatni atribut ID klase Firm, kao tip Integer
	 */
    private Integer ID;
    /**
     * privatni atribut ime firme, kao String
     */
    private String firmName;
    /**
     * privatni atribut adresa firme, kao String
     */
    private String firmAddress;
    /**
     * privatni atribut koji predstavlja mod za WHERE sql klauzulu, kao enum.
     * Nacin na koji se odredjuje jednakost WHERE klauzule.
     */
    private WhereClauseMode mode;
    
    public Firm() {
    }

    public Firm(Integer ID, String firmName, String firmAddress) {
        this.ID = ID;
        this.firmName = firmName;
        this.firmAddress = firmAddress;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
    	if(ID == null) {
    		throw new NullPointerException("ID cannot be null.");
    	}
    	if(ID < 0 || ID > 1000000) {
    		throw new IllegalArgumentException("ID must be within range of 0 and 1000000.");
    	}
        this.ID = ID;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
    	if(firmName == null || firmName.isEmpty()) {
    		throw new NullPointerException("Firm name cannot be empty.");
    	}
    	if(firmName.length() < 2 || firmName.length() > 30) {
    		throw new IllegalArgumentException("Firm name cannot be shorter"
    				+ " than 2 characters or longer than 30 characters.");
    	}
        this.firmName = firmName;
    }

    public String getFirmAddress() {
        return firmAddress;
    }

    public void setFirmAddress(String firmAddress) {
    	if(firmAddress == null || firmAddress.isEmpty()) {
    		throw new NullPointerException("Address cannot be null or blank.");
    	}
    	if(firmAddress.length() < 2 || firmAddress.length()>30) {
    		throw new IllegalArgumentException("Address cannot be shorter "
    				+ "than 2 characters or longer than 30 characters.");
    	}
        this.firmAddress = firmAddress;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.ID);
        hash = 67 * hash + Objects.hashCode(this.firmName);
        hash = 67 * hash + Objects.hashCode(this.firmAddress);
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
        final Firm other = (Firm) obj;
        if (!Objects.equals(this.firmName, other.firmName)) {
            return false;
        }
        if (!Objects.equals(this.firmAddress, other.firmAddress)) {
            return false;
        }
        return Objects.equals(this.ID, other.ID);
    }

    @Override
    public String toString() {
//        return "Firm{" + "ID=" + ID + ", firmName=" + firmName + ", firmAddress=" + firmAddress + '}';
          return firmName;
    }

	@Override
	public String getTableName() {
		return "firma";
	}

	@Override
	public String getColumnNames() {
		return "(IDFirme, nazivFirme, adresaFirme)";
	}

	@Override
	public String getValues() {
		return "(IDFirme = " + ID + ", nazivFirme = " + firmName + ", adresaFirme = " + firmAddress + ")";
	}

	@Override
	public String getInsertValues() {
		return "(" + firmName + ", " + firmAddress + ")";
	}

	@Override
	public String getWhereCondition(WhereClauseMode mode) {
		if(mode == WhereClauseMode.BY_ID)
	        return "(IDFirme=" + ID + ")";
	        else 
	            return "true";
	}

	@Override
	public String getColumnsWithoutID() {
		return "(nazivFirme, adresaFirme)";
	}

	@Override
	public WhereClauseMode getMode() {
		return mode;
	}

	@Override
	public DomainClass selectObject(ResultSet rs) throws SQLException {
		return new Firm(rs.getInt("IDFirme"),
              rs.getString("nazivFirme"),
              rs.getString("adresaFirme"));
	}

}
