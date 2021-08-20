package learn.authconsumer.security;

import learn.authconsumer.models.AppUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringJwtToAppUserConverter implements Converter<String, AppUser> {

    private JwtConverter jwtConverter;

    public StringJwtToAppUserConverter(JwtConverter jwtConverter) {
        this.jwtConverter = jwtConverter;
    }

    @Override
    public AppUser convert(String s) {
        AppUser appUser = null;

        if (s != null && s.startsWith("Bearer ")) {
            appUser = jwtConverter.getUserFromAuthHeader(s);
        }

        return appUser;
    }
}
