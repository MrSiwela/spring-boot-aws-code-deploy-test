package com.za.ubuntuspace.askjabu.Controllers;

import com.za.ubuntuspace.askjabu.Configs.FileUploadUtil;
import com.za.ubuntuspace.askjabu.Entities.ResetUser;
import com.za.ubuntuspace.askjabu.Entities.User;
import com.za.ubuntuspace.askjabu.Entities.Vendor;
import com.za.ubuntuspace.askjabu.Repositories.UserRepository;
import com.za.ubuntuspace.askjabu.Services.RegistrationService;
import com.za.ubuntuspace.askjabu.Services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class SettingsController {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegistrationService registrationService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/settings")
    public String settingsPage(Model model){
        User loggedUser = userRepository.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Vendor userVendor = vendorService.getVendorById(loggedUser.getVendor().getId());
        model.addAttribute("vendor",userVendor);
        model.addAttribute("resetUser",new ResetUser());
        return "settings";
    }

    @PostMapping("/settings/vendor-settings")
    public String saveSettings(Vendor vendor, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        vendor.setVendorImage(fileName);
        Vendor savedVendor = vendorService.newVendor(vendor);

        String uploadDir = "./uploads/vendors/"+ savedVendor.getId();
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);

        return "redirect:/settings";
    }

    @PostMapping("/settings/password-reset")
    public String resetPassord(Model model, ResetUser resetUser, RedirectAttributes ra){
        User loggedUser = userRepository.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if( !passwordEncoder.matches(resetUser.getOldPassword(), loggedUser.getPassword())){
            model.addAttribute("errorMessage","Password Incorrect");
        ra.addAttribute("errorMessage","Password Incorrect");
        model.addAttribute(ra);
            return "settings";
        }
        userRepository.save(registrationService.encodeUserPassword(loggedUser,resetUser.getNewPassword()));

        return "redirect:/settings";
    }

}
