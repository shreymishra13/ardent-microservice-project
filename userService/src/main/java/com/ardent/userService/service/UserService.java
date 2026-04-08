package com.ardent.userService.service;


import com.ardent.userService.dto.UserDTO;
import com.ardent.userService.entity.Address;
import com.ardent.userService.entity.Gender;
import com.ardent.userService.entity.State;
import com.ardent.userService.entity.User;
import com.ardent.userService.exception.InvalidUserDetailException;
import com.ardent.userService.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<String> addUserDetail(@Valid UserDTO userDTO) {
        if(userDTO == null){
            throw new InvalidUserDetailException("User is null");
        }

        Address address = new Address();
        address.setAddressLine(userDTO.getAddress().getAddressLine());
        address.setCity(userDTO.getAddress().getCity());
        address.setState(State.valueOf(userDTO.getAddress().getState()));
        address.setCountry(userDTO.getAddress().getCountry());
        address.setPincode(userDTO.getAddress().getPincode());

        // Convert DTO → Entity
        User user = User.builder()
                .firstName(userDTO.getFirstName())
                .middleName(userDTO.getMiddleName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .gender(Gender.valueOf(userDTO.getGender().toUpperCase())) // String → Enum
                .address(address)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok().body("User Added successfully");

    }
}
