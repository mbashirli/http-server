package dp.ms.httpserver;

import dp.ms.httpserver.config.Configuration;
import dp.ms.httpserver.config.ConfigurationManager;

/**
 * Hello world!
 *
 */
public class HttpServer
{
    public static void main( String[] args )
    {
        System.out.println( "Server starting.. " );
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.printf("Using port: %s, using webroot: %s%n", conf.getPort(), conf.getWebroot());

    }
}
