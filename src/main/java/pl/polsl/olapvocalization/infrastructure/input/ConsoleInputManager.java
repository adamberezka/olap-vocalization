package pl.polsl.olapvocalization.infrastructure.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;

public class ConsoleInputManager implements InputManager {

    @Override
    public String getInput() {
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (final IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
