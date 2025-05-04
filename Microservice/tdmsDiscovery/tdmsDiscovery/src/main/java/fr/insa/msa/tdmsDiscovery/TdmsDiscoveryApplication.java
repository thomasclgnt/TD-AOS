package fr.insa.msa.tdmsDiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TdmsDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TdmsDiscoveryApplication.class, args);
	}

}
