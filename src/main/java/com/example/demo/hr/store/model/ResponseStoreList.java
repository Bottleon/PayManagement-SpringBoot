package com.example.demo.hr.store.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseStoreList {
    private List<Store> stores= new ArrayList<>();
}
