package learn.authconsumer.controllers;

import learn.authconsumer.models.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/admin")
public class ExampleController {


    /**
     * This example uses the StringJwtToAppUserConverter in
     * the /security package to automatically convert the
     * "Authorization: Bearer..." header into an AppUser
     *
     * @param appUser
     * @return
     */

    @PostMapping
    public ResponseEntity<Object> accessProtectedStuff(@RequestHeader(value = "Authorization", required = false) AppUser appUser) {
        // reject for this protected route
        if (appUser == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        // Could check for AppUser roles here and conditionally reject

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
