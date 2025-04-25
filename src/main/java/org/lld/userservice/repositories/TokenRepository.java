package org.lld.userservice.repositories;

import org.lld.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token save(Token token);
    Optional<Token> findTokenByValueAndIsDeleted(String value, boolean deleted);
    Optional<Token> findTokenByValueAndIsDeletedAndExpiryDateGreaterThan(String value, boolean deleted, Date expiryDate);
}
