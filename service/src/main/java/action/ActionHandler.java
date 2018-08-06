package action;

import io.vertx.ext.web.RoutingContext;

public interface ActionHandler {
    void handle(RoutingContext req);
}
