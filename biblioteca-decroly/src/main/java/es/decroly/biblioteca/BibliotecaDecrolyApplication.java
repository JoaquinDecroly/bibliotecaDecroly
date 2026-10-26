package es.decroly.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class BibliotecaDecrolyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaDecrolyApplication.class, args);
    }

}
