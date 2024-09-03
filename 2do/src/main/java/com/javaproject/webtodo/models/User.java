package com.javaproject.webtodo.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.javaproject.webtodo.dtos.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Task> tasks;

    private User(UserDTO userDTO) {
        this.username = userDTO.username();
        this.password = userDTO.password();
        this.name = userDTO.name();
        this.email = userDTO.email();
        this.phone = userDTO.phone();

    }
}
