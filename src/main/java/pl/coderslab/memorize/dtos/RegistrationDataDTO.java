package pl.coderslab.memorize.dtos;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RegistrationDataDTO {

    //TODO: @Size blocks username with white spaces (for example "Stefan Paprota" was blocked, "Stefan" was OK)
    //TODO: to verify unique username
    @NotBlank @Size(min = 3, max = 12)
    private String username;
    //TODO: to verify unique email
    @NotBlank @Email
    private String email;
    @NotBlank @Size(min = 4, max = 12)
    private String password;
    //TODO: to verify the same password @ rePassword
    @NotBlank @Size(min = 4, max = 12)
    private String rePassword;
    @NotNull @AssertTrue
    private Boolean termsAcceptance;

}
