package learn.op.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinimumAbilityValidator.class)
@Documented
public @interface MustHaveMinTwoAbilities {
    String message() default "{hero must have at least 2 unique abilities}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] getPayload() default {};
}
