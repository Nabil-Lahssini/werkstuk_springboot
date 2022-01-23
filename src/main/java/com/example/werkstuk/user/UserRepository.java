package com.example.werkstuk.user;

import org.springframework.data.repository.CrudRepository;

//De repository klassen gebruiken wij om met de database te communiceren. bv findbyid, save enz.
public interface UserRepository extends CrudRepository<User, String> {
}
