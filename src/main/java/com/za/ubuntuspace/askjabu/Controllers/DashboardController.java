package com.za.ubuntuspace.askjabu.Controllers;

import com.za.ubuntuspace.askjabu.Entities.*;
import com.za.ubuntuspace.askjabu.Repositories.UserRepository;
import com.za.ubuntuspace.askjabu.Services.CustomerService;
import com.za.ubuntuspace.askjabu.Services.OrderService;
import com.za.ubuntuspace.askjabu.Services.ProductService;
import com.za.ubuntuspace.askjabu.Services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private VendorService vendorService;

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        User loggedUser = userRepository.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Vendor userVendor = vendorService.getVendorById(loggedUser.getVendor().getId());
        model.addAttribute("vendor",userVendor);

        List<Product> products = productService.getAllProdcuts();
        List<Order> orders = orderService.getAllOrders();
        List<Customer> customers = customerService.getAllCustomers();

        if(orders.size() >= 10){
            orders = orders.subList(0,10);
        }
        if(customers.size() >= 5){
            customers = customers.subList(0,5);
        }

        adminDashboardStats(model);

        model.addAttribute("productList",products);
        model.addAttribute("orderList",orders);
        model.addAttribute("customers",customers);
        return "dashboard";
    }

    public void adminDashboardStats(Model model){
        int ordersPlaced = orderService.getByOrderStatus("order-placed").size();
        int ordersInTransit = orderService.getByOrderStatus("in-transit").size();
        int ordersDelivered = orderService.getByOrderStatus("delivered").size();

        model.addAttribute("OrdersPlaced",ordersPlaced);
        model.addAttribute("ordersInTransit",ordersInTransit);
        model.addAttribute("ordersDelivered",ordersDelivered);
    }
}
