package action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import store.DataStore;
import types.Script;

/**
 *  Get Game Script
 *  Note: This is only a example handler. Need to find an easier way to implement.
 *  Maybe use the WebHandler to solve request later.
 */
public class GetScriptHandler implements ActionHandler {

    private Script script = DataStore.dataStore.getScript();

    @Override
    public void handle(RoutingContext req) {
        ObjectMapper mapper = new ObjectMapper();
        String resp = "";
        try {
            resp = mapper.writeValueAsString(script);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JsonObject json = new JsonObject(resp);
        req.response().setStatusCode(200).end(json.encode());
    }
}
