package com.bestofsky.red4java.repos;

import com.bestofsky.red4java.models.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailAddressRepository extends JpaRepository<EmailAddress, Long> {
}
