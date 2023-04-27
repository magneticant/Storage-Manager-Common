//package domain.abstraction.implementation;
package rs.np.storage_manager_common.domain.abstraction.implementation;

import rs.np.storage_manager_common.domain.*;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocument;
import rs.np.storage_manager_common.domain.abstraction.AbstractDocumentItem;
import rs.np.storage_manager_common.domain.utility.DateParser;

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
public class Invoice extends AbstractDocument implements DomainClass {
    private Partner partner;
    
    public Invoice() {
        items = new ArrayList<>();
    }

    public Invoice(Integer ID, Firm firm, Partner partner, Date issueDate, Date Deadline, BigDecimal totalCost) {
        items = new ArrayList<>();
        this.ID = ID;
        this.firm = firm;
        this.partner = partner;
        this.issueDate = issueDate;
        this.Deadline = Deadline;
        this.totalCost = totalCost;
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
    public Firm getFirm() {
        return firm;
    }

    @Override
    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    @Override
    public Partner getSecondParticipant() {
        return partner;
    }

    public void setSecondParticipant(DomainClass partner) {
        this.partner = (Partner) partner;
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
    public void setDeadLine(Date Deadline) {
        this.Deadline = Deadline;
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
    public List<AbstractDocumentItem> getItems() {
        return items;
    }

    @Override
    public void setItems(List<AbstractDocumentItem> items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.ID);
        hash = 41 * hash + Objects.hashCode(this.firm);
        hash = 41 * hash + Objects.hashCode(this.partner);
        hash = 41 * hash + Objects.hashCode(this.issueDate);
        hash = 41 * hash + Objects.hashCode(this.Deadline);
        hash = 41 * hash + Objects.hashCode(this.totalCost);
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
        final Invoice other = (Invoice) obj;
        return Objects.equals(this.partner, other.partner);
    }

    @Override
    public String toString() {
        return Deadline.toString();
    }

    @Override
    public String getTableName() {
        return "narudzbenica";
    }

    @Override
    public String getColumnNames() {
        return "(IDNarudzbenice, IDFirme, IDPartnera, datumIzdavanjaN, datumValuteN, totalnaCena)";
    }

    @Override
    public String getValues() {
        return "(IDNarudzbenice = " + ID + ", IDFirme = " + firm == null? null : firm.getID() +
               ", IDPartnera = " + partner == null? null : partner.getID() + ",datumIzdavanjaN = "+
               issueDate + ", datumValuteN = " + Deadline + ", totalnaCena = " + totalCost + ")";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_ID)
        return "(IDNarudzbenice = " + ID + ")";
        return "true";
    }

    @Override
    public String getInsertValues() {
        return "(" + (firm == null ? "NULL" : firm.getID()) + ", " + (partner == null ? "NULL" : partner.getID()) +
                ", '" + DateParser.resolveDateFormat(issueDate) + "', '"
                + DateParser.resolveDateFormat(Deadline) + "', " + totalCost + ")";
    }

    @Override
    public String getColumnsWithoutID() {
        return "(IDFirme, IDPartnera, datumIzdavanjaN, datumValuteN, totalnaCena)";
    }

    @Override
    public WhereClauseMode getMode() {
        return mode;
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
