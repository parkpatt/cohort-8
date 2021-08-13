package learn.op.controllers;

import learn.op.models.Hero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/{heroId}")
    public ResponseEntity<Hero> getHero(@PathVariable int heroId) {
        Hero hero = heroes.stream()
                .filter(h -> h.getId() == heroId)
                .findFirst()
                .orElse(null);
        if (hero != null) {
            return ResponseEntity.ok(hero);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Hero> create(@RequestBody Hero hero) {
        int nextId = heroes.stream()
                .mapToInt(h -> h.getId())
                .max()
                .orElse(0) + 1;

        hero.setId(nextId);
        heroes.add(hero);
        return ResponseEntity
                .created(URI.create("http://localhost:8080/api/hero/" + nextId))
                .body(hero);
    }

    @PutMapping("/{heroId}")
    public ResponseEntity<Void> update(@PathVariable int heroId, @RequestBody Hero hero) {
        if (hero == null || hero.getId() != heroId) {
            return ResponseEntity.badRequest().build();
        }
        for (int i = 0; i < heroes.size(); i ++) {
            if (heroes.get(i).getId() == heroId) {
                heroes.set(i, hero);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{heroId}")
    public ResponseEntity<Void> delete(@PathVariable int heroId) {
        if (heroes.removeIf(h -> h.getId() == heroId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
