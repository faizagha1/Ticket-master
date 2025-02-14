package com.UserService.Dtos.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupResp {
    private String username;
    private String email;
    private String message;
}
