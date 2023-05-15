package rs.np.storage_manager_common.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FirmTest extends DomainClassTest{
	
	@BeforeEach
	void setUp() throws Exception {
		domainClass = new Firm(1, "testFirm", "testFirmAddress");
	}

	@AfterEach
	void tearDown() throws Exception {
		domainClass = null;
	}

}
