/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xlink.springboot.models;

import lombok.Getter;
import lombok.Setter;



@Setter
@Getter
public class Account {
    private Long id;
    private String name;
    private String description;
}
