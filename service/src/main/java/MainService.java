import io.vertx.core.Vertx;

public class MainService {
    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();

        ServiceVerticle serviceVerticle = new ServiceVerticle();

        vertx.deployVerticle(serviceVerticle, res -> {
            if (res.succeeded()) {
                System.out.println("Deployment id is " + res.result());
            } else {
                System.out.println("Fail: " + res.cause().getMessage());
            }
        });
    }
}
