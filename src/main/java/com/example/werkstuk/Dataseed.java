package com.example.werkstuk;

import com.example.werkstuk.product.Category;
import com.example.werkstuk.product.CategoryRepository;
import com.example.werkstuk.product.Product;
import com.example.werkstuk.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Dataseed implements CommandLineRunner {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCatsData();
        loadDogsData();
    }

    private void loadCatsData() {

        if (categoryRepository.count() == 0) {
            Category category = new Category();
            category.setName("Cats");
            var newCategory = categoryRepository.save(category);
            Product product = new Product();
            product.setTitle("Yellow cat"); product.setPrice(19.99); product.setDescription("Tiny yellow kitten");
            product.setCategory(newCategory);
            productRepository.save(product);

            Product product2 = new Product();
            product2.setTitle("Black cat"); product2.setPrice(19.99); product2.setDescription("Tiny black kitten");
            product2.setCategory(newCategory);
            productRepository.save(product2);
        }
    }
    private void loadDogsData(){
        if (categoryRepository.count() == 0) {
            Category category = new Category();
            category.setName("Dogs");
            var newCategory = categoryRepository.save(category);
            Product product = new Product();
            product.setTitle("White dog");
            product.setPrice(19.99);
            product.setDescription("Big white dog");
            product.setCategory(newCategory);
            productRepository.save(product);

            Product product2 = new Product();
            product2.setTitle("Brown dog");
            product2.setPrice(19.99);
            product2.setDescription("Tiny brown dog");
            product2.setCategory(newCategory);
            productRepository.save(product2);
        }
    }
}
