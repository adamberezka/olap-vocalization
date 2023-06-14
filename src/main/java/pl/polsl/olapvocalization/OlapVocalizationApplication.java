package pl.polsl.olapvocalization;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.polsl.olapvocalization.infrastructure.VOOLProcessor;

@SpringBootApplication
@RequiredArgsConstructor
public class OlapVocalizationApplication implements CommandLineRunner {

    private final VOOLProcessor voolProcessor;

    public static void main(String[] args) {
        SpringApplication.run(OlapVocalizationApplication.class, args);
    }

    @Override
    public void run(String... args) {
        voolProcessor.run();
    }
}
