package learn.recordcollection.domain;

import learn.recordcollection.models.Record;

import java.util.ArrayList;
import java.util.List;

public class Result<T> {
    private final ArrayList<String> messages = new ArrayList<>();
    private T payload;

    private ResultType resultType = ResultType.SUCCESS;

    public boolean isSuccess() {
        return resultType == ResultType.SUCCESS;
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public void addMessage(String message, ResultType resultType) {
        messages.add(message);
        this.resultType = resultType;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }

    public ResultType getResultType() {
        return resultType;
    }
}
