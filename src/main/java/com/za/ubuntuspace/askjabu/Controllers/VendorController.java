package com.za.ubuntuspace.askjabu.Controllers;

import com.za.ubuntuspace.askjabu.Entities.User;
import com.za.ubuntuspace.askjabu.Entities.Vendor;
import com.za.ubuntuspace.askjabu.Repositories.UserRepository;
import com.za.ubuntuspace.askjabu.Services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("vendorList",vendorList);
        return "vendors";
    }

    @GetMapping("/vendors/new")
    public String addVendorPage(Model model){
        model.addAttribute("newVendor",new Vendor());
        return "addVendor";
    }

    @PostMapping("/vendors/save")
    public String saveVendor(Vendor vendor,Model model){
        vendorService.newVendor(vendor);
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
}
