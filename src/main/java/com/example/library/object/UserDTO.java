package com.example.library.object;

import com.example.library.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
@ApiModel(value = "User", description = "User DTO uses in controller")
public class UserDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty("user id.")
    private long id;

    @JsonProperty("phone_number")
    @Pattern(regexp = "^((\\+|00)98|0)?(9\\d{9})$", message = "Phone number is not valid")
    @ApiModelProperty("user phone number, must be valid and unique.")
    private String phoneNumber;

    @NotNull(message = "First name is required")
    @JsonProperty("first_name")
    @ApiModelProperty( "user first name")
    private String firstName;

    @NotNull(message = "Last name is required")
    @JsonProperty("last_name")
    @ApiModelProperty("user last name.")
    private String lastName;

    @NotNull(message = "Birthdate is required")
    @JsonProperty("birthdate")
    @ApiModelProperty("birthdate of user, in format YYYY-MM-DD.")
    private LocalDate birthdate;

    @JsonIgnore
    @JsonProperty(defaultValue = "0")
    @ApiModelProperty("number of user reserves.")
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
