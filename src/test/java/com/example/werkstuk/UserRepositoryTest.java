package com.example.werkstuk;

import com.example.werkstuk.user.User;
import com.example.werkstuk.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


//Unit test om het verbinding met de database te testen
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;
    @Test
    public void testAddDeleteUser(){
        User user = new User();
        user.setId("uniqueid");
        user.setEmail("email@mail.com");
        user.setUserName("nabil-lah");
        var saved = repository.save(user);
        Assertions.assertThat(saved).isNotNull();
        Assertions.assertThat(saved.getId()).isEqualTo("uniqueid");
        repository.delete(saved);
    }

}
