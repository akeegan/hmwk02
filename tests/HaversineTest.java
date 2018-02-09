import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HaversineTest {

    Haversine test = new Haversine(39.76,-94.85,40.83,-95.3);
    Haversine test2 = new Haversine(39.76,-94.85,41.43,-120.53);

    @Test
    void calcHaversine() {
        assertEquals(125,test.calcHaversine());
        assertEquals(2168,test2.calcHaversine());


    }
}