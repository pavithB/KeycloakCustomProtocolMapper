import org.keycloak.models.ClientSessionContext;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.ProtocolMapperModel;
import org.keycloak.models.UserSessionModel;
import org.keycloak.protocol.oidc.OIDCLoginProtocol;
import org.keycloak.protocol.oidc.mappers.*;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.representations.AccessToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomOIDCProtocolMapper extends AbstractOIDCProtocolMapper implements OIDCAccessTokenMapper, OIDCIDTokenMapper, UserInfoTokenMapper {

    public static final String PROVIDER_ID = "oidc-customprotocolmapper";

    private static final List<ProviderConfigProperty> configProperties = new ArrayList<ProviderConfigProperty>();

    /**
     * Maybe you want to have config fields for your Mapper
     */
    /*
    static {
        ProviderConfigProperty property;
        property = new ProviderConfigProperty();
        property.setName(ProtocolMapperUtils.USER_ATTRIBUTE);
        property.setLabel(ProtocolMapperUtils.USER_MODEL_ATTRIBUTE_LABEL);
        property.setHelpText(ProtocolMapperUtils.USER_MODEL_ATTRIBUTE_HELP_TEXT);
        property.setType(ProviderConfigProperty.STRING_TYPE);
        configProperties.add(property);

        property = new ProviderConfigProperty();
        property.setName(ProtocolMapperUtils.MULTIVALUED);
        property.setLabel(ProtocolMapperUtils.MULTIVALUED_LABEL);
        property.setHelpText(ProtocolMapperUtils.MULTIVALUED_HELP_TEXT);
        property.setType(ProviderConfigProperty.BOOLEAN_TYPE);
        configProperties.add(property);

    }
     */
    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configProperties;
    }

    @Override
    public String getDisplayCategory() {
        return TOKEN_MAPPER_CATEGORY;
    }

    @Override
    public String getDisplayType() {
        return "ohhhhhh noooooooooooo";
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getHelpText() {
        return "some help text";
    }

//    public IDToken transformAccessToken(IDToken token, ProtocolMapperModel mappingModel, KeycloakSession keycloakSession,
//                                            UserSessionModel userSession, ClientSessionContext clientSessionCtx) {
////IDToken token, ProtocolMapperModel mappingModel, UserSessionModel userSession, KeycloakSession keycloakSession, ClientSessionContext clientSessionCtx
//        token.getOtherClaims().put("stackoverflowCustomToken", "stackoverflow");
////        token.getOtherClaims().
//        // call profile API and enrich with permission claims
////        setClaim(token, mappingModel, userSession);
//        setClaim(token, mappingModel, userSession, keycloakSession,clientSessionCtx);
//        return token;
//    }

    public AccessToken transformAccessToken(AccessToken token, ProtocolMapperModel mappingModel, KeycloakSession keycloakSession,
                                            UserSessionModel userSession, ClientSessionContext clientSessionCtx) {

        token.getOtherClaims().put("supiri", "token_supiri");

        setClaim(token, mappingModel, userSession, keycloakSession, clientSessionCtx);
        return token;
    }

//    public static ProtocolMapperModel create(String name, boolean accessToken, boolean idToken, boolean userInfo) {
//        ProtocolMapperModel mapper = new ProtocolMapperModel();
//        mapper.setName(name);
//        mapper.setProtocolMapper(PROVIDER_ID);
//        mapper.setProtocol(OIDCLoginProtocol.LOGIN_PROTOCOL);
//        Map<String, String> config = new HashMap<String, String>();
//        config.put("id.token.claim", "true");
//        config.put("access.token.claim", "true");
//        mapper.setConfig(config);
//        return mapper;
//    }

    public static ProtocolMapperModel create(String name,
                                             boolean accessToken, boolean idToken, boolean userInfo) {
        ProtocolMapperModel mapper = new ProtocolMapperModel();
        mapper.setName(name);
        mapper.setProtocolMapper(PROVIDER_ID);
        mapper.setProtocol(OIDCLoginProtocol.LOGIN_PROTOCOL);
        Map<String, String> config = new HashMap<String, String>();
//        config.put(OIDCAttributeMapperHelper.TOKEN_CLAIM_NAME, hardcodedName);
//        config.put(CLAIM_VALUE, hardcodedValue);
//        config.put(OIDCAttributeMapperHelper.JSON_TYPE, claimType);
        config.put(OIDCAttributeMapperHelper.INCLUDE_IN_ACCESS_TOKEN, "true");
        config.put(OIDCAttributeMapperHelper.INCLUDE_IN_ID_TOKEN, "true");
        mapper.setConfig(config);
        return mapper;
    }

}
