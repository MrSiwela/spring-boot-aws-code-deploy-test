package com.za.ubuntuspace.askjabu.Repositories;

import com.za.ubuntuspace.askjabu.Entities.User;
import com.za.ubuntuspace.askjabu.Entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor,Integer> {

    public List<Vendor> findByVendorNameLike(String search);
}
