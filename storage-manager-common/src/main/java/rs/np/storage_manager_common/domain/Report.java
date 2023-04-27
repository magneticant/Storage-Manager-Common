//package domain;
package rs.np.storage_manager_common.domain;

//import domain.utility.DateParser;
import rs.np.storage_manager_common.domain.utility.DateParser;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class Report implements DomainClass{
    private Date reportDate;
    private Double totalCapacity;
    List<ReportItem> reportItems;
    private WhereClauseMode mode;
            
    public Report() {
        reportItems = new ArrayList<>();
        this.totalCapacity = Double.valueOf("100");
        
    }

    public Report(Date reportDate, double totalCapacity) {
        reportItems = new ArrayList<>();
        this.reportDate = reportDate;
        this.totalCapacity = totalCapacity;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public double getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(double totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public List<ReportItem> getReportItems() {
        return reportItems;
    }

    public void setReportItems(List<ReportItem> reportItems) {
        this.reportItems = reportItems;
    }

    public void setTotalCapacity(Double totalCapacity) {
        this.totalCapacity = totalCapacity;
    }
    
    public void setMode(WhereClauseMode mode) {
        this.mode = mode;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.reportDate);
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.totalCapacity) ^ (Double.doubleToLongBits(this.totalCapacity) >>> 32));
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
        final Report other = (Report) obj;
        if (Double.doubleToLongBits(this.totalCapacity) != Double.doubleToLongBits(other.totalCapacity)) {
            return false;
        }
        return Objects.equals(this.reportDate, other.reportDate);
    }

    @Override
    public String toString() {
        return "Cap: " + totalCapacity + " Items: " +
                (reportItems == null? "0" : reportItems.size());
    }

    @Override
    public String getTableName() {
        return "izvestaj";
    }

    @Override
    public String getColumnNames() {
        return "datumIzvestaja, ukupanKap";
    }

    @Override
    public String getValues() {
        return "(datumIzvestaja = " + reportDate + 
                ", ukupanKap = " + totalCapacity + ")";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_ID)
        return "(datumIzvestaja = '" + DateParser.resolveDateFormat(reportDate) + "')";
        return "true";
    }

    @Override
    public void setID(Integer id) {
        
    }

    @Override
    public String getInsertValues() {
        return "('" + DateParser.resolveDateFormat(reportDate) + 
                "', " + totalCapacity + ")";
    }

    @Override
    public String getColumnsWithoutID() {
        return "(ukupanKap)";
    }

    @Override
    public WhereClauseMode getMode() {
        return mode;
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        return new Report(
                DateParser.sqlDateToUtilDate(
                rs.getDate("datumizvestaja")),
                rs.getDouble("ukupanKap")
            );
    }

    

}
