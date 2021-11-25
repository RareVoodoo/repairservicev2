package test.repairservice.repairservicev2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    private static HashMap<String, String> registeredRoles = new HashMap<>();

    static {
        registeredRoles.put("Manager","/manager-dashboard");
        registeredRoles.put("Client","/user-dashboard");
        registeredRoles.put("Master","/master-dashboard");
    }



    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        List<GrantedAuthority> roles = AuthorityUtils.createAuthorityList(String.valueOf(authentication.getAuthorities()));
        String authority = roles.get(0).toString().replaceAll("[\\[\\]]","");

        httpServletResponse.sendRedirect(registeredRoles.get(authority));


        Set<String> role = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());
    }
}
