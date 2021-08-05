package learn.recordcollection.controllers;

import learn.recordcollection.domain.RecordService;
import learn.recordcollection.domain.Result;
import learn.recordcollection.domain.ResultType;
import learn.recordcollection.models.Condition;
import learn.recordcollection.models.Record;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {

    private final RecordService service;

    public RecordController(RecordService service) {
        this.service = service;
    }

    @GetMapping
    public List<Record> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Record record) {
        Result<Record> result = service.add(record);
        if (result.getResultType() == ResultType.INVALID) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }
}
