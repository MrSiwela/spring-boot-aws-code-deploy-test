package com.za.ubuntuspace.askjabu.Services;

import com.za.ubuntuspace.askjabu.Entities.Product;
import com.za.ubuntuspace.askjabu.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProdcuts(){
        return productRepository.findAll();
    }

    public Product getProductById(int id){
        return productRepository.findById(id).get();
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Product deleteProduct(int id){
        Product deleteProduct = productRepository.findById(id).get();
        productRepository.delete(deleteProduct);
        return deleteProduct;
    }

}
