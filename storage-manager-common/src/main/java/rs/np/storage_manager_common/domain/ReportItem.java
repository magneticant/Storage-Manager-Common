//package domain;
package rs.np.storage_manager_common.domain;

import rs.np.storage_manager_common.domain.utility.DateParser;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class ReportItem implements DomainClass{
    private Integer ID;
    private Report report;
    private Double productCapacity;
    private Product product;
    private Integer totalAvailableCapacity = 200;
    private WhereClauseMode mode;
    
    public ReportItem() {
    }

    public ReportItem(Integer ID, Report report, Double productCapacity, Product product) {
        this.ID = ID;
        this.report = report;
        this.productCapacity = productCapacity;
        this.product = product;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Double getProductCapacity() {
        return productCapacity;
    }

    public void setProductCapacity(Double productCapacity) {
        this.productCapacity = productCapacity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getTotalAvailableCapacity() {
        return totalAvailableCapacity;
    }

    public void setTotalAvailableCapacity(Integer totalAvailableCapacity) {
        this.totalAvailableCapacity = totalAvailableCapacity;
    }

    public void setMode(WhereClauseMode mode) {
        this.mode = mode;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.ID);
        hash = 53 * hash + Objects.hashCode(this.report);
        hash = 53 * hash + Objects.hashCode(this.productCapacity);
        hash = 53 * hash + Objects.hashCode(this.product);
        hash = 53 * hash + Objects.hashCode(this.totalAvailableCapacity);
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
        final ReportItem other = (ReportItem) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.report, other.report)) {
            return false;
        }
        if (!Objects.equals(this.productCapacity, other.productCapacity)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return Objects.equals(this.totalAvailableCapacity, other.totalAvailableCapacity);
    }
    
   
    @Override
    public String toString() {
        return "ReportItem{" + "ID=" + ID + ", report=" + report + ", productCapacity=" + productCapacity + ", product=" + product + ", totalAvailableCapacity=" + totalAvailableCapacity + '}';
    }

    @Override
    public String getTableName() {
        return "stavkaizvestaja";
    }

    @Override
    public String getColumnNames() {
        return "kapacitetArt, sifraStavke, datumIzvestaja, sifraArtikla, ukupanKapDostupno";
    }

    @Override
    public String getValues() {
        return "(kapacitetArt = " + productCapacity + ", sifraStavke = " +
                ID + ", datumIzvestaja = " + report == null? null : report.getReportDate() +
                ", sifraArtikla = " + product == null? null : product.getID() + ", ukupanKapDostupno = " +
                totalAvailableCapacity + ")";
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_ID)
        return "(sifraStavke = " + ID + ")"; 
        
        return "true";
    }

    @Override
    public String getInsertValues() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("REPORT: " + report);
        System.out.println("PRODUCTCAPACITY: " + productCapacity);
        System.out.println("PRODUCTID:" + product.getID());
        return "('" + format.format(report.getReportDate()) + "', " +
                Math.round(productCapacity * 100.0) / 100.0 + ", " + product.getID() + 
                ", " + totalAvailableCapacity + ")";
                 
    }

    @Override
    public String getColumnsWithoutID() {
        return "datumIzvestaja, kapacitetArt,sifraArtikla, ukupanKapDostupno";
    }

    
    public Double calculateCapacity() {
        if(product == null)
            return Double.valueOf(100);
        Integer stock = product.getAmount();
        
        return (Double.valueOf(stock)/totalAvailableCapacity)*100;
    }

    @Override
    public WhereClauseMode getMode() {
        return mode;
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        Report report = new Report();
        report.setReportDate(
                DateParser.sqlDateToUtilDate(
                    rs.getDate("datumIzvestaja"))
        );
        Product prod = new Product();
        prod.setID(rs.getInt("sifraArtikla"));
        return new ReportItem(
                rs.getInt("sifraStavke"),
                report,
                rs.getDouble("ukupanKapDostupno"),
                prod
                );
    }
    
    public void setCapacity() {
        if(product == null || product.getAmount()<200){
            return;
        }
        this.totalAvailableCapacity = product.getAmount();
    }

    
    
}
