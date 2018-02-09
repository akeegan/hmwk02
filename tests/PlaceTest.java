import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceTest {

    Place test = new Place("Saint Joseph","MO","64504",66325, 39.76,-94.85,300);
    @Test
    void getDistanceFromZipMiles() {
        assertEquals(186.411,test.getDistanceFromZipMiles());
    }
}