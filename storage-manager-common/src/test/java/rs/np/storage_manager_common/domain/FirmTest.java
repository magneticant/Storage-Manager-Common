package rs.np.storage_manager_common.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.google.gson.Gson;

class FirmTest extends DomainClassTest{
	
	@BeforeEach
	void setUp() throws Exception {
		domainClass = new Firm(1, "testFirm", "testFirmAddress");
	}

	@AfterEach
	void tearDown() throws Exception {
		domainClass = null;
	}
	
	@Test
	void setIDNull() {
		assertThrows(NullPointerException.class,
				()->domainClass.setID(null));
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
		domainClass.setID(1);
		assertEquals(1, ((Firm) domainClass).getID());
	}
	
	
	@Test
	void setFirmNameNull() {
		assertThrows(NullPointerException.class,
				()->((Firm)domainClass).setFirmName(null));
	}
	
	@Test
	void setFirmNameEmptyString() {
		assertThrows(NullPointerException.class,
				()->((Firm)domainClass).setFirmName(""));
	}
	
	@CsvSource({
		"abcd",
		"This firm name has got to be more than 30 characters, it's so long!"
	})
	@ParameterizedTest
	void setFirmNameIllegalArgument(String arg) {
		assertThrows(IllegalArgumentException.class,
				()->((Firm)domainClass).setFirmName(arg));
	}
	
	@CsvSource(value = {
			"null",
			"''"
	}, nullValues = {"null"})
	@ParameterizedTest
	void setFirmAddressNull(String arg) {
		assertThrows(NullPointerException.class,
				()->((Firm)domainClass).setFirmName(arg));
	}
	
	@CsvSource({
		"abcdefghi",
		"This firm address has got to be more than 30 characters, it's so long!"
	})
	@ParameterizedTest
	void setFirmAddressIllegalArgument(String arg) {
		assertThrows(IllegalArgumentException.class,
				()->((Firm)domainClass).setFirmAddress(arg));
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
		"firmName1, address test case 1, 1, firmName1, address test case 1, 1, true",
		"firmName1, address test case 1, 1, firmName2, address test case 1, 1, false",
		"firmName1, address test case 1, 1, firmName1, address test case 2, 1, false",
		"firmName1, address test case 1, 1, firmName1, address test case 1, 2, false",
		"firmName1, address test case 1, 1, firmName2, address test case 2, 1, false",
		"firmName1, address test case 1, 1, firmName1, address test case 2, 2, false",
		"firmName1, address test case 1, 1, firmName2, address test case 2, 2, false"
	})
	@ParameterizedTest
	void equalsTestNormal(String firmName1, String addressName1, Integer ID1,
			String firmName2, String addressName2, Integer ID2, boolean result) {
		((Firm)domainClass).setFirmAddress(addressName1);
		((Firm)domainClass).setFirmName(firmName1);
		((Firm)domainClass).setID(ID1);
		
		Firm firm = new Firm(ID2, firmName2, addressName2);
		assertEquals(result, ((Firm)domainClass).equals(firm));
	}
	
	@Test
	void toStringTest() {
		assertEquals("testFirm", domainClass.toString());
	}
	
}
