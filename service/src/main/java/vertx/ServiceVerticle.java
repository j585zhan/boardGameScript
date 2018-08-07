package vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;
import web.RouteBuilder;

@Slf4j
public class ServiceVerticle extends AbstractVerticle {

    public static final String HOST = "localhost";
    public static final int PORT = 8080;

    private HttpServer server;

    @Override
    public void start() {
        Router router = new RouteBuilder(vertx).getRouter();

        server = vertx.createHttpServer().requestHandler(router::accept);

        String prop = System.getenv("PORT");
        int port = PORT;
        String host = HOST;

        if (prop != null) {
            port = Integer.valueOf(prop);
            host = "0.0.0.0";
        }

        server.listen(port, host, res -> {
            if (res.succeeded()) {
                log.info("Listening on {}:{}", HOST, PORT);
            } else {
                log.error("Fail to Listening on {}:{}", HOST, PORT);
            }
        });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
