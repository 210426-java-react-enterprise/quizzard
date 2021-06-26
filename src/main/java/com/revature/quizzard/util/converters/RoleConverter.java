package com.revature.quizzard.util.converters;

import com.revature.quizzard.users.AppUser;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Component
@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<AppUser.Role, String> {

    @Override
    public String convertToDatabaseColumn(AppUser.Role role) {
        if (role == null) {
            throw new IllegalArgumentException();
        }
        return role.toString();
    }

    @Override
    public AppUser.Role  convertToEntityAttribute(String roleName) {
        return Stream.of(AppUser.Role.values())
                     .filter(r -> r.toString().equals(roleName))
                     .findFirst()
                     .orElseThrow(IllegalArgumentException::new);
    }

}
