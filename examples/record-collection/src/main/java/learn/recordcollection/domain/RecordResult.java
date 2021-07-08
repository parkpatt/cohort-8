package learn.recordcollection.domain;

import learn.recordcollection.models.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordResult {
    private final ArrayList<String> messages = new ArrayList<>();
    private Record record;

    public boolean isSuccess() {
        return messages.size() == 0;
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public Record getRecord() {
        return record;
    }
}
