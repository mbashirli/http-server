package dp.ms.httpserver;

import dp.ms.httpserver.config.Configuration;
import dp.ms.httpserver.config.ConfigurationManager;
import dp.ms.httpserver.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
public class HttpServer
{
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);

    public static void main( String[] args )
    {
        System.out.println( "Server starting.. " );
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        LOGGER.info("Using port: {}", conf.getPort());
        LOGGER.info("Using WebRoot: {}", conf.getWebroot());

        try {
            ServerListenerThread workerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            workerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
            // TODO handle later
        }
    }
}
