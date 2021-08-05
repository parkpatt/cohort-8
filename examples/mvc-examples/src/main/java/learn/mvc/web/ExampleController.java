package learn.mvc.web;

import learn.mvc.models.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExampleController {

    @GetMapping("/")
    public String HelloWorld() {
        return "Hello World";
    }

    @GetMapping("/{petId}")
    public ResponseEntity<String> findById(@PathVariable int petId) {
        if (petId == 1) {
            return new ResponseEntity<>("Hello World " + petId, HttpStatus.OK);
        } else if (petId == 2) {
            return new ResponseEntity<>("Hello World " + petId, HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pet> create(@RequestBody Pet pet) {
        if (pet.getId() == 0) {
            return new ResponseEntity<>(pet, HttpStatus.CREATED);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{n}/{pets}/{user}/c")
    public String getWackyRequest(@PathVariable int n, @PathVariable String pets, @PathVariable String user) {
        return user + " " + n;
    }

}
