package myy803.traineeship_app.controllers.supervisorsearchstrategies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SupervisorAssignmentFactoryTest {

    @Mock
    private AssignmentBasedOnInterests assignmentBasedOnInterests;

    @Mock
    private AssignmentBasedOnLoad assignmentBasedOnLoad;

    @InjectMocks
    private SupervisorAssigmentFactory factory;

    @Test
    void testCorrectReturnStrategy(){
        // based on load
        SupervisorAssignmentStrategy strategy1 = factory.create("load");
        assertEquals(assignmentBasedOnLoad, strategy1);

        // based on interests
        SupervisorAssignmentStrategy strategy2 = factory.create("interests");
        assertEquals(assignmentBasedOnInterests, strategy2);

        // default
        SupervisorAssignmentStrategy strategy3 = factory.create("blabla");
        assertEquals(assignmentBasedOnLoad, strategy3);
    }
}
