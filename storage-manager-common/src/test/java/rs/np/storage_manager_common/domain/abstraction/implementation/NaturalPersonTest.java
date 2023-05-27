package rs.np.storage_manager_common.domain.abstraction.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.google.gson.Gson;

import rs.np.storage_manager_common.domain.WhereClauseMode;
import rs.np.storage_manager_common.domain.abstraction.Buyer;

class NaturalPersonTest{
	private NaturalPerson naturalPerson;
	private Gson gson = new Gson();

	
	@BeforeEach
	void setUp() throws Exception {
		naturalPerson = new NaturalPerson();
	}

	@AfterEach
	void tearDown() throws Exception {
		naturalPerson = null;
	}

	@Test
	void setBuyerTestNull() {
		assertThrows(NullPointerException.class,
				()->naturalPerson.setBuyer(null));
	}
	
	@Test
	void setBuyerTest() {
		Buyer buyer = new Buyer();
		buyer.setID(1);
		buyer.setBuyerAddress("new buyer address");
		buyer.setMode(WhereClauseMode.BY_ID);
		
		naturalPerson.setBuyer(buyer);
		
		assertEquals(buyer, naturalPerson.getBuyer());
	}
	
	@CsvSource(value = {
			"null",
			"''"
		}, nullValues = {"null"})
		@ParameterizedTest
		void setNameTestNull(String input) {
			assertThrows(NullPointerException.class,
					()->naturalPerson.setBuyerName(input));
		}
		
		@CsvSource({
			"a",
			"Really super long very not short string that is probably longer than 30 characters I hope."
		})
		@ParameterizedTest
		void setNameTestIllegalArgument(String input) {
			assertThrows(IllegalArgumentException.class,
					()->naturalPerson.setBuyerName(input));
		}
		
		@Test
		void setNameTestNormal() {
			naturalPerson.setBuyerName("TestName");
			assertEquals("TestName",
					naturalPerson.getBuyerName());
		}
		
		@CsvSource(value = {
				"null",
			"''"
		}, nullValues = {"null"})
		@ParameterizedTest
		void setLastNameTestNull(String input) {
				assertThrows(NullPointerException.class,
						()->naturalPerson.setBuyerLastName(input));
		}
			
		@CsvSource({
			"a",
			"Really super long very not short string that is probably longer than 30 characters I hope."
		})
		@ParameterizedTest
		void setLastNameTestIllegalArgument(String input) {
			assertThrows(IllegalArgumentException.class,
					()->naturalPerson.setBuyerLastName(input));
		}
			
		@Test
		void setLastNameTestNormal() {
			naturalPerson.setBuyerLastName("TestName");
			assertEquals("TestName",
					naturalPerson.getBuyerLastName());
		}
			
		@Test
		void toStringTest() {
			naturalPerson.setBuyerName("Milan");
			naturalPerson.setBuyerLastName("Stankovic");
			assertTrue(naturalPerson.toString().contains("Milan"));
			assertTrue(naturalPerson.toString().contains("Stankovic"));
		}
		
		@Test
		void equalsTestSameObj() {
			assertTrue(naturalPerson.equals(naturalPerson));
		}
		
		@Test
		void equalsTestNullObj() {
			assertFalse(naturalPerson.equals(null));
		}
		
		@Test
		void equalsTestDifferentClass() {
			assertFalse(naturalPerson.equals(new Gson()));
		}
		
		@CsvSource(value ={
		    "firstName1|{\"ID\":1,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName1|firstName1|{\"ID\":1,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName1|true",
		    "firstName2|{\"ID\":1,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName1|firstName1|{\"ID\":1,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName1|false",
		    "firstName1|{\"ID\":2,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName1|firstName1|{\"ID\":1,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName1|false",
		    "firstName1|{\"ID\":1,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName2|firstName1|{\"ID\":1,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName1|false",
		    "firstName2|{\"ID\":2,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName1|firstName1|{\"ID\":1,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName1|false",
		    "firstName1|{\"ID\":2,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName2|firstName1|{\"ID\":1,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName1|false",
		    "firstName2|{\"ID\":1,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName2|firstName1|{\"ID\":1,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName1|false",
		    "firstName2|{\"ID\":2,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName2|firstName1|{\"ID\":1,\"buyerAddress\":\"address123\",\"mode\":\"BY_ID\"}|lastName1|false"
		}, delimiter = '|')
		@ParameterizedTest
		void equalsTestWithCSVSource(String name1, String buyer1JSON, String lastName1,
				String name2, String buyer2JSON, String lastName2, boolean result) {
			try {
				naturalPerson.setBuyerName(name1);
				naturalPerson.setBuyer(gson.fromJson(buyer1JSON, Buyer.class));
				naturalPerson.setBuyerLastName(lastName1);
				
				NaturalPerson naturalPerson1 = new NaturalPerson();
				naturalPerson1.setBuyerName(name2);
				naturalPerson1.setBuyer(gson.fromJson(buyer2JSON, Buyer.class));
				naturalPerson1.setBuyerLastName(lastName2);
				
				assertEquals(result, naturalPerson.equals(naturalPerson1));
			}catch(Exception ex) {
				ex.printStackTrace();
				fail("Test has thrown an exception");
			}
		}
}
