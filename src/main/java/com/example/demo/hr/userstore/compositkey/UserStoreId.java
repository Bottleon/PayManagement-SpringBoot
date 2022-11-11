package com.example.demo.hr.userstore.compositkey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStoreId implements Serializable {

    private String user;
    private Long store;
}
