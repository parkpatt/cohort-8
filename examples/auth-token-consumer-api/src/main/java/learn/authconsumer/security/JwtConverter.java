package learn.authconsumer.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import learn.authconsumer.models.AppUser;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Component
public class JwtConverter {

    // Matches the secret set in the user-api, should probably set in properties
    private static final String SECRET = "de8a26d0-f6e8-4470-91d0-ba7a44391281";
    private Key key = new SecretKeySpec(SECRET.getBytes(), HS256.getJcaName());
    private final String ISSUER = "dev10-users-api";

    public AppUser getUserFromAuthHeader(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .requireIssuer(ISSUER)
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token.substring(7));

            String username = jws.getBody().getSubject();
            String rolesStr = (String) jws.getBody().get("roles");
            List<String> roles = Arrays.stream(rolesStr.split(",")).collect(Collectors.toList());

            AppUser user = new AppUser();
            user.setUsername(username);
            user.setRoles(roles);

            return user;
        } catch (JwtException ex) {
            System.out.println(ex);
        }

        return null;
    }
}