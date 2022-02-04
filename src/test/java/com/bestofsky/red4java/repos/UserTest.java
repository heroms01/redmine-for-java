package com.bestofsky.red4java.repos;

import com.bestofsky.red4java.models.EmailAddress;
import com.bestofsky.red4java.models.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
//@Transactional
class UserTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailAddressRepository emailAddressRepository;

    User getTestUser() {
        return User.builder()
                .login("hong444")
                .password("password")
                .passwordConfirmation("password")
                .firstName("길동")
                .lastName("홍")
                .admin(false)
                .lastLoginOn(LocalDateTime.now())
                .createdOn(ZonedDateTime.now())
                .build();
    }

    @Test
    void test_create() {
        User user = User.builder()
                .login("new")
                .firstName("new")
                .lastName("user")
                .build();

        EmailAddress emailAddress = EmailAddress.builder()
                .address("newuser@somenet.foo")
                .isDefault((short) 1)
                .notify((short) 0)
                .build();

        User savedUser = userRepository.save(user);

        emailAddress.setUser(user);
        emailAddressRepository.save(emailAddress);

        assertThat(savedUser.getEmailAddress()).isEqualTo(emailAddress);
    }

    @Test
    void generateSalt() {
        User user = getTestUser();
        String salt = user.generateSalt();

        assertThat(salt.length()).isEqualTo(32);
    }

    @Test
    void hashPassword() {
        User user = getTestUser();
        String salt = user.generateSalt();
        String hashPassword = user.hashPassword(user.getPassword());
        String result = user.hashPassword(salt + hashPassword);

        log.info(result);
    }
}