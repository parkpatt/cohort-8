package learn.op.validations;

import learn.op.models.Ability;
import learn.op.models.Hero;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class MinimumAbilityValidator implements ConstraintValidator<MustHaveMinTwoAbilities, Hero> {

    @Override
    public void initialize(MustHaveMinTwoAbilities constraintAnnotation) {
        // nothing to do here
    }

    @Override
    public boolean isValid(Hero hero, ConstraintValidatorContext constraintValidatorContext) {
        Set<Ability> abilities = new HashSet<>();
        for (Ability a : hero.getAbilities()) {
            abilities.add(a);
        }

        return abilities.size() == hero.getAbilities().size() && abilities.size() > 1;
    }
}
