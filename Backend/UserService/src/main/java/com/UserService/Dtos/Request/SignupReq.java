package com.UserService.Dtos.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupReq {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String profilePicture;
}
