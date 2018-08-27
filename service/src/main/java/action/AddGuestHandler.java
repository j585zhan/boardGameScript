package action;

import response.Response;
import response.ResponseGenerator;
import types.AddGuestRequest;
import types.AddGuestResponse;

import java.util.List;
import java.util.function.Consumer;

/**
 *  Add Guest Handler
 *
 */
public class AddGuestHandler implements ActionHandler<AddGuestRequest, AddGuestResponse> {

    @Override
    public Class<AddGuestRequest> getRequestType() {
        return AddGuestRequest.class;
    }

    @Override
    public Class<AddGuestResponse> getResponseType() {
        return AddGuestResponse.class;
    }

    @Override
    public void handle(final AddGuestRequest req, final Consumer<Response<AddGuestResponse>>toResponse) {
        final String roomId = req.getRoomId();
        final String name = req.getName();

        dataStore.addGuestToRoom(roomId, name, (guests) -> toAddGuestResponse(guests, toResponse));
    }

    private void toAddGuestResponse(final List<String> guests, final Consumer<Response<AddGuestResponse>>toResponse) {
        final AddGuestResponse resp = new AddGuestResponse().withGuests(guests);

        toResponse.accept(ResponseGenerator.ok(resp));
    }
}
