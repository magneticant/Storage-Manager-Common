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
public class GoodsReceivedNote extends AbstractDocument implements DomainClass {
    private Partner partner;
    
    public GoodsReceivedNote() {
        items = new ArrayList<>();
    }

    public GoodsReceivedNote(Integer ID, Firm firm, Partner partner, Date issueDate, Date Deadline, BigDecimal totalCost) {
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

    @Override
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
        final GoodsReceivedNote other = (GoodsReceivedNote) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.firm, other.firm)) {
            return false;
        }
        if (!Objects.equals(this.partner, other.partner)) {
            return false;
        }
        if (!Objects.equals(this.issueDate, other.issueDate)) {
            return false;
        }
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        return Objects.equals(this.Deadline, other.Deadline);
    }

    @Override
    public String toString() {
        return "ID:" + this.ID + " IDFirme: " + this.firm.getID() +
                " IDPartnera: " + this.partner.getID() + 
                " datumIzdavanja: " + this.issueDate +
                " datumValute: " + this.Deadline + ";";
    }

    @Override
    public String getTableName() {
        return "prijemnica";
    }

    @Override
    public String getColumnNames() {
        return "(IDPrijemnice, IDFirme, IDPartnera, datumIzdavanjaP, datumValuteP, totalnaCena)";
    }

    @Override
    public String getValues() {
        return "(IDPrijemnice = " + ID + ", IDFirme = " + firm == null? null : firm.getID() +
               ", IDPartnera = " + partner == null? null : partner.getID() + ",datumIzdavanjaP = "+
               issueDate + ", datumValuteP = " + Deadline + ", totalnaCena = " + totalCost + ")";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_ID)
        return "(IDPrijemnice = " + ID + ")";
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
        return "(IDFirme, IDPartnera, datumIzdavanjaP, datumValuteP, totalnaCena)";
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
