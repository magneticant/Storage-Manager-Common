package rs.np.storage_manager_common.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductTest extends DomainClassTest{

	@BeforeEach
	void setUp() throws Exception {
		domainClass = new Product(1, "test", 6.5, false, 10, ProductType.Art, new BigDecimal(1000));
	}

	@AfterEach
	void tearDown() throws Exception {
		domainClass = null;
	}

}
