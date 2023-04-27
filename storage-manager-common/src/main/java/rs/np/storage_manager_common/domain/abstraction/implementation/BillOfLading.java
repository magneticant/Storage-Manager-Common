//package domain.abstraction.implementation;
package rs.np.storage_manager_common.domain.abstraction.implementation;

import rs.np.storage_manager_common.domain.*;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocument;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocumentItem;
import rs.np.storage_manager_common.domain.abstraction.Buyer;
import rs.np.storage_manager_common.domain.utility.DateParser;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class BillOfLading extends AbstractDocument implements DomainClass, Serializable {
    private Buyer buyer;
    
    public BillOfLading() {
        items = new ArrayList<>();
    }

    public BillOfLading(Integer ID, Buyer buyer, Firm firm, Date issueDate, Date dueDate, BigDecimal totalPrice, WhereClauseMode mode) {
        this.ID = ID;
        this.buyer = buyer;
        this.firm = firm;
        this.issueDate = issueDate;
        this.Deadline = dueDate;
        this.totalCost = totalPrice;
        this.mode = mode;
        items = new ArrayList<>();
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public DomainClass getSecondParticipant() {
        return buyer;
    }

    @Override
    public void setSecondParticipant(DomainClass buyer) {
        this.buyer = (Buyer)buyer;
    }

    @Override
    public Firm getFirm() {
        return firm;
    }

    @Override
    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    @Override
    public Date getIssueDate() {
        return issueDate;
    }

    @Override
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public Date getDeadLine() {
        return Deadline;
    }
    
    @Override
    public void setDeadLine(Date date) {
        Deadline = date;
    }

    @Override
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    @Override
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public WhereClauseMode getMode() {
        return mode;
    }

    public void setMode(WhereClauseMode mode) {
        this.mode = mode;
    }

    @Override
    public List<AbstractDocumentItem> getItems() {
        return items;
    }

    @Override
    public void setItems(List<AbstractDocumentItem> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.ID);
        hash = 19 * hash + Objects.hashCode(this.buyer);
        hash = 19 * hash + Objects.hashCode(this.firm);
        hash = 19 * hash + Objects.hashCode(this.issueDate);
        hash = 19 * hash + Objects.hashCode(super.Deadline);
        hash = 19 * hash + Objects.hashCode(super.totalCost);
        hash = 19 * hash + Objects.hashCode(this.mode);
        hash = 19 * hash + Objects.hashCode(this.items);
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
        final BillOfLading other = (BillOfLading) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.buyer, other.buyer)) {
            return false;
        }
        if (!Objects.equals(this.firm, other.firm)) {
            return false;
        }
        if (!Objects.equals(this.issueDate, other.issueDate)) {
            return false;
        }
        if (!Objects.equals(this.Deadline, other.Deadline)) {
            return false;
        }
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        if (this.mode != other.mode) {
            return false;
        }
        return Objects.equals(this.items, other.items);
    }

    @Override
    public String toString() {
        return "BillOfLading{" + "ID=" + ID + ", buyer=" + buyer + ", firm=" + firm;
    }
    
    @Override
    public String getTableName() {
        return "otpremnica";
    }

    @Override
    public String getColumnNames() {
        return "IDOtpremnice, IDKupca, IDFirme, datumIzdavanja, datumValute, ukupanIznos";
    }

    @Override
    public String getValues() {
        return "IDOtpremnice = " + ID + ", IDKupca = " + (getSecondParticipant() == null ? "NULL" : buyer.getID()) + 
                ", IDFirme = " + (firm == null? "NULL" : firm.getID()) + ", datumIzdavanja = " + 
        DateParser.resolveDateFormat(issueDate) + ", datumValute = " + DateParser.resolveDateFormat(Deadline) 
                + ", ukupanIznos = " + totalCost;
    }

    @Override
    public String getInsertValues() {
        
        return "(" + (buyer == null? "NULL" : buyer.getID()) + ", " +
               (firm == null? "NULL" : firm.getID()) + ", '" +
                DateParser.resolveDateFormat(issueDate) + "', '" + 
                DateParser.resolveDateFormat(Deadline) + "', " +
                String.valueOf(totalCost) + ")";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        switch(mode){
            case BY_ID:
                return "(IDOtpremnice = " + ID + ")";
            case BY_NAME:
            case BY_USERNAME_PASSWORD:
                return "true";
            default: return "true";
        }
    }

    @Override
    public String getColumnsWithoutID() {
        return "IDKupca, IDFirme, datumIzdavanja, datumValute, ukupanIznos";
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
