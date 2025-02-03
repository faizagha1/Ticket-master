package com.UserService.Dtos.Request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SigninReq {
    private String email;
    private String password;
}
