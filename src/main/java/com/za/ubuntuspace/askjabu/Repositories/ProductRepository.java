package com.za.ubuntuspace.askjabu.Repositories;

import com.za.ubuntuspace.askjabu.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    public List<Product> findAllByVendorId(int vendorId);
}
