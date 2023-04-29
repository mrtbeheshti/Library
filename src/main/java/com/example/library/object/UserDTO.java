package com.example.library.object;

import com.example.library.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class UserDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @JsonProperty("phone_number")
    @Pattern(regexp = "^((\\+|00)98|0)?(9\\d{9})$", message = "Phone number is not valid")
    private String phoneNumber;

    @NotNull(message = "First name is required")
    @JsonProperty("first_name")
    private String firstName;

    @NotNull(message = "Last name is required")
    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = "Birthdate is required")
    @JsonProperty("birthdate")
    private LocalDate birthdate;

    @JsonIgnore
    @JsonProperty(defaultValue = "0")
    private int reserves;

    public static UserDTO from(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthdate(user.getBirthdate())
                .reserves(user.getReserves())
                .build();
    }
}
