package action;

import response.Response;
import response.ResponseGenerator;
import store.DataStore;
import types.GetScriptRequest;
import types.GetScriptResponse;
import types.Script;

/**
 *  Get Game Script
 *  Note: This is only a example handler. Need to find an easier way to implement.
 *  Maybe use the WebHandler to solve request later.
 */
public class GetScriptHandler implements ActionHandler<GetScriptRequest, GetScriptResponse> {

    private Script script = DataStore.dataStore.getScript();

    @Override
    public Class<GetScriptRequest> getRequestType() {
        return GetScriptRequest.class;
    }

    @Override
    public Class<GetScriptResponse> getResponseType() {
        return GetScriptResponse.class;
    }

    @Override
    public Response<GetScriptResponse> handle(GetScriptRequest req) {
        System.out.println(req);

        GetScriptResponse resp = new GetScriptResponse()
            .withNpcs(script.getNpcs())
            .withPlayers(script.getPlayers())
            .withScenes(script.getScenes())
            .withSetting(script.getSetting());

        return ResponseGenerator.ok(resp);
    }
}
