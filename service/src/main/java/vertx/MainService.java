package vertx;

import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainService {
    public static void main(String[] args) {

        final Vertx vertx = Vertx.vertx();

        final ServiceVerticle serviceVerticle = new ServiceVerticle();

        vertx.deployVerticle(serviceVerticle, res -> {
            if (res.succeeded()) {
                log.info("SUCCEED: Deployment id is {}", res.result());
            } else {
                log.error("FAIL to deploy: {}", res.cause().getMessage());
            }
        });
    }
}
