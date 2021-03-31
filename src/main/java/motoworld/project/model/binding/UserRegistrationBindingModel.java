package motoworld.project.model.binding;


import motoworld.project.util.validators.FieldMatch;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldMatch(
    first = "password",
    second = "confirmPassword"
)
public class UserRegistrationBindingModel {

  @NotEmpty
  @Size(min = 3)
  private String username;

  @NotEmpty
  @Size(min = 3)
  private String fullName;

  @NotEmpty
  @Size(min = 5, max = 20)
  private String password;

  @NotEmpty
  private String confirmPassword;

  public String getUsername() {
    return username;
  }

  public UserRegistrationBindingModel setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getFullName() {
    return fullName;
  }

  public UserRegistrationBindingModel setFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserRegistrationBindingModel setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public UserRegistrationBindingModel setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
    return this;
  }
}
