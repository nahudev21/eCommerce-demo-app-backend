package com.nahudev.electronic_shop.service.user;

import com.nahudev.electronic_shop.dto.UserDTO;
import com.nahudev.electronic_shop.model.User;
import com.nahudev.electronic_shop.request.CreateUserRequest;
import com.nahudev.electronic_shop.request.UpdateUserRequest;

import java.util.List;

public interface IUserService {

    public User getUserById(Long userId);

    public List<User> getListUsers();

    public User createUser(CreateUserRequest request);

    public User updateUser(UpdateUserRequest request, Long userId);

    public void deleteUser(Long userId);

    public UserDTO convertToDto(User user);

    List<UserDTO> convertListToDto(List<User> users);

    public User getAuthenticatedUser();
}
