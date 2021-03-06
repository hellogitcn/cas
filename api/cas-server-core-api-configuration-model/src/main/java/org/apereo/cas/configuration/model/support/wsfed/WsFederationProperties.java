package org.apereo.cas.configuration.model.support.wsfed;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apereo.cas.configuration.model.core.util.EncryptionJwtSigningJwtCryptographyProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

/**
 * This is {@link WsFederationProperties}.
 *
 * @author Misagh Moayyed
 * @since 5.1.0
 */
@Slf4j
@Getter
@Setter
public class WsFederationProperties implements Serializable {

    private static final long serialVersionUID = -8679379856243224647L;

    /**
     * Settings related to the wed-fed identity provider.
     */
    private IdentityProvider idp = new IdentityProvider();

    /**
     * Settings related to the we-fed security token service.
     */
    private SecurityTokenService sts = new SecurityTokenService();

    @Getter
    @Setter
    public static class IdentityProvider implements Serializable {

        private static final long serialVersionUID = 5190493517277610788L;

        /**
         * At this point, by default security token service’s endpoint operate using a single
         * realm configuration and identity provider configuration is only able to recognize and request tokens for a single realm.
         * Registration of clients need to ensure this value is matched.
         */
        private String realm = "urn:org:apereo:cas:ws:idp:realm-CAS";

        /**
         * Realm name.
         */
        private String realmName = "CAS";
    }

    @Getter
    @Setter
    public static class SecurityTokenService implements Serializable {

        private static final long serialVersionUID = -1155140161252595793L;

        /**
         * When generating a SAML token, indicates the subject name-id format to use.
         */
        private String subjectNameIdFormat = "unspecified";

        /**
         * Whether tokens generated by STS should encrypted.
         */
        private boolean encryptTokens = true;

        /**
         * Keystore path used to sign tokens.
         */
        private String signingKeystoreFile;

        /**
         * Keystore password used to sign tokens.
         */
        private String signingKeystorePassword;

        /**
         * Keystore path used to encrypt tokens.
         */
        private String encryptionKeystoreFile;

        /**
         * Keystore password used to encrypt tokens.
         */
        private String encryptionKeystorePassword;

        /**
         * Default WSDL location to load for the security token service
         * that publishes the endpoints for various ops.
         */
        private String wsdlLocation = "classpath:/wsdl/ws-trust-1.4-service.wsdl";

        /**
         * Name of the service cross-referenced in the WSDL.
         */
        private String serviceName = "ns1:SecurityTokenService";

        /**
         * Name of the endpoint op cross-referenced in the WSDL.
         */
        private String endpointName = "ns1:TransportUT_Port";

        /**
         * Crypto settings used to secure calls between the idp and the sts.
         */
        @NestedConfigurationProperty
        private EncryptionJwtSigningJwtCryptographyProperties crypto = new EncryptionJwtSigningJwtCryptographyProperties();

        /**
         * Realm definition settings that define this CAS server.
         */
        private RealmDefinition realm = new RealmDefinition();

        @Getter
        @Setter
        public static class RealmDefinition implements Serializable {

            private static final long serialVersionUID = -2209230334376432934L;

            /**
             * Keystore path associated with the this realm.
             */
            private String keystoreFile;

            /**
             * Keystore password associated with the this realm.
             */
            private String keystorePassword;

            /**
             * Key alias associated with the this realm.
             */
            private String keystoreAlias;

            /**
             * Key alias associated with the this realm.
             */
            private String keyPassword;

            /**
             * Issuer/name of the realm identified and registered with STS.
             */
            private String issuer = "CAS";
        }
    }
}
