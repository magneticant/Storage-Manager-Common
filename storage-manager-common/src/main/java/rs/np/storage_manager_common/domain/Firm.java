//package domain;
package rs.np.storage_manager_common.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class Firm implements DomainClass{
    private Integer ID;
    private String firmName;
    private String firmAddress;
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
        this.ID = ID;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getFirmAddress() {
        return firmAddress;
    }

    public void setFirmAddress(String firmAddress) {
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
