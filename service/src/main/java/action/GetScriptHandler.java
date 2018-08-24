package action;

import response.Response;
import response.ResponseGenerator;
import store.DataStore;
import types.GetScriptRequest;
import types.GetScriptResponse;
import types.Script;

import java.util.function.Consumer;

/**
 *  Get Game Script
 *
 */
public class GetScriptHandler implements ActionHandler<GetScriptRequest, GetScriptResponse> {
    final DataStore dataStore = DataStore.dataStore;

    @Override
    public Class<GetScriptRequest> getRequestType() {
        return GetScriptRequest.class;
    }

    @Override
    public Class<GetScriptResponse> getResponseType() {
        return GetScriptResponse.class;
    }

    @Override
    public void handle(final GetScriptRequest req, Consumer<Response<GetScriptResponse>>toResponse) {
        final String id = req.getId();

        dataStore.getScript(id, script -> toScriptResponse(script, toResponse));
    }

    private void toScriptResponse(final Script script, Consumer<Response<GetScriptResponse>>toResponse) {
        final GetScriptResponse resp = new GetScriptResponse()
            .withNpcs(script.getNpcs())
            .withPlayers(script.getPlayers())
            .withScenes(script.getScenes())
            .withSetting(script.getSetting());

        toResponse.accept(ResponseGenerator.ok(resp));
    }
}
