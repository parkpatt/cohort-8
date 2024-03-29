package learn.cubicles.domain;

import learn.cubicles.models.Cubicle;

import java.util.ArrayList;
import java.util.List;

public class CubicleResult {
    private final ArrayList<String> messages = new ArrayList<>();
    private Cubicle payload;

    public boolean isSuccess() {
        return messages.size() == 0;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public Cubicle getPayload() {
        return payload;
    }

    public void setPayload(Cubicle payload) {
        this.payload = payload;
    }
}
