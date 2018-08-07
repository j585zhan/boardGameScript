package action;

import org.junit.Test;
import types.GetScriptRequest;
import types.GetScriptResponse;

import static org.junit.Assert.assertEquals;

public class ScriptTest {
    @Test
    public void testRequestType() {
        GetScriptHandler getScriptHandler = new GetScriptHandler();
        assertEquals(getScriptHandler.getRequestType(), GetScriptRequest.class);
    }

    @Test
    public void testResponseType() {
        GetScriptHandler getScriptHandler = new GetScriptHandler();
        assertEquals(getScriptHandler.getResponseType(), GetScriptResponse.class);
    }
}
