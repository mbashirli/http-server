package dp.ms.httpserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import dp.ms.httpserver.exception.HttpConfigurationException;
import dp.ms.httpserver.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    private static Configuration config;
    private static ConfigurationManager configurationManager;

    private ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        if (configurationManager == null) {
            configurationManager = new ConfigurationManager();
        }
        return configurationManager;
    }

    /*
    *   Used to load a configuration file by the path provided
    * */
    public void loadConfigurationFile(String filePath) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException("File not found: " + filePath);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        while (true) {
            try {
                if ((i = fileReader.read()) == -1) break;
            } catch (IOException e) {
                throw new HttpConfigurationException(e);
            }
            sb.append((char) i);
        }
        JsonNode conf;
        try {
            conf = Json.parse(sb.toString());
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing configuration file.");
        }
        try {
            config = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing configuration file, internal", e) ;
        }
    }

    /*
        Returns the current loaded configuration
     */
    public Configuration getCurrentConfiguration() {
        if (config == null) {
            throw new HttpConfigurationException("No current configuration set.");
        }
        return config;
    }
}
