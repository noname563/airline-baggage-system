package com.airline.baggage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AirlineBaggageApplication {
    public static void main(String[] args) {
        SpringApplication.run(AirlineBaggageApplication.class, args);
    }

    @Bean
    CommandLineRunner seedData(BaggageRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Baggage(null, "Rahul Sharma", "AI-101", 18.5));
                repository.save(new Baggage(null, "Vaidik Patel", "6E-202", 22.0));
                repository.save(new Baggage(null, "Firdous Warsi", "UK-303", 16.8));
            }
        };
    }
}
