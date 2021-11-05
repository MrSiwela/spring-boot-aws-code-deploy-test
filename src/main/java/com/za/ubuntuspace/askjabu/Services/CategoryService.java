package com.za.ubuntuspace.askjabu.Services;

import com.za.ubuntuspace.askjabu.Entities.Category;
import com.za.ubuntuspace.askjabu.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category removeCategory(int id){
        Category deleteCategory = categoryRepository.findById(id).get();
        categoryRepository.delete(deleteCategory);
        return deleteCategory;
    }
}
