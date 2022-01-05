package com.za.ubuntuspace.askjabu.Services;

import com.za.ubuntuspace.askjabu.Entities.Vendor;
import com.za.ubuntuspace.askjabu.Repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public List<Vendor> getAllVendors(){
        return vendorRepository.findAll();
    }

    public Vendor getVendorById(int id){
        return vendorRepository.findById(id).get();
    }

    public Vendor newVendor(Vendor vendor){
        return vendorRepository.save(vendor);
    }

    public Vendor deleteVendor(int id){
        Vendor deleteVendor = getVendorById(id);
        vendorRepository.delete(deleteVendor);
        return deleteVendor;
    }

    public List<Vendor> searchVendors(String searchTerm){
        return vendorRepository.findByVendorNameLike(searchTerm);
    }
}
