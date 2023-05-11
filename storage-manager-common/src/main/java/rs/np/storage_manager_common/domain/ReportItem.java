//package domain;
package rs.np.storage_manager_common.domain;

import rs.np.storage_manager_common.domain.utility.DateParser;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class ReportItem implements DomainClass{
    private Integer ID;
//    private Report report;
    private Date reportID;
    private Double productCapacity;
    private Product product;
    private Integer totalAvailableCapacity = 200;
    private WhereClauseMode mode;
    
    public ReportItem() {
    }

    public ReportItem(Integer ID, Date reportID, Double productCapacity, Product product) {
        this.ID = ID;
//        this.report = report;
        this.reportID = reportID;
        this.productCapacity = productCapacity;
        this.product = product;
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

    public Date getReportID() {
        return reportID;
    }

    public void setReportID(Date reportID) {
    	if(reportID == null) {
    		throw new NullPointerException("Date cannot be set to null.");
    	}
    	if(reportID.after(new Date())) {
    		throw new IllegalArgumentException("Report date cannot be set to a future date.");
    	}
        this.reportID = reportID;
    }

    public Double getProductCapacity() {
        return productCapacity;
    }

    public void setProductCapacity(Double productCapacity) {
    	if(productCapacity == null) {
    		throw new NullPointerException("Product capacity cannot be null.");
    	}
    	if(productCapacity < 0 || productCapacity > 100) {
    		throw new IllegalArgumentException("Product capacity can only be set between 0 and 100.");
    	}
        this.productCapacity = productCapacity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
    	if(product == null) {
    		throw new NullPointerException("You may not set product as null.");
    	}
        this.product = product;
    }

    public Integer getTotalAvailableCapacity() {
        return totalAvailableCapacity;
    }

    public void setTotalAvailableCapacity(Integer totalAvailableCapacity) {
    	if(totalAvailableCapacity == null) {
    		throw new NullPointerException("Product capacity cannot be null.");
    	}
    	if(totalAvailableCapacity < 0 
    			|| totalAvailableCapacity > 100) {
    		throw new IllegalArgumentException("Product capacity can only be set between 0 and 100.");
    	}
        this.totalAvailableCapacity = totalAvailableCapacity;
    }

    public void setMode(WhereClauseMode mode) {
    	if(mode == null) {
    		throw new NullPointerException("Mode must be set with this method.");
    	}
        this.mode = mode;
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(ID, mode, product, productCapacity, reportID, totalAvailableCapacity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReportItem other = (ReportItem) obj;
		return Objects.equals(ID, other.ID) && mode == other.mode && Objects.equals(product, other.product)
				&& Objects.equals(productCapacity, other.productCapacity) && Objects.equals(reportID, other.reportID)
				&& Objects.equals(totalAvailableCapacity, other.totalAvailableCapacity);
	}

	

    @Override
	public String toString() {
		return "ReportItem [ID=" + ID + ", reportID=" + reportID + ", productCapacity=" + productCapacity + ", product="
				+ product + ", totalAvailableCapacity=" + totalAvailableCapacity + ", mode=" + mode + "]";
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
                ID + ", datumIzvestaja = " + reportID +
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
        System.out.println("REPORT: " + reportID);
        System.out.println("PRODUCTCAPACITY: " + productCapacity);
        System.out.println("PRODUCTID:" + product.getID());
        return "('" + format.format(reportID) + "', " +
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
//        Report report = new Report();
//        report.setReportDate(
//                DateParser.sqlDateToUtilDate(
//                    rs.getDate("datumIzvestaja"))
//        );
        Product prod = new Product();
        prod.setID(rs.getInt("sifraArtikla"));
        return new ReportItem(
                rs.getInt("sifraStavke"),
                DateParser.sqlDateToUtilDate(
                        rs.getDate("datumIzvestaja")),
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
