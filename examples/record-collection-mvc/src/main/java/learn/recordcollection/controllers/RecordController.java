package learn.recordcollection.controllers;

import learn.recordcollection.domain.RecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/records")
public class RecordController {
    private  final RecordService service;

    public RecordController(RecordService service) {
        this.service = service;
    }
}
