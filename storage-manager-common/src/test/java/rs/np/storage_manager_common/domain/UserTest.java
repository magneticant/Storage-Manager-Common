package rs.np.storage_manager_common.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.google.gson.Gson;

class UserTest extends DomainClassTest {
	
	@BeforeEach
	void setUp() throws Exception {
		domainClass = new User(1, "Milan", "Stankovic", "stanmil_", "123abc");
	}

	@AfterEach
	void tearDown() throws Exception {
		domainClass = null;
	}
	
	@Test
	void setIdTestNull() {
		assertThrows(NullPointerException.class, 
				()-> ((User)domainClass).setID(null));
	}
	
	@CsvSource({
		"-1",
		"1000001"
	})
	@ParameterizedTest
	void setIdTestIllegalArgument(int val) {
		assertThrows(IllegalArgumentException.class,
				()->((User)domainClass).setID(val));
	}
	
	@Test
	void setIdTestNormal() {
		((User)domainClass).setID(10);
		assertEquals(10, ((User)domainClass).getID());
	}
	
	@CsvSource(value = {
		"null",
		"''"
	}, nullValues = {"null"})
	@ParameterizedTest
	void setNameTestNull(String input) {
		assertThrows(NullPointerException.class,
				()->((User)domainClass).setName(input));
	}
	
	@CsvSource({
		"a",
		"Really super long very not short string that is probably longer than 30 characters I hope."
	})
	@ParameterizedTest
	void setNameTestIllegalArgument(String input) {
		assertThrows(IllegalArgumentException.class,
				()->((User)domainClass).setName(input));
	}
	
	@Test
	void setNameTestNormal() {
		((User)domainClass).setName("TestName");
		assertEquals("TestName",
				((User)domainClass).getName());
	}
	
	@Test
	void setLastNameNormal() {
		((User)domainClass).setLastName("TestName");
		assertEquals("TestName",
				((User)domainClass).getLastName());
	}
	
	@CsvSource(value = {
			"null",
			"''"
	}, nullValues= {"null"})
	@ParameterizedTest
	void setUsernameTestNull(String input) {
		assertThrows(NullPointerException.class,
				()->((User)domainClass).setUsername(input));
	}
	
	@CsvSource({
		"a",
		"Really super long very not short string that is probably longer than 30 characters I hope."
	})
	@ParameterizedTest
	void setUsernameIllegalArgument(String input) {
		assertThrows(IllegalArgumentException.class,
				()->((User)domainClass).setUsername(input));
	}
	
	@CsvSource(value = {
			"null",
			"''"
	}, nullValues= {"null"})
	@ParameterizedTest
	void setPasswordTestNull(String input) {
		assertThrows(NullPointerException.class,
				()->((User)domainClass).setPassword(input));
	}
	
	@CsvSource({
		"a",
		"Really super long very not short string that is probably longer than 30 characters I hope."
	})
	@ParameterizedTest
	void setPasswordIllegalArgument(String input) {
		assertThrows(IllegalArgumentException.class,
				()->((User)domainClass).setPassword(input));
	}
	
	@Test
	void setModeNormal() {
		((User)domainClass).setMode(WhereClauseMode.BY_NAME);
		assertEquals(WhereClauseMode.BY_NAME, 
				((User)domainClass).getMode());
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
		"1, password123, username123, 1, password123, username123, true",
		"1, password123, username123, 2, password123, username123, false",
		"1, password123, username123, 1, password456, username123, false",
		"1, password123, username123, 1, password123, username456, false",
		"1, password123, username123, 2, password456, username123, false",
		"1, password123, username123, 2, password123, username456, false",
		"1, password123, username123, 1, password456, username456, false",
		"1, password123, username123, 2, password456, username456, false"
	})
	void equalsTestNormal(Integer ID1, String password1, String username1,
			Integer ID2, String password2, String username2, boolean result) {
		User user = new User();
		user.setID(ID2);
		user.setPassword(password2);
		user.setUsername(username2);
		
		((User)domainClass).setID(ID1);
		((User)domainClass).setPassword(password1);
		((User)domainClass).setUsername(username1);
		
		assertEquals(result,
				((User)domainClass).equals(user));
	}
	
	@Test
	void toStringTest() {
		assertTrue(((User)domainClass).toString().contains("Milan"));
		assertTrue(((User)domainClass).toString().contains("Stankovic"));
		assertTrue(((User)domainClass).toString().contains("stanmil_"));
		assertTrue(((User)domainClass).toString().contains("123abc"));
	}
}
