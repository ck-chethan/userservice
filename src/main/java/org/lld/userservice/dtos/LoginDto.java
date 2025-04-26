package org.lld.userservice.dtos;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.lld.userservice.models.BaseModel;
import org.lld.userservice.models.Token;
import org.lld.userservice.models.User;

import java.util.Date;

@Getter
@Setter
public class LoginDto {
    private String value;
    private UserDto userDto;
    private Date expiryDate;
    public static LoginDto from(Token token) {
        if (token == null) return null;

        LoginDto loginDto = new LoginDto();
        loginDto.setValue(token.getValue());
        loginDto.setUserDto(UserDto.from(token.getUser()));
        loginDto.setExpiryDate(token.getExpiryDate());
        return loginDto;
    }
}
