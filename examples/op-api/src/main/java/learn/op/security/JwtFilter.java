package learn.op.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter implements Filter {

    private final JwtConverter jwtConverter = new JwtConverter();

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // For our purposes, we allow all GET and OPTIONS requests
        if (request.getMethod().equalsIgnoreCase("GET")
                || request.getMethod().equalsIgnoreCase("OPTIONS")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // If it's not a GET or OPTIONS, get the token make a user out of it
        AppUser user = jwtConverter.getUserFromAuthHeader(request.getHeader("Authorization"));

        // Reject anyone without a valid token
        if (user == null) {
            response.setStatus(403);
            return;
        }

        // Now that we have our AppUser we can get fancy with roles...
        // in this example, reject any request to an
        // "admin" path from a user without the "ADMIN" role.
        if (!user.getRoles().contains("ADMIN") && request.getServletPath().contains("/admin")) {
            response.setStatus(403);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}

