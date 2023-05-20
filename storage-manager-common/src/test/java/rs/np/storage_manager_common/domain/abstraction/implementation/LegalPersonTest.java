package rs.np.storage_manager_common.domain.abstraction.implementation;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.google.gson.Gson;

import rs.np.storage_manager_common.domain.Report;
import rs.np.storage_manager_common.domain.WhereClauseMode;
import rs.np.storage_manager_common.domain.abstraction.Buyer;
import rs.np.storage_manager_common.domain.abstraction.BuyerTest;

class LegalPersonTest {
	private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Gson gson = new Gson();
	private LegalPerson legalPerson;
	
	@BeforeEach
	void setUp() throws Exception {
		legalPerson = new LegalPerson();
	}

	@AfterEach
	void tearDown() throws Exception {
		legalPerson = null;
	}

	@Test
	void setBuyerTestNull() {
		assertThrows(NullPointerException.class,
				()->legalPerson.setBuyer(null));
	}
	
	@Test
	void setBuyerTest() {
		Buyer buyer = new Buyer();
		buyer.setID(1);
		buyer.setBuyerAddress("new buyer address");
		buyer.setMode(WhereClauseMode.BY_ID);
		
		legalPerson.setBuyer(buyer);
		
		assertEquals(buyer, legalPerson.getBuyer());
	}
	
	@CsvSource(value={
		"null",
		"''"
	}, nullValues = {"null"})
	void setFirmNameTestNull(String input) {
		assertThrows(NullPointerException.class,
				()->legalPerson.setFirmName(input));
	}
	
	@CsvSource({
		"a",
		"This is a good example of a long string that is over 30 characters in length!"
	})
	@ParameterizedTest
	void setFirmNameTestIllegalArgument(String input) {
		assertThrows(IllegalArgumentException.class,
				()->legalPerson.setFirmName(input));
	}
	
	@Test
	void setFirmNameTest() {
		legalPerson.setFirmName("firmName");
		assertEquals("firmName", 
				legalPerson.getFirmName());
	}
	
	@CsvSource(value={
			"2069-03-12",
			"3010-01-13",
		})
		@ParameterizedTest
		void setFoundingDateWrongDateTime(String input) {
			assertThrows(DateTimeException.class,
					()-> legalPerson.setFoundingDate(sdf.parse(input)));
		}
		
		@Test
		void setFoundingDateNormal() {
			try{
				legalPerson.setFoundingDate(sdf.parse("2010-01-01"));
			}catch(ParseException ex) { }
			assertEquals("2010-01-01", sdf.format(legalPerson.getFoundingDate()));
		}
		
		@Test
		void equalsTestSameObj() {
			assertTrue(legalPerson.equals(legalPerson));
		}
		
		@Test
		void equalsTestNullObj() {
			assertFalse(legalPerson.equals(null));
		}
		
		@Test
		void equalsTestDifferentClass() {
			assertFalse(legalPerson.equals(new Gson()));
		}
		
		@CsvSource({
			"firmName1, {\"ID\":1,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2020-01-01\",firmName1, {\"ID\":1,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2020-01-01\", true",
			"firmName2, {\"ID\":1,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2020-01-01\",firmName1, {\"ID\":1,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2020-01-01\", false",
			"firmName1, {\"ID\":2,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2020-01-01\",firmName1, {\"ID\":1,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2020-01-01\", false",
			"firmName1, {\"ID\":1,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2021-01-01\",firmName1, {\"ID\":1,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2020-01-01\", false",
			"firmName2, {\"ID\":2,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2020-01-01\",firmName1, {\"ID\":1,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2020-01-01\", false",
			"firmName1, {\"ID\":2,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2021-01-01\",firmName1, {\"ID\":1,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2020-01-01\", false",
			"firmName2, {\"ID\":1,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2021-01-01\",firmName1, {\"ID\":1,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2020-01-01\", false",
			"firmName2, {\"ID\":2,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2021-01-01\",firmName1, {\"ID\":1,\"buyerAddress\":\"address123\", \"mode\":\"BY_ID\"}, \"2020-01-01\", false",
		})
		@ParameterizedTest
		void equalsTestWithCSVSource(String name1, String buyer1JSON, String date1,
				String name2, String buyer2JSON, String date2, boolean result) {
			try {
				legalPerson.setFirmName(name1);
				legalPerson.setBuyer(gson.fromJson(buyer1JSON, Buyer.class));
				legalPerson.setFoundingDate(sdf.parse(date1));
				
				LegalPerson legalPerson1 = new LegalPerson();
				legalPerson1.setFirmName(name2);
				legalPerson1.setBuyer(gson.fromJson(buyer2JSON, Buyer.class));
				legalPerson1.setFoundingDate(sdf.parse(date2));
				
				assertEquals(result, legalPerson.equals(legalPerson1));
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		@Test
		void toStringTest() {
			legalPerson.setFirmName("new firm name");
			assertTrue(legalPerson.toString().contains("new firm name"));
		}
}
