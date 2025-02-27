package fr.insa.ms.td.server.config.tdConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class TdConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdConfigServerApplication.class, args);
	}

}
