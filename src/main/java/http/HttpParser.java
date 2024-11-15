package http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpParser {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);

    private static final int SP = 0x20; // 32
    private static final int CR = 0x0D; // 13
    private static final int LF = 0x0A; // 10

    public HttpRequest parseHttpRequest(InputStream inputStream){
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        HttpRequest httpRequest = new HttpRequest();

        //parseHttpRequestLine(reader, httpRequest);
        parseHeaders(reader, httpRequest);
        parseBody(reader, httpRequest);

        return httpRequest;
    }

    private void parseHttpRequestLine(InputStreamReader reader, HttpRequest httpRequest) throws IOException {
        int _byte;
        while ((_byte = reader.read()) >= 0){
            if (_byte == CR){
                _byte = reader.read();
                if (_byte == LF){}

            }
        }
    }

    private void parseHeaders(InputStreamReader reader, HttpRequest httpRequest) {

    }

    private void parseBody(InputStreamReader reader, HttpRequest httpRequest) {

    }
}
