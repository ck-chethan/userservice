package org.lld.userservice;

import org.junit.jupiter.api.Test;
import org.lld.userservice.security.repositories.JpaRegisteredClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.test.annotation.Commit;

import java.util.UUID;

@SpringBootTest
class UserserviceApplicationTests {
//    @Autowired
//    private JpaRegisteredClientRepository jpaRegisteredClientRepository;
//
//    @Test
//    void contextLoads() {
//    }
//
////    @Test
////    @Commit
////    void storeRegisterClientIntoDatabase() {
////        // This test is to check if the storeRegisterClientIntoDatabase method works as expected
////        // You can implement the logic to test the method here
////        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
////                .clientId("oidc-client")
////                .clientSecret("{noop}secret")
////                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
////                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
////                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
////                .redirectUri("https://oauth.pstmn.io/v1/callback")
////                .postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
////                .scope(OidcScopes.OPENID)
////                .scope(OidcScopes.PROFILE)
////                .scope("ADMIN")
////                .scope("STUDENT")
////                .scope("MENTOR") // Role
////                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
////                .build();
////
////        jpaRegisteredClientRepository.save(oidcClient);
////
////    }

}
