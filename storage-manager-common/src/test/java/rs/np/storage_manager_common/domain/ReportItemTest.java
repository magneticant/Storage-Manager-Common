package rs.np.storage_manager_common.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.google.gson.Gson;


class ReportItemTest extends DomainClassTest{
	private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@BeforeEach
	void setUp() throws Exception {
		Product product = new Product(2, "prodTest", 10.0, false, 1, ProductType.Carpets, new BigDecimal(10));
		domainClass = new ReportItem(1, sdf.parse("2022-01-01"), 10.0, product);
	}

	@AfterEach
	void tearDown() throws Exception {
		domainClass = null;
	}
	
	@Test
	void setIdTestNull() {
		assertThrows(NullPointerException.class, 
				()-> ((ReportItem)domainClass).setID(null));
	}
	
	@CsvSource({
		"-1",
		"1000001"
	})
	@ParameterizedTest
	void setIdTestIllegalArgument(int val) {
		assertThrows(IllegalArgumentException.class,
				()->((ReportItem)domainClass).setID(val));
	}
	
	@Test
	void setIdTestNormal() {
		((ReportItem)domainClass).setID(10);
		assertEquals(10, ((ReportItem)domainClass).getID());
	}

	@Test
	void setProductCapacityTestNull() {
		assertThrows(NullPointerException.class,
				()->((ReportItem)domainClass).setProductCapacity(null));
	}
	
	@CsvSource({
		"-1",
		"101"
	})
	@ParameterizedTest
	void setProductCapacityTestIllegalArgument(double value) {
		assertThrows(IllegalArgumentException.class,
				()->((ReportItem)domainClass).setProductCapacity(value));
	}
	
	@Test
	void setProductCapacityTestNormal() {
		((ReportItem)domainClass).setProductCapacity(15.0);
		assertEquals(15.0, ((ReportItem)domainClass).getProductCapacity());
	}
	
	@Test
	void setProductTestNull() {
		assertThrows(NullPointerException.class,
				()->((ReportItem)domainClass).setProduct(null));
	}
	
	@Test
	void setProductTestNormal() {
		Product p1 = new Product(1,"testProd",6.9,true,10,ProductType.Art, new BigDecimal(10));
		((ReportItem)domainClass).setProduct(p1);
		
		assertEquals(p1, ((ReportItem)domainClass).getProduct());
	}
	
	@Test
	void setTotalAvailableCapacityTestNull() {
		assertThrows(NullPointerException.class,
				()->((ReportItem)domainClass).setTotalAvailableCapacity(null));
	}
	
	@CsvSource({
		"-1",
		"50000"
	})
	void setTotalAvailableCapacityTestIllegalArgument(Integer cap) {
		assertThrows(IllegalArgumentException.class,
				()->((ReportItem)domainClass).setTotalAvailableCapacity(cap));
	}
	
	@Test
	void setTotalAvailableCapacityTestNormal() {
		((ReportItem)domainClass).setTotalAvailableCapacity(10);
		assertEquals(10, ((ReportItem)domainClass).getTotalAvailableCapacity());
	}
	
	@Test
	void setModeTestNull() {
		assertThrows(NullPointerException.class,
				()->((ReportItem)domainClass).setMode(null));
	}
	
	@Test
	void setModeTestNormal() {
		((ReportItem)domainClass).setMode(WhereClauseMode.BY_ID);
		assertEquals(WhereClauseMode.BY_ID, ((ReportItem)domainClass).getMode());
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
		"1, 2010-01-01, 1, 2010-01-01, true",
		"1, 2010-01-01, 1, 2010-01-02, false",
		"1, 2010-01-01, 2, 2010-01-01, false",
		"1, 2010-01-01, 2, 2010-01-02, false",
	})
	@ParameterizedTest
	void equalsTestNormal(Integer ID1, String reportID1,
			Integer ID2, String reportID2, boolean result) {
		
		try {
		
			ReportItem item = new ReportItem();
			item.setID(ID2);
			item.setReportID(sdf.parse(reportID2));
		
			((ReportItem)domainClass).setID(ID1);
			((ReportItem)domainClass).setReportID(sdf.parse(reportID1));
			
			assertEquals(result,((ReportItem)domainClass).equals(item));
			
		} catch(ParseException ex) {
			ex.printStackTrace();
			fail("Test has thrown an exception.");
		}
	}
	
	@Test
	void toStringTest() {
		assertTrue(((ReportItem)domainClass).toString().contains("1"));
		assertTrue(((ReportItem)domainClass).toString().contains("2022"));
		assertTrue(((ReportItem)domainClass).toString().contains("01"));
		assertTrue(((ReportItem)domainClass).toString().contains("prodTest"));
		assertTrue(((ReportItem)domainClass).toString().contains("10.0"));
	}
}
