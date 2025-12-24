package myy803.traineeship_app.controllers.searchstrategies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class PositionsSearchFactoryTest {
    @Mock
    private SearchBasedOnLocation searchBasedOnLocation;

    @Mock
    private SearchBasedOnInterests searchBasedOnInterests;

    @InjectMocks
    private PositionsSearchFactory factory;

    @Test
    void testCorrectReturnsStrategy(){
        // interests
        PositionsSearchStrategy strategy1 = factory.create("interests");
        assertEquals(searchBasedOnInterests, strategy1);

        // location
        PositionsSearchStrategy strategy2 = factory.create("location");
        assertEquals(searchBasedOnLocation, strategy2);


        // default behaviour location (switch)
        PositionsSearchStrategy strategy3 = factory.create("blabla");
        assertEquals(searchBasedOnLocation, strategy3);
    }
}
