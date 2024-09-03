package com.javaproject.webtodo.dtos;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


public record UserDTO(@NotBlank @Column(unique = true) String username,
                      @NotBlank @Column(unique = true) @Size(min = 5) String password,
                      @NotBlank @Column(unique = true) @Size(max = 10, message = "Phone number must be at most 10 digits")
                      @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits") String phone,
                      @Pattern(regexp = "^[a-zA-Z0-9. _%+-]+@[a-zA-Z0-9. -]+\\\\", message = "Email not valid") @Column(unique = true) String email,
                      @Column(unique = true) String name)
{

}
