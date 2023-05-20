package rs.np.storage_manager_common.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.google.gson.Gson;

class ProductTest extends DomainClassTest{

	@BeforeEach
	void setUp() throws Exception {
		domainClass = new Product(1, "test", 6.5, false, 10, ProductType.Art, new BigDecimal(1000));
	}

	@AfterEach
	void tearDown() throws Exception {
		domainClass = null;
	}
	
	@CsvSource({
		"-1",
		"1000001"
	})
	@ParameterizedTest
	void setIDIllegalArgument(Integer arg) {
		assertThrows(IllegalArgumentException.class,
				()->domainClass.setID(arg));
	}
	
	@Test
	void setIDNormal() {
		((Product)domainClass).setID(1);
		assertEquals(1, ((Product) domainClass).getID());
	}
	
	@CsvSource(value= {
			"null",
			"''"
	}, nullValues = {"null"})
	@ParameterizedTest
	void setProductNameNull(String input) {
		assertThrows(NullPointerException.class,
				()-> ((Product)domainClass).setProductName(input));
	}
	
	@CsvSource({
		"abcdefghijklmnopqrstuvwxyz12345",
		"This product name is a very long String and it's longer than 30 characters"
	})
	@ParameterizedTest
	void setProductNameIllegalArgument(String input) {
		assertThrows(IllegalArgumentException.class,
				()-> ((Product)domainClass).setProductName(input));
	}
	
	@Test
	void setProductNameNormal() {
		((Product)domainClass).setProductName("ProductName1");
		assertEquals("ProductName1", ((Product)domainClass).getProductName());
	}
	
	@Test
	void setWeightNull() {
		assertThrows(NullPointerException.class, 
				()->((Product)domainClass).setWeight(null));
	}
	
	@Test
	void setWeightNormal() {
		((Product)domainClass).setWeight(6.9);
		assertEquals(6.9, ((Product)domainClass).getWeight());
	}
	
	@Test
	void setFragileNull() {
		assertThrows(NullPointerException.class, 
				()->((Product)domainClass).setFragile(null));
	}
	
	@Test
	void setFragileNormal() {
		((Product)domainClass).setFragile(false);
		assertFalse(((Product)domainClass).getFragile());
	}
	
	@Test
	void setAmountNull() {
		assertThrows(NullPointerException.class,
				()->((Product)domainClass).setAmount(null));
	}
	
	@CsvSource({
		"-1",
		"-1000",
		"-2"
	})
	@ParameterizedTest
	void setAmountIllegalArgument(int arg) {
		assertThrows(IllegalArgumentException.class
				,()->((Product)domainClass).setAmount(arg));
	}
	
	@Test
	void setAmountNormal() {
		((Product)domainClass).setAmount(6);
		assertEquals(6, ((Product)domainClass).getAmount());
	}
	
	@Test
	void setTypeNull() {
		assertThrows(NullPointerException.class,
				()->((Product)domainClass).setType(null));
	}
	
	@Test
	void setTypeNormal() {
		((Product)domainClass).setType(ProductType.Art);
		assertEquals(ProductType.Art, 
				((Product)domainClass).getType());
	}
	
	@Test
	void setPriceNull() {
		assertThrows(NullPointerException.class,
				()->((Product)domainClass).setPrice(null));
	}
	
	@CsvSource({
		"-1",
		"-2",
		"-3"
	})
	@ParameterizedTest
	void setPriceIllegalArgument(int arg) {
		assertThrows(IllegalArgumentException.class,
				()->((Product)domainClass).setPrice(new BigDecimal(arg)));
	}
	
	@Test
	void setPriceNormal() {
		((Product)domainClass).setPrice(new BigDecimal(10));
		assertEquals(new BigDecimal(10), ((Product)domainClass).getPrice());
	}
	
	@Test
	void setModeNull() {
		assertThrows(NullPointerException.class,
				()->((Product)domainClass).setMode(null));
	}
	
	@Test
	void setModeNormal() {
		((Product)domainClass).setMode(WhereClauseMode.BY_ID);
		assertEquals(WhereClauseMode.BY_ID,
				((Product)domainClass).getMode());
	}
	
	@Test
	void equalsTestSameObj() {
		assertTrue(domainClass.equals(domainClass));
	}
	
	@Test
	void equalsTestNullObj() {
		assertFalse(domainClass.equals(null));
	}
	
	@Test
	void equalsTestDifferentClass() {
		assertFalse(domainClass.equals(new Gson()));
	}
	
	@CsvSource({
		"1, productNum1, 1, productNum1, true",
		"1, productNum1, 2, productNum1, false",
		"1, productNum1, 1, productNum2, false",
		"1, productNum1, 2, productNum2, false"
	})
	@ParameterizedTest
	void equalsTestNormal(Integer ID1, String name1, 
			Integer ID2, String name2, boolean result) {
		((Product)domainClass).setProductName(name1);
		((Product)domainClass).setID(ID1);
		
		Product product = new Product();
		product.setID(ID2);
		product.setProductName(name2);
		
		assertEquals(result, product.equals(domainClass));
	}
	
	@Test
	void toStringTest() {
		assertEquals("test", ((Product)domainClass).getProductName());
	}
}
