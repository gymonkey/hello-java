package me.liyiting.railway;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.util.Callback;

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
            public boolean handle(Request request, Response response, Callback callback) throws Exception {
                return false;
            }
        });
        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
