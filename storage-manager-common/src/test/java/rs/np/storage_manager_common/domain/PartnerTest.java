package rs.np.storage_manager_common.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.google.gson.Gson;

class PartnerTest extends DomainClassTest{

	@BeforeEach
	void setUp() throws Exception {
		domainClass = new Partner(1, "businessPartnerNameTest", "businessPartnerAddressTest");
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
		assertEquals(1, ((Partner) domainClass).getID());
	}
	
	@CsvSource(value = {
			"null",
			"''"
	}, nullValues = {"null"})
	@ParameterizedTest
	void setBusinessPartnerNameNull(String arg) {
		assertThrows(NullPointerException.class,
				()->((Partner)domainClass).setBusinessPartnerName(arg));
	}
	
	@CsvSource({
		"a",
		"This string has got to be over 30 characters in length!"
	})
	@ParameterizedTest
	void setBusinessPartnerNameIllegalArgument(String arg) {
		assertThrows(IllegalArgumentException.class,
				()->((Partner)domainClass).setBusinessPartnerName(arg));
	}
	
	@Test
	void setBusinessPartnerNameNormal() {
		((Partner)domainClass).setBusinessPartnerName("partner1");
		assertEquals("partner1",
				((Partner)domainClass).getBusinessPartnerName());
	}
	
	@CsvSource(value = {
			"null",
			"''"
	}, nullValues = {"null"})
	@ParameterizedTest
	void setBusinessPartnerAddressNull(String arg) {
		assertThrows(NullPointerException.class,
				()->((Partner)domainClass).setBusinessPartnerAddress(arg));
	}
	
	@CsvSource({
		"abcd",
		"This string has got to be at least 31 characters, perhaps even longer than that!"
	})
	@ParameterizedTest
	void setBusinessPartnerAddressIllegalArgument(String arg) {
		assertThrows(IllegalArgumentException.class,
				()->((Partner)domainClass).setBusinessPartnerAddress(arg));
	}
	
	@Test
	void setBusinessPartnerAddressNormal() {
		((Partner)domainClass).setBusinessPartnerAddress("partner1 - address");
		assertEquals("partner1 - address",
				((Partner)domainClass).getBusinessPartnerAddress());
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
		"partnerName1, address test case 1, 1, partnerName1, address test case 1, 1, true",
		"partnerName1, address test case 1, 1, partnerName2, address test case 1, 1, false",
		"partnerName1, address test case 1, 1, partnerName1, address test case 2, 1, false",
		"partnerName1, address test case 1, 1, partnerName1, address test case 1, 2, false",
		"partnerName1, address test case 1, 1, partnerName2, address test case 2, 1, false",
		"partnerName1, address test case 1, 1, partnerName1, address test case 2, 2, false",
		"partnerName1, address test case 1, 1, partnerName2, address test case 2, 2, false"
	})
	@ParameterizedTest
	void equalsTestNormal(String partnerName1, String partnerAddress1, Integer ID1,
			String partnerName2, String partnerAddress2, Integer ID2, boolean result) {
		((Partner)domainClass).setBusinessPartnerName(partnerName1);;
		((Partner)domainClass).setBusinessPartnerAddress(partnerAddress1);
		((Partner)domainClass).setID(ID1);
		
		Partner partner = new Partner(ID2, partnerName2, partnerAddress2);
		assertEquals(result, ((Partner)domainClass).equals(partner));
	}
	
	@Test
	void toStringTest() {
		assertEquals("businessPartnerNameTest", ((Partner)domainClass).getBusinessPartnerName());
	}
}
