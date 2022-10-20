package com.acornworks.customer.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
public class UserData {
    public UserData() {}

    public UserData(String userName, float price) {
        this.userName = userName;
        this.price = price;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "data_index")
    @JsonProperty("data_index")
    private int dataIndex;

    @Column(name = "user_name")
    @JsonProperty("user_name")
    private String userName;

    @Column(name = "price")
    @JsonProperty("price")
    private float price;
}
