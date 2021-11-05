package com.za.ubuntuspace.askjabu.Repositories;

import com.za.ubuntuspace.askjabu.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
