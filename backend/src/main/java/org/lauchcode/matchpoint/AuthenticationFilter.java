package org.lauchcode.matchpoint;

import org.lauchcode.matchpoint.controllers.AuthenticationController;
import org.lauchcode.matchpoint.models.User;
import org.lauchcode.matchpoint.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends HandlerInterceptorAdapter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/signout", "/css", "/test");

    private static boolean isWhitelisted(String path){
        for (String pathRoot : whitelist){
            if (path.startsWith(pathRoot)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        if(isWhitelisted(request.getRequestURI())){
            return true;
        }

        if(user != null){
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}
