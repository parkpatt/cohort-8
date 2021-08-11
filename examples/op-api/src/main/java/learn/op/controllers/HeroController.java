package learn.op.controllers;

import learn.op.models.Hero;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/hero")
public class HeroController {

    private List<Hero> heroes = Hero.makeHeroes();

    @GetMapping
    public List<Hero> getHeroes() {
        return heroes;
    }
}
