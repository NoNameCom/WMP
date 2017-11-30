package web.mentor.ru.util;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

public class SettingsUtil  extends Properties{

    public SettingsUtil() {
        loadFile("settings.properties");
    }

    private void loadFile(String file) {
        try {
            load((new ClassPathResource(file)).getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("Error read property file", e);
        }
    }
}
