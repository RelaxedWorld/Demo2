package com.example.demo.domains;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private String userName;
    private String userPwd;
    private String email;
}
