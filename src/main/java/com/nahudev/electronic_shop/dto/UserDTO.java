package com.nahudev.electronic_shop.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private List<OrderDTO> orders;

    private CartDTO cart;

}
