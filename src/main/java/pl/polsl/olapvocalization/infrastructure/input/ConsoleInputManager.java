package pl.polsl.olapvocalization.infrastructure.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputManager implements InputManager {

    @Override
    public String getInput() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            return reader.readLine();
        } catch (IOException e) {
            return ""; // TODO: refactor
        }
    }
}
