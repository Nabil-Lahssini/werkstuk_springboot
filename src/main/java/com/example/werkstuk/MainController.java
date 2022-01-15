package com.example.werkstuk;

import com.example.werkstuk.product.Category;
import com.example.werkstuk.product.CategoryRepository;
import com.example.werkstuk.product.Product;
import com.example.werkstuk.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class MainController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @ModelAttribute("MyCategories")
    public Iterable<Category> categories() {
        return categoryRepository.findAll();
    }
    @GetMapping(value = {"/"})
    public String index(Model model, @RequestParam(required = false) String category){
        var alleproducten = productRepository.findAll();
        var newList = new ArrayList<Product>();
        if (category != null){
            if(!category.isBlank()) {
                for (var product : alleproducten) {
                    if (product.getCategory().getName().equals(category)) {
                        newList.add(product);
                    }
                }
                model.addAttribute("products", newList);
            }else{
                model.addAttribute("products", alleproducten);
            }
        }else{
            model.addAttribute("products", alleproducten);
        }
        return "producten";
    }
}
