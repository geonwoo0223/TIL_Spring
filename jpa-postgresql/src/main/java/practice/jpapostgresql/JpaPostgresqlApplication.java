package practice.jpapostgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JpaPostgresqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaPostgresqlApplication.class, args);
	}

}
