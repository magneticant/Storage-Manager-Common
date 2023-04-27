//package domain.abstraction.implementation;
package rs.np.storage_manager_common.domain.abstraction.implementation;

import rs.np.storage_manager_common.domain.*;
import rs.np.storage_manager_common.domain.abstraction.Buyer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class NaturalPerson extends Buyer {
    private Buyer buyer;
    private String buyerName;
    private String buyerLastName;

    public NaturalPerson() {
    }

    public NaturalPerson(Integer ID, Buyer buyer, String buyerName, String buyerLastName) {
        this.ID = ID;
        this.buyer = buyer;
        this.buyerName = buyerName;
        this.buyerLastName = buyerLastName;
    }
    
    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }

    @Override
    public String toString() {
        return  buyerName + " " + buyerLastName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.buyer);
        hash = 97 * hash + Objects.hashCode(this.buyerName);
        hash = 97 * hash + Objects.hashCode(this.buyerLastName);
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
        final NaturalPerson other = (NaturalPerson) obj;
        if (!Objects.equals(this.buyerName, other.buyerName)) {
            return false;
        }
        if (!Objects.equals(this.buyerLastName, other.buyerLastName)) {
            return false;
        }
        return Objects.equals(this.buyer, other.buyer);
    }
    
    @Override
    public String getTableName() {
        return "fizickoLice";
    }

    @Override
    public String getColumnNames() {
        return "(IDKupca, imeKupca, prezimeKupca)";
    }

    @Override
    public String getValues() {
        return "(IDKupca = " + ID + ", imeKupca = " + buyerName + 
                ", prezimeKupca = " + buyerLastName + ")";
    }

    @Override
    public String getInsertValues() {
        return "(" + buyerName + ", " + buyerLastName + ")";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_ID){
            return "(IDKupca= " + ID + ")";
        }
        return "true";
    }

    @Override
    public String getColumnsWithoutID() {
        return "(imeKupca, prezimeKupca)";
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Buyer buyer = new Buyer();
        buyer.setID(rs.getInt("IDKupca"));
        return new NaturalPerson(
                rs.getInt("IDKupca"),
                buyer,
                rs.getString("imeKupca"),
                rs.getString("prezimeKupca")
        );
    }
    
}
