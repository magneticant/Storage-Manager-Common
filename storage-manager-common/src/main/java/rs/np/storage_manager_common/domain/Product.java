//package domain;
package rs.np.storage_manager_common.domain;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author Milan
 */
public class Product implements DomainClass {
    private Integer ID;
    private String productName;
    private Double weight;
    private Boolean fragile;
    private Integer amount;
    private ProductType type;
    private BigDecimal price;
    private WhereClauseMode mode;
    
    public Product() {
    }

    public Product(Integer ID, String productName, Double weight, Boolean fragile, Integer amount, ProductType type, BigDecimal price) {
        this.ID = ID;
        this.productName = productName;
        this.weight = weight;
        this.fragile = fragile;
        this.amount = amount;
        this.type = type;
        this.price = price;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer productID) {
        this.ID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean getFragile() {
        return fragile;
    }

    public void setFragile(Boolean fragile) {
        this.fragile = fragile;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public WhereClauseMode getMode() {
        return mode;
    }

    public void setMode(WhereClauseMode mode) {
        this.mode = mode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.ID);
        hash = 97 * hash + Objects.hashCode(this.productName);
        hash = 97 * hash + Objects.hashCode(this.weight);
        hash = 97 * hash + Objects.hashCode(this.fragile);
        hash = 97 * hash + Objects.hashCode(this.amount);
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.price);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.weight, other.weight)) {
            return false;
        }
        if (!Objects.equals(this.fragile, other.fragile)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return Objects.equals(this.price, other.price);
    }

    @Override
    public String getTableName() {
        return "artikal";
    }

    @Override
    public String getColumnNames() {
        return "sifraArtikla, imeArtikla, gramaza, lomljiv, kolicina, tip, cena";
    }

    @Override
    public String getValues() {
        return "sifraArtikla = " + ID + ", imeArtikla = '" + productName +
                "', gramaza = " + weight + ", lomljiv = '" + (fragile == false ? 0 : 1) +
                "', kolicina = " + amount + ", tip = '" + type + 
                "', cena = " + price;
    }

    @Override
    public String getWhereCondition(WhereClauseMode mode) {
        if(mode == WhereClauseMode.BY_NAME)
            return "(imeArtikla LIKE '%" + productName + "%')";
        if(mode == WhereClauseMode.BY_ID)
            return "(sifraArtikla = " + ID + ")";
        
        return "true";
    }

    @Override
    public String getInsertValues() {
      return "('" + productName + "', " + weight + ", " + fragile + ", " +
              amount + ", '" + type + "', " + price + ")";
    }

    @Override
    public String getColumnsWithoutID() {
        return "imeArtikla, gramaza, lomljiv, kolicina, tip, cena";
    }

    @Override
    public DomainClass selectObject(ResultSet rs) throws SQLException {
        return new Product(
                rs.getInt("sifraArtikla"),
                rs.getString("imeArtikla"),
                rs.getDouble("gramaza"),
                rs.getBoolean("lomljiv"),
                rs.getInt("kolicina"),
                ProductType.valueOf(rs.getString("tip")),
                BigDecimal.valueOf(rs.getDouble("cena"))
        );
    }

	@Override
	public String toString() {
		return productName;
	}
    
}
