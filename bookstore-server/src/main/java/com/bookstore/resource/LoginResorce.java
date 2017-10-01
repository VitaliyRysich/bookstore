package com.bookstore.resource;

import com.bookstore.domain.User;
import com.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Vitalii on 23.09.2017.
 */
@RestController
public class LoginResorce {
    @Autowired
    private UserService userService;

    @RequestMapping("/token")
    public Map<String, String> token(HttpSession session, HttpServletRequest request){
        System.out.println("test....");
        System.out.println(request.getRemoteHost());

        String remoteHost = request.getRemoteHost();
        int portNumber = request.getRemotePort();

        System.out.println(remoteHost+" : "+portNumber);
        System.out.println(request.getRemoteAddr());

        return Collections.singletonMap("token", session.getId());
    }

    @RequestMapping("/checkSession")
    public ResponseEntity checkSession(){
        return new ResponseEntity("Session Active", HttpStatus.OK);
    }

    @RequestMapping(value="user/logout", method = RequestMethod.POST)
    public ResponseEntity logout(){
        SecurityContextHolder.clearContext();
        return new ResponseEntity("Logout Succesfully!", HttpStatus.OK);
    }

}
