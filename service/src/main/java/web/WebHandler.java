package web;

import action.ActionHandler;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import response.Response;
import response.ResponseGenerator;

public class WebHandler<RequestT, ResponseT> {

    private ActionHandler<RequestT, ResponseT> actionHandler;

    public WebHandler(ActionHandler<RequestT, ResponseT> actionHandler) {
        this.actionHandler = actionHandler;
    }
    public void handle(RoutingContext req) {
        RequestT reqData;

        try {
            reqData = Json.decodeValue(req.getBody(), actionHandler.getRequestType());
        } catch (final Exception e) {
            Response<ResponseT> resp = ResponseGenerator.badRequest();
            response(req, resp, "Server could not understand request.");
            return;
        }

        Response<ResponseT> resp = actionHandler.handle(reqData);
        String respData = Json.encode(resp.getData());
        response(req, resp, respData);
    }

    private void response(RoutingContext req, Response<ResponseT> resp, String respData) {
        req
            .response()
            .setStatusCode(resp.getStatusCode())
            .setStatusMessage(resp.getStatusText())
            .end(respData);
    }
}
