package action;

import response.Response;

public interface ActionHandler <RequestT, ResponseT> {
    Class<RequestT> getRequestType();

    Class<ResponseT> getResponseType();

    Response<ResponseT> handle(RequestT req);
}
