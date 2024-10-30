package com.nahudev.electronic_shop.dto;

import com.nahudev.electronic_shop.model.Role;
import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
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

    private Collection<Role> roles = new HashSet<>();

}
