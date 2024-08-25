package com.project.tacocloud.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TacoOrder {

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryZip;

    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    private void addTacos(Taco taco) {
        this.tacos.add(taco);
    }
}









