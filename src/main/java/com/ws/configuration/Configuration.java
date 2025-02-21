package com.ws.configuration;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private Properties properties;

    public Configuration() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Désolé, impossible de trouver le fichier config.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getTampon() {
        return Integer.parseInt(properties.getProperty("tampon", "1024")); // Valeur par défaut si non définie
    }

    public String getCheminFichier() {
        return properties.getProperty("chemin_fichier", "/path/to/default/file.csv"); // Valeur par défaut si non définie
    }
}
