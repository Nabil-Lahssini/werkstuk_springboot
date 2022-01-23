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

            Category category2 = new Category();
            category2.setName("Dogs");
            var newCategory2 = categoryRepository.save(category2);
            Product product3 = new Product();
            product3.setTitle("White dog");
            product3.setPrice(19.99);
            product3.setDescription("Big white dog");
            product3.setCategory(newCategory2);
            productRepository.save(product3);

            Product product4 = new Product();
            product4.setTitle("Brown dog");
            product4.setPrice(19.99);
            product4.setDescription("Tiny brown dog");
            product4.setCategory(newCategory2);
            productRepository.save(product4);
        }
    }
    private void loadDogsData(){
        if (categoryRepository.count() == 0) {

        }
    }
}
