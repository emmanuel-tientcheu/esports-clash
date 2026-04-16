package com.example.esport_clash;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(PostgreSQLTestConfiguration.class)
@SpringBootTest
class EsportClashApplicationTests {

	@Test
	void contextLoads() {
	}

}
