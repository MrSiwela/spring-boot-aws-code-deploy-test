package com.za.ubuntuspace.askjabu.Controllers;

import com.za.ubuntuspace.askjabu.Configs.FileUploadUtil;
import com.za.ubuntuspace.askjabu.Entities.User;
import com.za.ubuntuspace.askjabu.Entities.Vendor;
import com.za.ubuntuspace.askjabu.Repositories.UserRepository;
import com.za.ubuntuspace.askjabu.Services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/vendors")
    public String vendorPage(Model model){
        List<Vendor> vendorList = vendorService.getAllVendors();
        List<User> disabledUsers = userRepository.findAllByEnabled(false);
        model.addAttribute("vendorList",vendorList);
        model.addAttribute("disabledUsers",disabledUsers);
        return "vendors";
    }

    @GetMapping("/vendors/new")
    public String addVendorPage(Model model){
        model.addAttribute("newVendor",new Vendor());
        return "addVendor";
    }

    @PostMapping("/vendors/save")
    public String saveVendor(Vendor vendor, Model model, @RequestParam("image")MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        vendor.setVendorImage(fileName);

        if(!multipartFile.isEmpty()) vendor.setVendorImage(fileName);
        Vendor savedVendor = vendorService.newVendor(vendor);

        System.out.println(vendor.getVendorImage());


        String uploadDir = "./uploads/vendors/"+ savedVendor.getId();
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);


        return "redirect:/vendors";
    }

    @GetMapping("/vendors/edit/{id}")
    public String editVendor(@PathVariable("id") int id, Model model){
        Vendor editVendor = vendorService.getVendorById(id);
        List<User> users = userRepository.findAllByVendorId(id);
        model.addAttribute("editVendor",editVendor);
        model.addAttribute("employees",users);
        return "editVendor";
    }

    @GetMapping("/vendors/delete/{id}")
    public String removeVendor(@PathVariable("id") int id, Model model){
        Vendor deletedVendor = vendorService.deleteVendor(id);
        model.addAttribute("deletedVendor",deletedVendor);
        return "redirect:/vendors";
    }

    @PostMapping("/vendors/user/enable/{email}")
    public String enableUser(@PathVariable("email") String email,Model model){
        User user = userRepository.getUserByEmail(email);
        user.setEnabled(true);
        userRepository.save(user);
        List<Vendor> vendors = vendorService.searchVendors("%Test%");
        System.out.println(vendors.size());
        return  "redirect:/vendors";
    }

    @PostMapping("/vendors/search/{search}")
    public String searchVendors(@PathVariable("search") String search, Model model, RedirectAttributes ra){
        List<Vendor> vendors = vendorService.searchVendors("%"+search+"%");
        ra.addAttribute("vendorList",vendors);
        model.addAttribute(ra);
        return  "redirect:/vendors";
    }
}
