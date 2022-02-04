package com.bestofsky.red4java.models;

import com.bestofsky.red4java.lib.Utils;
import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity(name = "users")
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @Column(name = "firstname", length = 30)
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "admin")
    private Boolean admin;

    @Column(name = "status")
    @ColumnDefault("1")
    private Integer status;

    @Column(name = "last_login_on")
    private LocalDateTime lastLoginOn;

    @Column(name = "language", length = 5)
    private String language;

    @Column(name = "auth_source_id")
    private Integer authSourceId;

    @Column(name = "type")
    private String type;

    @Column(name = "identity_url")
    private String identityUrl;

    @Column(name = "mail_notification")
    private String mailNotification;

    @Column(name = "salt", length = 64)
    private String salt;

    @Column(name = "must_change_passwd")
    private Short mustChangePasswd;

    @Column(name = "passwd_changed_on")
    private LocalDateTime passwdChangedOn;

    @Column(name = "twofa_scheme")
    private String twofaScheme;

    @Column(name = "twofa_totp_key")
    private String twofaTotpKey;

    @Column(name = "twofa_totp_last_used_at")
    private Integer twofaTotpLastUsedAt;

    @OneToOne(mappedBy = "user")
    private EmailAddress emailAddress;

    @Transient
    private String password;

    @Transient
    private String passwordConfirmation;

    @Column(name = "created_on")
    private ZonedDateTime createdOn;

    @Column(name = "updated_on")
    private ZonedDateTime updatedOn;

    public String hashPassword(String clearPassword) {
        return DigestUtils.sha1Hex(clearPassword);
    }

    public String generateSalt() {
        return Utils.randomHex(16);
    }
}
