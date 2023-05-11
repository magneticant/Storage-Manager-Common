//package domain;
package rs.np.storage_manager_common.domain;

//import domain.abstraction.SecondParticipant;
import rs.np.storage_manager_common.domain.abstraction.SecondParticipant;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class Partner implements DomainClass, SecondParticipant {
    private Integer ID;
    private String businessPartnerName;
    private String businessPartnerAddress;
    private WhereClauseMode mode;
    
    public Partner() {
    }

    public Partner(Integer ID, String businessPartnerName, String businessPartnerAddress) {
        this.ID = ID;
        this.businessPartnerName = businessPartnerName;
        this.businessPartnerAddress = businessPartnerAddress;
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

    public String getBusinessPartnerName() {
        return businessPartnerName;
    }

    public void setBusinessPartnerName(String businessPartnerName) {
    	if(businessPartnerName == null || businessPartnerName.isEmpty()) {
    		throw new NullPointerException("Business partner name cannot be empty.");
    	}
    	if(businessPartnerName.length() < 2 || businessPartnerName.length() > 30) {
    		throw new IllegalArgumentException("Business partner name cannot be "
    				+ "shorter than 2 or longer than 30 characters.");
    	}
        this.businessPartnerName = businessPartnerName;
    }

    public String getBusinessPartnerAddress() {
        return businessPartnerAddress;
    }

    public void setBusinessPartnerAddress(String businessPartnerAddress) {
    	if(businessPartnerAddress == null || businessPartnerAddress.isEmpty()) {
    		throw new NullPointerException("Business partner address cannot be empty.");
    	}
    	if(businessPartnerAddress.length() < 2 || businessPartnerAddress.length() > 30) {
    		throw new IllegalArgumentException("Business partner address cannot be "
    				+ "shorter than 2 or longer than 30 characters.");
    	}
        this.businessPartnerAddress = businessPartnerAddress;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.ID);
        hash = 19 * hash + Objects.hashCode(this.businessPartnerName);
        hash = 19 * hash + Objects.hashCode(this.businessPartnerAddress);
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
        final Partner other = (Partner) obj;
        if (!Objects.equals(this.businessPartnerName, other.businessPartnerName)) {
            return false;
        }
        if (!Objects.equals(this.businessPartnerAddress, other.businessPartnerAddress)) {
            return false;
        }
        return Objects.equals(this.ID, other.ID);
    }

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
