package com.za.ubuntuspace.askjabu.Services;

import com.za.ubuntuspace.askjabu.Entities.Vendor;
import com.za.ubuntuspace.askjabu.Repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public Vendor getVendorById(int id){
        return vendorRepository.findById(id).get();
    }
}
