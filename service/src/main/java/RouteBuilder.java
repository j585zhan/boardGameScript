import action.ActionHandler;
import action.GetScriptHandler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class RouteBuilder {

    private Router router;

    private GetScriptHandler getSceneHandler = new GetScriptHandler();

    public RouteBuilder(final Vertx vertx) {
        router = Router.router(vertx);
    }

    public Router getRouter() {
        Route("/get", getSceneHandler);
        return router;
    }

    private void Route(final String path, final ActionHandler action) {
        router.get(path).handler(action::handle); // TODO: maybe change to webHandler
    }
}
