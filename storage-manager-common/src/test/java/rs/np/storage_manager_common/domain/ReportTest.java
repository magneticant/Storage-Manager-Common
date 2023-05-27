package rs.np.storage_manager_common.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.google.gson.Gson;

class ReportTest extends DomainClassTest{
	private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@BeforeEach
	void setUp() throws Exception {
		Product product = new Product(2, "prodTest", 10.0, false, 1, ProductType.Carpets, new BigDecimal(10));
		ReportItem reportItem = new ReportItem(1, sdf.parse("2022-01-01"), 10.0, product);
		List<ReportItem> items = Arrays.asList(reportItem);
		domainClass = new Report(sdf.parse("2022-01-01"), 88.9, items);
	}

	@AfterEach
	void tearDown() throws Exception {
		domainClass = null;
	}
	
	@CsvSource(value={
		"2069-03-12",
		"3010-01-13"
	}, nullValues = "null")
	@ParameterizedTest
	void setReportDateWrongDateTime(String input) {
		assertThrows(DateTimeException.class,
				()-> ((Report)domainClass).setReportDate(sdf.parse(input)));
	}
	
	@Test
	void setReportDateNormal() {
		try{
			((Report)domainClass).setReportDate(sdf.parse("2010-01-01"));
		}catch(ParseException ex) {
			ex.printStackTrace();
			fail("Test has thrown an exception");
		}
		assertEquals("2010-01-01", sdf.format(((Report)domainClass).getReportDate()));
	}
	
	@CsvSource({
		"-1",
		"101"
	})
	void setTotalCapacityIllegalArgument(double arg) {
		assertThrows(IllegalArgumentException.class,
				()->((Report)domainClass).setTotalCapacity(arg));
	}
	
	@Test
	void setTotalCapacityNormal() {
		((Report)domainClass).setTotalCapacity(50.0);
		assertEquals(50.0,
				((Report)domainClass).getTotalCapacity());
	}
	
	@Test
	void setReportItemsNull() {
		assertThrows(NullPointerException.class,
				()->((Report)domainClass).setReportItems(null));
	}
	
	@Test
	void setReportItemsNormal() {
		try {
			ReportItem i1 = new ReportItem(2,sdf.parse("2022-01-01"),20.0, new Product());
			List<ReportItem> listOfItems = Arrays.asList(i1);
			((Report)domainClass).setReportItems(listOfItems);
			assertEquals(listOfItems,
					((Report)domainClass).getReportItems());
		} catch (ParseException e) {}
	}
	
	@Test
	void setModeNull() {
		assertThrows(NullPointerException.class
				, ()->((Report)domainClass).setMode(null));
	}
	
	@Test
	void setModeNormal() {
		((Report)domainClass).setMode(WhereClauseMode.BY_ID);
		assertEquals(WhereClauseMode.BY_ID,
				((Report)domainClass).getMode());
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
		"2020-03-03, 50.0, 2020-03-03, 50.0, true",
		"2020-03-03, 50.0, 2020-03-04, 50.0, false",
		"2020-03-03, 50.0, 2020-03-03, 51.0, false",
		"2020-03-03, 50.0, 2020-03-04, 51.0, false"
	})
	@ParameterizedTest
	void equalsTestNormal(String date1, double cap1,
			String date2, double cap2, boolean res) {
		try {
			((Report)domainClass).setReportDate(sdf.parse(date1));
			((Report)domainClass).setTotalCapacity(cap1);
			
			Report report = new Report(sdf.parse(date2), cap2);
			
			assertEquals(res,((Report)domainClass).equals(report));
		}catch(ParseException ex) {}
	}
	
	@Test
	void testToString() {
		try {
		((Report)domainClass).setTotalCapacity(50);
		((Report)domainClass).setReportDate(sdf.parse("2010-01-01"));
		ReportItem ri = new ReportItem(3,sdf.parse("2010-01-01"),19.0, new Product());
		List<ReportItem> list = Arrays.asList(ri);
		((Report)domainClass).setReportItems(list);
		assertTrue(((Report)domainClass).toString().contains("50"));
		assertTrue(((Report)domainClass).toString().contains("1"));
		}catch(ParseException ex) {}
	}
}
