package com.travelapp.core.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GrantedAuthorityDeserializer extends JsonDeserializer<Collection<? extends GrantedAuthority>> {
    @Override
    public Collection<? extends GrantedAuthority> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        List<String> authorityList = p.readValueAs(List.class);  // Read JSON as a list of strings
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (String authority : authorityList) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));  // Convert each string to a SimpleGrantedAuthority
        }

        return grantedAuthorities;
    }
}
