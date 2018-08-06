package response;

import lombok.Getter;

@Getter
public class Response<T> {
    private int statusCode;
    private String statusText = "";
    private T data;

    public Response<T> withData(T data) {
        this.data = data;
        return this;
    }

    public Response<T> withStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public Response<T> withStatusText(String statusText) {
        this.statusText = statusText;
        return this;
    }
}
