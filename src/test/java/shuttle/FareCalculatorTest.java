package shuttle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.junit.jupiter.api.Test;

public class FareCalculatorTest {

    @Test
    void shortTripAdultPaysBaseFare() {
        // Arrange
        double distanceKm = 3;
        // Act
        int result = FareCalculator.fareCents(distanceKm, "adult");
        // Assert
        assertEquals(200, result);
    }

    //Write 6 more tests
    @Test
    void exactlyFiveKmIsStillBaseFare() {
        assertEquals(200, FareCalculator.fareCents(5, "adult"));
    }

    @Test
    void eachExtraKmCosts50Cents() {
        assertEquals(350, FareCalculator.fareCents(8, "adult"));
    }

    @Test
    void studentPaysHalf() {
        assertEquals(175, FareCalculator.fareCents(8, "student"));
    }

    @Test
    void childRidesFree() {
        assertEquals(0, FareCalculator.fareCents(20, "child"));
    }

    @Test
    void negativeDistanceThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> FareCalculator.fareCents(-1, "adult"));
    }

    @Test
    void unknownRiderTypeThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> FareCalculator.fareCents(3, "pilot"));
    }

    //Find a bug with a test
    @Test
    void partOfAKmIsChargedAsAFullKm() {
        assertEquals(250, FareCalculator.fareCents(5.5, "adult"));
    }

    @ParameterizedTest(name = "{0} km, {1} -> {2} cents")
    @CsvSource({
            "0,   adult,   200",
            "5,   adult,   200",
            "5.1, adult,   250",
            "6,   adult,   250",
            "10,  student, 225",
            "10,  senior,  225",
            "100, child,   0"
    })
    void fareTable(double distanceKm, String rider, int expected) {
        assertEquals(expected, FareCalculator.fareCents(distanceKm, rider));
    }
}
