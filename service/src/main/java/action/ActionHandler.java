package action;

import response.Response;

import java.util.function.Consumer;

public interface ActionHandler <RequestT, ResponseT> {
    Class<RequestT> getRequestType();

    Class<ResponseT> getResponseType();

    void handle(RequestT req, Consumer<Response<ResponseT>>toResponse);
}
