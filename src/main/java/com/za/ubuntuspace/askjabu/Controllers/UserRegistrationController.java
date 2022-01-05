package com.za.ubuntuspace.askjabu.Controllers;

import com.za.ubuntuspace.askjabu.Entities.RegistrationRequest;
import com.za.ubuntuspace.askjabu.Entities.User;
import com.za.ubuntuspace.askjabu.Entities.Vendor;
import com.za.ubuntuspace.askjabu.Repositories.UserRepository;
import com.za.ubuntuspace.askjabu.Services.RegistrationService;
import com.za.ubuntuspace.askjabu.Services.UserDetailsServiceImpl;
import com.za.ubuntuspace.askjabu.Services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserRegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VendorService vendorService;

    @GetMapping("/registration")
    public String registrationPage(Model model){
        RegistrationRequest registerUser = new RegistrationRequest();
        List<Vendor> vendors = vendorService.getAllVendors();
        model.addAttribute("registerUser",registerUser);
        model.addAttribute("vendorList",vendors);
        return "registration";
    }


    @PostMapping("/registration/new")
    public String userRegistration(RegistrationRequest registrationRequest){
        User registered = registrationService.register(registrationRequest);
        System.out.println(registered.getEmail()+" : "+registered.getFullName());
        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/password-reset")
    public String passwordResetPage(Model model){
        User resetUser = new User();
        model.addAttribute("resetUser",resetUser);
        return "forgotPassword";
    }

    @PostMapping("/password-reset/reset")
    public String resetUserPassword(User user){
        User resetUser = userRepository.getUserByEmail(user.getEmail());
        userRepository.save(registrationService.encodeUserPassword(resetUser,resetUser.getEmail()));
        return "redirect:/login";
    }


}
