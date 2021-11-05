package com.za.ubuntuspace.askjabu.Controllers;

import com.za.ubuntuspace.askjabu.Entities.Category;
import com.za.ubuntuspace.askjabu.Entities.Order;
import com.za.ubuntuspace.askjabu.Entities.Product;
import com.za.ubuntuspace.askjabu.Entities.Vendor;
import com.za.ubuntuspace.askjabu.Repositories.ProductRepository;
import com.za.ubuntuspace.askjabu.Services.CategoryService;
import com.za.ubuntuspace.askjabu.Services.OrderService;
import com.za.ubuntuspace.askjabu.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        List<Product> products = productService.getAllProdcuts();
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("productList",products);
        model.addAttribute("orderList",orders);
        return "dashboard";
    }

    @GetMapping("/inventory")
    public String inventory(Model model,@RequestParam(defaultValue = "0") int page){
//        List<Product> products = productService.getAllProdcuts();
//        model.addAttribute("productList",products);
        model.addAttribute("productList",repository.findAll(PageRequest.of(page,6)));
        model.addAttribute("product",new Product());
        return "inventory";
    }

    @GetMapping("/inventory/new")
    public String addNewProduct(Model model){
        List<Category> categoryList = categoryService.getAllCategories();
        model.addAttribute("product",new Product());
        model.addAttribute("categoryList",categoryList);
        return "addProduct";
    }

    @PostMapping("/inventory/save")
    public String saveProduct(Product product, RedirectAttributes ra){
        Product savedProduct = productService.saveProduct(product);
        ra.addFlashAttribute("message","Product Was Added Successfully.");
        return "redirect:/inventory";
    }

    @PostMapping("/inventory/edit/save")
    public String editProduct(Product product, RedirectAttributes ra){
        System.out.println(product.toString());
        productService.saveProduct(product);
        ra.addFlashAttribute("message","Product Was Added Successfully.");
        return "redirect:/inventory";
    }

    @GetMapping("/inventory/edit/{id}")
    public String editProduct(@PathVariable("id") int id,Model model, RedirectAttributes ra){
        try{
            Product editProduct = productService.getProductById(id);
            List<Category> categoryList = categoryService.getAllCategories();
            model.addAttribute("product",editProduct);
            model.addAttribute("categoryList",categoryList);
            return "editProduct";
        }catch(Exception e){
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/inventory";
        }
    }

    @GetMapping("/inventory/delete/{id}")
    public String removeProduct(@PathVariable("id") int id,Model model){
            Product deleteProduct = productService.deleteProduct(id);
            model.addAttribute("product",deleteProduct);
            return "redirect:/inventory";

    }

    @GetMapping("/settings")
    public String settings(Model model){
        model.addAttribute("settings",new Vendor());
        return "settings";
    }


    @GetMapping("/api/v1/products/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        try{
            return ResponseEntity.ok()
                    .location((new URI("/api/v1/products/all")))
                    .body(productService.getAllProdcuts());
        }catch(URISyntaxException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/api/v1/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product savedProduct = productService.saveProduct(product);

        try{
            return ResponseEntity.created(new URI("/api/v1/products/"+savedProduct.getId()))
                    .eTag(Integer.toString(savedProduct.getId()))
                    .body(savedProduct);
        }catch(URISyntaxException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/api/v1/products/{id}")
    public ResponseEntity deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public String testing(){
        return "Test Successful";
    }

}
