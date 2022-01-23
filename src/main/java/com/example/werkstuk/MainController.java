package com.example.werkstuk;

import com.example.werkstuk.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;

@Controller
public class MainController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ShoppingCartRepository cartRepository;
    @ModelAttribute("MyCategories")
    public Iterable<Category> categories() {
        return categoryRepository.findAll();
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple(Authentication authentication) {
        OAuth2AuthenticationToken token =  (OAuth2AuthenticationToken) authentication;
        return token.getPrincipal().getAttribute("login");
    }

    @GetMapping(value = "/cart")
    public String getCart(Model model, Authentication authentication) {
        OAuth2AuthenticationToken token =  (OAuth2AuthenticationToken) authentication;
        var shoppingCart = cartRepository.findById(authentication.getName()).get();
        model.addAttribute("products", shoppingCart.getProducts());
        model.addAttribute("total", shoppingCart.getTotal());
        model.addAttribute("username", token.getPrincipal().getAttribute("login"));

        return "cart";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.GET)
    public String addProduct(Model model,@RequestParam(required = false) String productId, Authentication authentication) {
        var shoppingCart = cartRepository.findById(authentication.getName()).get();
        shoppingCart.getProducts().add(productRepository.findById(Integer.parseInt(productId)).get());
        cartRepository.save(shoppingCart);
        return "redirect:/";
    }

    @RequestMapping(value = "/removeProduct", method = RequestMethod.GET)
    public String removeProduct(@RequestParam(required = false) String productId, Authentication authentication) {
        var shoppingCart = cartRepository.findById(authentication.getName()).get();
        shoppingCart.getProducts().remove(productRepository.findById(Integer.parseInt(productId)).get());
        cartRepository.save(shoppingCart);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(Authentication authentication) {
        var shoppingCart = cartRepository.findById(authentication.getName()).get();
        shoppingCart.products.clear();
        cartRepository.save(shoppingCart);
        return "success";
    }


    @GetMapping(value = {"/"})
    public String index(Model model, @RequestParam(required = false) String category, Authentication authentication){
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
        OAuth2AuthenticationToken token =  (OAuth2AuthenticationToken) authentication;
        model.addAttribute("username", token.getPrincipal().getAttribute("login"));

        return "producten";
    }
}
