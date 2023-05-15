package rs.np.storage_manager_common.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest extends DomainClassTest {
	
	@BeforeEach
	void setUp() throws Exception {
		domainClass = new User(1, "Milan", "Stankovic", "stanmil_", "123abc");
	}

	@AfterEach
	void tearDown() throws Exception {
		domainClass = null;
	}
	
}
