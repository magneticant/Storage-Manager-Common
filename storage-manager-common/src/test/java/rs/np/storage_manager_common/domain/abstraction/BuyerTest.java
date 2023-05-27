package rs.np.storage_manager_common.domain.abstraction;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.google.gson.Gson;

import rs.np.storage_manager_common.domain.WhereClauseMode;

public class BuyerTest {
	
	@BeforeEach
	void setUp() throws Exception {
		buyer = new Buyer();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		buyer = null;
	}
	
	protected Buyer buyer;
	
	@CsvSource(value = {
			"null",
			"''"
	}, nullValues = {"null"})
	@ParameterizedTest
	void setBuyerAddressTestNull(String arg) {
		assertThrows(NullPointerException.class,
				()->buyer.setBuyerAddress(arg));
	}
	
	@CsvSource({
		"abcd",
		"This is a good example of a mockup string that is incredibly long, has no"
		+ " real purpose and is only here to be longer than 50 characters so that the exception is thrown!"
	})
	@ParameterizedTest
	void setBuyerAddressIllegalArgument(String arg) {
		assertThrows(IllegalArgumentException.class,
				()->buyer.setBuyerAddress(arg));
	}
	
	@Test
	void setBuyerAddressNormalString() {
		buyer.setBuyerAddress("buyerAddress");
		assertEquals("buyerAddress", buyer.getBuyerAddress());
	}
	
	@Test
	void setWhereClauseModeTestNull() {
		assertThrows(NullPointerException.class,
				()->buyer.setMode(null));
	}
	
	@Test
	void setWhereClauseModeNormal() {
		buyer.setMode(WhereClauseMode.BY_ID);
		
		assertEquals(WhereClauseMode.BY_ID,
				buyer.getMode());
	}
	
	@Test
	void toStringTest() {
		buyer.setBuyerAddress("buyerAddress");
		buyer.setID(1);
		buyer.setMode(WhereClauseMode.BY_NAME);
		
		if(buyer instanceof Buyer) {
		assertTrue(buyer.toString().contains("buyerAddress"));
		assertTrue(buyer.toString().contains("1"));
		assertTrue(buyer.toString().contains("BY_NAME"));
		}
	}
	
	@Test
	public void equalsTestSameObj() {
		assertTrue(buyer.equals(buyer));
	}
	
	@Test
	public void equalsTestNullObj() {
		assertFalse(buyer.equals(null));
	}
	
	@Test
	public void equalsTestDifferentClass() {
		assertFalse(buyer.equals(new Gson()));
	}
	
	@CsvSource({
		"BY_ID, address test case 1, 1, BY_ID, address test case 1, 1, true",
		"BY_ID, address test case 1, 1, BY_USERNAME_PASSWORD, address test case 1, 1, false",
		"BY_ID, address test case 1, 1, BY_ID, address test case 2, 1, false",
		"BY_ID, address test case 1, 1, BY_ID, address test case 1, 2, false",
		"BY_ID, address test case 1, 1, BY_USERNAME_PASSWORD, address test case 2, 1, false",
		"BY_ID, address test case 1, 1, BY_ID, address test case 2, 2, false",
		"BY_ID, address test case 1, 1, BY_USERNAME_PASSWORD, address test case 2, 2, false"
	})
	@ParameterizedTest
	void equalsTestNormal(String mode1, String addressName1, Integer ID1,
			String mode2, String addressName2, Integer ID2, boolean result) {
		buyer.setMode(WhereClauseMode.valueOf(mode1));
		buyer.setBuyerAddress(addressName1);
		buyer.setID(ID1);
		
		Buyer buyer1 = new Buyer();
		buyer1.setMode(WhereClauseMode.valueOf(mode2));
		buyer1.setBuyerAddress(addressName2);
		buyer1.setID(ID2);
		
		System.out.println(buyer1);
		
		assertEquals(result, buyer1.equals(buyer));
	}
}
