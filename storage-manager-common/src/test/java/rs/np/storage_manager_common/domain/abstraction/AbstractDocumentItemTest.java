package rs.np.storage_manager_common.domain.abstraction;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.google.gson.Gson;

import rs.np.storage_manager_common.domain.DomainClass;
import rs.np.storage_manager_common.domain.Firm;
import rs.np.storage_manager_common.domain.Product;
import rs.np.storage_manager_common.domain.ProductType;
import rs.np.storage_manager_common.domain.WhereClauseMode;

public abstract class AbstractDocumentItemTest {
	protected AbstractDocumentItem item;
	protected AbstractDocumentItem item1;

	@Test
	void setIDNull() {
		assertThrows(NullPointerException.class,
				()->item.setID(null));
	}
	
	@CsvSource({
		"-1",
		"1000001"
	})
	@ParameterizedTest
	void setIDIllegalArgument(Integer arg) {
		assertThrows(IllegalArgumentException.class,
				()->item.setID(arg));
	}
	
	@Test
	void setIDTestNormal() {
		item.setID(1);
		assertEquals(1, item.getID());
	}
	
	@Test
	void setDocumentIDTest() {
		item.setDocumentID(1);
		assertEquals(1,
				item.getDocumentID());
	}
	
	@Test
	void setFirmTest() {
		Firm firm = new Firm(1,"testFirmName","testFirmAddress");
		item.setFirm(firm);
		
		assertEquals(firm, item.getFirm());
	}
	
	@Test
	void setAmountNull() {
		assertThrows(NullPointerException.class, 
				()->item.setAmount(null));
	}
	
	@CsvSource({
		"-1",
		"-2",
		"-3"
	})
	@ParameterizedTest
	void setAmountTest(Integer amount) {
		assertThrows(IllegalArgumentException.class,
				()->item.setAmount(amount));
	}
	
	@Test
	void setAmountNormal() {
		item.setAmount(10);
		assertEquals(10, item.getAmount());
	}
	
	@Test
	void setProductTest() {
		Product product = new Product(1,"prodNameTest",10.0,false,9,ProductType.Dairy, new BigDecimal(59));
		item.setProduct(product);
		
		assertEquals(product, item.getProduct());
	}
	
	@Test
	void alterStockTestNullProduct() {
		assertEquals(0, item.alterStock(0));
	}
	
	@Test
	void equalsTestSameObj() {
		assertTrue(item.equals(item));
	}
	
	@Test
	void equalsTestNullObj() {
		assertFalse(item.equals(null));
	}
	
	@Test
	void equalsTestDifferentClass() {
		assertFalse(item.equals(new Gson()));
	}
	
	@CsvSource({
		"1, 1, 1, 1, true",
		"1, 1, 2, 1, false",
		"1, 1, 1, 2, false",
		"1, 1, 2, 2, false",
	})
	@ParameterizedTest
	void equalsTest(Integer ID1, Integer documentID1,
			Integer ID2, Integer documentID2, boolean result) {
		item.setID(ID1);
		item.setDocumentID(documentID1);
		
		item1.setID(ID2);
		item1.setDocumentID(documentID2);
		
		assertEquals(result, item.equals(item1));
	}
}

