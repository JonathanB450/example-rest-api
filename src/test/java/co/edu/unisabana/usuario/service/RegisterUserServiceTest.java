package co.edu.unisabana.usuario.service;

import co.edu.unisabana.usuario.service.model.User;
import co.edu.unisabana.usuario.service.port.RegisterUserPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RegisterUserServiceTest {


  @InjectMocks
  private RegisterUserService service;

  @Mock
  private RegisterUserPort registerUserPort;

  @Test
  public void Given_name_isEmpty_When_RegisterUser_then_throwException(){
    User user = new User();
    user.setName("");
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      service.registerUser(user);
    });
  }

  @Test
  public void Given_age_is_minor_18_When_RegisterUser_then_throwException(){
    User user = new User();
    user.setName("Jonathan");
    user.setAge(16);
    Assertions.assertThrows(RuntimeException.class, () -> {
      service.registerUser(user);
    });
  }

  @Test
  public void Given_newUser_When_RegisterUser_Then_returnCreatedUser(){
    User user = new User();
    user.setName("Jonathan");
    user.setAge(20);
    boolean result = service.registerUser(user);
    Mockito.verify(registerUserPort).addNewUser(user);
  }

  @Test
  public void Give_dontSendCorrectIinformantion_When_Registeruser_Then_throwIllegalArgument() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      service.registerUser(new User());
    });
  }

  @Test
  public void Give_SendCorrectIinformantion_When_Registeruser_Then_returnFalse() {
    User user = new User();
    user.setName("Daniel");
    Mockito.when(registerUserPort.addNewUser(user)).thenReturn(true);
    boolean result = service.registerUser(user);
    Mockito.verify(registerUserPort).addNewUser(user);
    Assertions.assertTrue(result);
  }


}
