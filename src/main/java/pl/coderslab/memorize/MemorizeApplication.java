package pl.coderslab.memorize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.coderslab.memorize.dtos.WebAppParamDTO;

@SpringBootApplication
public class MemorizeApplication {

	// common aplication object for settings
	public static WebAppParamDTO webAppParamDTOMain;

	public static void main(String[] args) {
		SpringApplication.run(MemorizeApplication.class, args);
	}

}
