package com.acornworks.customer.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
public class UserInfo {
    public UserInfo() {}

    public UserInfo(String userName) {
        this.userName = userName;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_code")
    @JsonProperty("user_code")
    private int userCode;

    @Column(name = "user_name")
    @JsonProperty("user_name")
    private String userName;
}
