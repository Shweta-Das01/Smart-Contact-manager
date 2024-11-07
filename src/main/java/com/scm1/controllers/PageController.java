package com.scm1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm1.entities.User;
import com.scm1.forms.UserForm;
import com.scm1.helper.Message;
import com.scm1.helper.MessageType;
import com.scm1.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {
@Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    
@RequestMapping("/home")
public String home(){
 return "home";
}
@RequestMapping("/about")
public String about(){
 return "about";
}
@RequestMapping("/services")
public String services(){
 return "services";
}
@GetMapping("/contact")
public String contact(){
    return "contact";
   }
   @GetMapping("/login")
public String login(){
    return "login";
   }
   @GetMapping("/register")
public String register(Model model){
    UserForm userForm=new UserForm();
    model.addAttribute("userForm", userForm);
    return "register";
   }
   //processing register
   @RequestMapping(value = "/do-register",method = RequestMethod.POST)
   public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult,HttpSession session){

    //fetch from data
    //validate from data
    System.out.println(userForm);

    // validate form data
    if (rBindingResult.hasErrors()) {
        return "register";
    }

    //save to database
    User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(true);
        user.setProfilePic(
                "https://static.tnn.in/thumb/msid-112774102,thumbsize-67998,width-1280,height-720,resizemode-75/112774102.jpg?quality=100");

        User savedUser = userService.saveUser(user);

        System.out.println("user saved :");
    //message="Registration Successfull"
    Message message = Message.builder().content("Registration Suceessful").type(MessageType.green).build();
    session.setAttribute("message", message);
    //redirect login page
    return "redirect:/register";
   }
}
