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
    private UserService userService; // Ο "Ψεύτικος" Συνεργάτης που διαχειρίζεται τους χρήστες

    @Mock
    private Model model; // Το ψεύτικο Model για να στέλνουμε μηνύματα στο HTML

    @InjectMocks
    private AuthController controller; // Ο Controller που ελέγχουμε

    // --- ΤΕΣΤ 1: Εμφάνιση Σελίδας Login ---
    @Test
    void testLogin() {
        // ACT
        String viewName = controller.login();
        
        // ASSERT
        assertEquals("auth/login", viewName);
    }

    // --- ΤΕΣΤ 2: Εμφάνιση Σελίδας Register ---
    @Test
    void testRegister() {
        // ACT
        String viewName = controller.register(model);
        
        // ASSERT
        // Ελέγχουμε ότι πρόσθεσε έναν κενό "user" στο μοντέλο (γραμμή 26 του κώδικά σου)
        verify(model).addAttribute(eq("user"), any(User.class));
        assertEquals("auth/register", viewName);
    }

    // --- ΤΕΣΤ 3: Επιτυχημένη Εγγραφή Χρήστη ---
    @Test
    void testRegisterUser_Success() {
        // ARRANGE
        User newUser = new User();
        newUser.setUsername("new_user");

        // "Εκπαιδεύουμε" το mock: Του λέμε ότι ο χρήστης ΔΕΝ υπάρχει
        when(userService.isUserPresent(newUser)).thenReturn(false);

        // ACT
        String viewName = controller.registerUser(newUser, model);

        // ASSERT
        // 1. Πρέπει να καλέσει την saveUser (Αυτό είναι το πιο σημαντικό!)
        verify(userService).saveUser(newUser);
        
        // 2. Πρέπει να βάλει μήνυμα επιτυχίας
        verify(model).addAttribute("successMessage", "User registered successfully!");
        
        // 3. Πρέπει να μας πάει στο login
        assertEquals("auth/login", viewName);
    }

    // --- ΤΕΣΤ 4: Αποτυχημένη Εγγραφή (Ο χρήστης υπάρχει ήδη) ---
    @Test
    void testRegisterUser_UserExists() {
        // ARRANGE
        User existingUser = new User();
        existingUser.setUsername("existing_user");

        // "Εκπαιδεύουμε" το mock: Του λέμε ότι ο χρήστης ΥΠΑΡΧΕΙ ήδη
        when(userService.isUserPresent(existingUser)).thenReturn(true);

        // ACT
        String viewName = controller.registerUser(existingUser, model);

        // ASSERT
        // 1. ΠΡΟΣΟΧΗ: ΔΕΝ πρέπει να καλέσει την saveUser!
        verify(userService, never()).saveUser(existingUser);
        
        // 2. Πρέπει να βάλει μήνυμα ότι ο χρήστης υπάρχει
        verify(model).addAttribute("successMessage", "User already registered!");
        
        // 3. Πρέπει να μας πάει πάλι στο login
        assertEquals("auth/login", viewName);
    }
}