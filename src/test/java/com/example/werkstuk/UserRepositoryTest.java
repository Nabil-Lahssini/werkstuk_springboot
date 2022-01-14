package com.example.werkstuk;

import com.example.werkstuk.user.User;
import com.example.werkstuk.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;
    @Test
    public void testAddDeleteUser(){
        User user = new User();
        user.setEmail("email@mail.com");
        user.setFirstName("Nabil");
        user.setLastName("Lahssini");
        var saved = repository.save(user);
        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getId()).isGreaterThan(0);
        repository.delete(saved);
    }

}
