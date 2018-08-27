package action;

import response.Response;
import response.ResponseGenerator;
import types.CreateRoomRequest;
import types.CreateRoomResponse;
import types.Room;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *  Create Room Handler
 *
 */
public class CreateRoomHandler implements ActionHandler<CreateRoomRequest, CreateRoomResponse> {

    @Override
    public Class<CreateRoomRequest> getRequestType() {
        return CreateRoomRequest.class;
    }

    @Override
    public Class<CreateRoomResponse> getResponseType() {
        return CreateRoomResponse.class;
    }

    @Override
    public void handle(final CreateRoomRequest req, final Consumer<Response<CreateRoomResponse>>toResponse) {
        final String id = req.getId();
        final String host = req.getHost();

        final ArrayList<String> guests = new ArrayList<>();
        guests.add(host);

        final Room room = new Room().withHost(host).withId(id).withGuests(guests);
        dataStore.createRoom(id, room, (ignore) -> toRoomResponse(id, toResponse));
    }

    private void toRoomResponse(final String id, final Consumer<Response<CreateRoomResponse>>toResponse) {
        final CreateRoomResponse resp = new CreateRoomResponse().withId(id);

        toResponse.accept(ResponseGenerator.ok(resp));
    }
}
