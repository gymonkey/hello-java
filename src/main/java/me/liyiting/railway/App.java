package me.liyiting.railway;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.util.Callback;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Server server = new Server();
        ServerConnector serverConnector = new ServerConnector(server, null, null, null, 0, 1,
                new HttpConnectionFactory());
        serverConnector.setPort(8080);
        serverConnector.setIdleTimeout(120_000);
        server.setConnectors(new Connector[]{serverConnector});
        server.setHandler(new AbstractHandler() {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
                response.setStatus(200);
                response.getWriter().write("hello world");
                baseRequest.setHandled(true);
            }
        });
        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
