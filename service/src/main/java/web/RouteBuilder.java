package web;

import action.ActionHandler;
import action.GetScriptHandler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.Getter;

@Getter
public class RouteBuilder {

    private Router router;

    public RouteBuilder(final Vertx vertx) {
        router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        Route("/get", new GetScriptHandler());
    }

    private <Req, Resp> void Route(final String path, final ActionHandler<Req, Resp> action) {
        WebHandler<Req, Resp> webHandler = new WebHandler<>(action);
        router.route(path).handler(webHandler::handle);
    }
}
