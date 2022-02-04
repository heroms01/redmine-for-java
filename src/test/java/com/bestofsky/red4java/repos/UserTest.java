package com.bestofsky.red4java.repos;

import com.bestofsky.red4java.models.EmailAddress;
import com.bestofsky.red4java.models.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
class UserTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailAddressRepository emailAddressRepository;

    User getTestUser() {
        return User.builder()
                .login("new")
                .password("password")
                .passwordConfirmation("password")
                .firstName("유저")
                .lastName("뉴")
                .admin(false)
                .build();
    }

    @Test
    @DisplayName("새 사용자 생성")
    @Transactional
    void newUser() {
        User user = getTestUser();
        User savedUser = userRepository.save(user);

        EmailAddress emailAddress = EmailAddress.builder()
                .address("newuser@somenet.foo")
                .isDefault((short) 1)
                .notify((short) 0)
                .build();

        emailAddress.setUser(user);
        emailAddressRepository.save(emailAddress);

        assertThat(savedUser.getEmailAddress()).isEqualTo(emailAddress);
    }

    @Test
    @DisplayName("패스워드 Not match")
    @Transactional
    void newUserWithPassword() {
        User user = getTestUser();
        user.setPassword("1234");
        user.setPasswordConfirmation("4567");

        assertThatThrownBy(() -> userRepository.save(user)).isInstanceOf(ValidationException.class);
    }

    @Test
    @DisplayName("새 사용자 생성시 login 중복 오류")
    void newUserSameLogin() {
        User user = getTestUser();
        userRepository.save(user);

        User sameLoginNewUser = User.builder()
                .login("new")
                .firstName("new2")
                .lastName("user2")
                .build();

        assertThatThrownBy(() -> userRepository.save(sameLoginNewUser))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void generateSalt() {
        User user = getTestUser();
        String salt = user.generateSalt();

        assertThat(salt.length()).isEqualTo(32);
    }

    @Test
    @Transactional
    void testPassword() {
        User user = getTestUser();
        user.setPassword(null);

        userRepository.save(user);
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