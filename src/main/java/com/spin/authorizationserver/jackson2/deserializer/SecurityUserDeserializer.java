package com.spin.authorizationserver.jackson2.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.spin.authorizationserver.entities.User;
import com.spin.authorizationserver.model.SecurityUser;

import java.io.IOException;

public class SecurityUserDeserializer extends JsonDeserializer<SecurityUser> {

    @Override
    public SecurityUser deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode jsonNode = mapper.readTree(jsonParser);
        JsonNode userNode = readJsonNode(jsonNode, "user");


        int id = readJsonNode(userNode, "id").asInt();
        String username = readJsonNode(userNode, "username").asText();
        String email = readJsonNode(userNode, "email").asText();
        String password = readJsonNode(userNode, "password").asText();
        String authority = readJsonNode(userNode, "authority").asText();
        boolean enabled = readJsonNode(userNode, "enabled").asBoolean();
        User user = new User(id,username,email,password, authority,enabled);
        user.setPassword(null);
        return new SecurityUser(user);
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String field){
        return jsonNode.has(field) ? jsonNode.get(field) : MissingNode.getInstance();
    }

//    private Set<UserAuthority> getAuthorities(ObjectMapper objectMapper, JsonNode authoritiesNode) throws JsonParseException, JsonMappingException, IOException {
//        Set<UserAuthority> authorities = new HashSet<>();
//
//        if(authoritiesNode !=null){
//            if(authoritiesNode.isArray()){
//                for(JsonNode objNode : authoritiesNode){
//                    if(objNode.isArray()){
//                        ArrayNode arrayNode = (ArrayNode) objNode;
//                        for(JsonNode elementNode : arrayNode){
//                            UserAuthority simpleGrantedAuthority =
//                                    objectMapper.readValue(elementNode.traverse(objectMapper), UserAuthority.class);
//                            authorities.add(simpleGrantedAuthority);
//                        }
//                    }
//                }
//            }
//        }
//        return authorities;
//    }
}
