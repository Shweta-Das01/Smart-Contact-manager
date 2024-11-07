package com.scm1.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm1.entities.User;
import com.scm1.helper.Helper;
import com.scm1.services.UserService;

@ControllerAdvice
public class RootController {
    private Logger logger=LoggerFactory.getLogger(this.getClass());
 @Autowired
    private UserService userService;
    @ModelAttribute
    public void addLoginUserInformation(Model model,Authentication authentication){

        if(authentication==null){
            return;
        }
        String username=Helper.getEmailOfLoggedInUser(authentication);
        logger.info("User logged in :",username);
        User user=userService.getUserByEmail(username);
        
        System.out.println(user);
            System.out.println(user.getEmail());
            System.out.println(user.getName());
    
           model.addAttribute("loggedInUser", user);
    
        
    }
}
