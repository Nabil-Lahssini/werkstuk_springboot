package com.example.werkstuk.user;

import com.example.werkstuk.product.ProductRepository;
import com.example.werkstuk.product.ShoppingCart;
import com.example.werkstuk.product.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class Auth implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ShoppingCartRepository shopRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken token =  (OAuth2AuthenticationToken) authentication;
        User user = new User();
        if (!userRepository.findById(token.getName()).isPresent()){
            user.setId(token.getName());
            user.setUserName(token.getPrincipal().getAttribute("login"));
            user.setEmail(token.getPrincipal().getAttribute("email"));
            userRepository.save(user);
            ShoppingCart sc = new ShoppingCart();
            sc.setId(user.getId());
            shopRepository.save(sc);
        }
        RedirectStrategy redirect = new DefaultRedirectStrategy();
        redirect.sendRedirect(request, response, "/");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);

    }
}
