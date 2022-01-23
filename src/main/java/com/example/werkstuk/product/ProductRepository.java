package com.example.werkstuk.product;

import org.springframework.data.repository.CrudRepository;

//De repository klassen gebruiken wij om met de database te communiceren. bv findbyid, save enz.
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
