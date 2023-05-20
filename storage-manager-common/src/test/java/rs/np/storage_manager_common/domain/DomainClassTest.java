package rs.np.storage_manager_common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public abstract class DomainClassTest {
	protected DomainClass domainClass;
	//domainClass = new Product(1, "test", 6.5, false, 10, ProductType.Art, new BigDecimal(1000));
	@Test
	@DisplayName("Table name initial test")
	void getTableName() {
		assertFalse(domainClass.getTableName() == null);
		assertFalse(domainClass.getTableName().isBlank());
	}
	
	@Test
	@DisplayName("Column names initial test")
	void getColumnNames() {
		assertFalse(domainClass.getColumnNames() == null);
		assertFalse(domainClass.getColumnNames().isBlank());
	}
	
	@Test
	@DisplayName("Get values initial test")
	void getValuesTest() {
		assertFalse(domainClass.getValues() == null);
		assertFalse(domainClass.getValues().isBlank());
		assertTrue(domainClass.getValues().contains("="));
	}
	
	@Test
	@DisplayName("Get insert values initial test")
	void getInsertValuesTest() {
		assertFalse(domainClass.getInsertValues() == null);
		assertFalse(domainClass.getInsertValues().isBlank());
		assertTrue(domainClass.getInsertValues().contains(")"));
		assertTrue(domainClass.getInsertValues().contains("("));
	}
	
	@Test
	@DisplayName("Set ID test for null values")
	void setIDNull() {
		assertThrows(NullPointerException.class,
				()->domainClass.setID(null));
	}
	
	@CsvSource({
		"-1",
		"1000001"
	})
	@ParameterizedTest
	@DisplayName("Set ID test for illegal argument")
	void setIDIllegalArgument(Integer arg) {
		assertThrows(IllegalArgumentException.class,
				()->domainClass.setID(arg));
	}
	
	@Test
	@DisplayName("Set ID test for a normal value")
	void setIDNormal() {
		domainClass.setID(1);
		assertEquals(1, domainClass.getID());
	}
	
}
