package learn.op.controllers;

import learn.op.models.Ability;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ability")
public class AbilityController {

    private Map<Integer, Ability> abilityMap = Ability.abilitiesMap();

    @GetMapping
    public List<Ability> getAbilities() {
        return new ArrayList<>(abilityMap.values());
    }
}
