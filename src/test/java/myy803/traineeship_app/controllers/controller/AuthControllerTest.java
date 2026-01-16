package myy803.traineeship_app.controllers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import myy803.traineeship_app.controllers.AuthController;
import myy803.traineeship_app.domain.User;
import myy803.traineeship_app.service.UserService;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @InjectMocks
    private AuthController controller;

    // login testing
    @Test
    void testLogin() {
        // ACT
        String viewName = controller.login();
        
        // ASSERT
        assertEquals("auth/login", viewName);
    }

    // user register testing
    @Test
    void testRegister() {
        // ACT
        String viewName = controller.register(model);
        
        // ASSERT
        verify(model).addAttribute(eq("user"), any(User.class));
        assertEquals("auth/register", viewName);
    }


    @Test
    void testRegisterUser_Success() {
        // ARRANGE
        User newUser = new User();
        newUser.setUsername("new_user");

        // mock
        when(userService.isUserPresent(newUser)).thenReturn(false);

        // ACT
        String viewName = controller.registerUser(newUser, model);

        // ASSERT
        verify(userService).saveUser(newUser);
        
        verify(model).addAttribute("successMessage", "User registered successfully!");
        
        assertEquals("auth/login", viewName);
    }

    // failed register (user already exists)
    @Test
    void testRegisterUser_UserExists() {
        // ARRANGE
        User existingUser = new User();
        existingUser.setUsername("existing_user");

        // mock
        when(userService.isUserPresent(existingUser)).thenReturn(true);

        // ACT
        String viewName = controller.registerUser(existingUser, model);

        // ASSERT (verify that the interraction did not happen with never() )
        verify(userService, never()).saveUser(existingUser);
        
        verify(model).addAttribute("successMessage", "User already registered!");
        
        assertEquals("auth/login", viewName);
    }
}