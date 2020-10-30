package com.infoshare.service;

import com.infoshare.dao.CategoryDao;
import com.infoshare.dto.CategoryDto;
import com.infoshare.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class CategoryService {

    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Inject
    private CategoryDao categoryDao;

    public void save(CategoryDto categoryDto) { categoryDao.saveCategory(categoryDto); }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        return categoryDao.updateCategory(id, categoryDto);
    }

    public Category getCategoryById(Long id) {
        return categoryDao.getCategoryById(id);
    }

    public List<Category> getCategoriesList() {
        return categoryDao.getCategoriesList();
    }

    public Category findCategoryByName(String name) {
        return categoryDao.findCategoryByName(name);
    }

}
