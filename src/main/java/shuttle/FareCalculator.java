package shuttle;
import java.util.Map;

public class FareCalculator {
    static final int BASE_FARE_CENTS = 200;
    static final double INCLUDED_KM = 5;
    static final int PER_EXTRA_KM_CENTS = 50;
    static final Map<String, Double> DISCOUNTS = Map.of(
            "adult", 1.0,
            "student", 0.5,
            "senior", 0.5,
            "child", 0.0);

    /**
     * Returns the shuttle fare in cents.
     *
     * Rules (the specification):
     *  - The base fare of 200 cents covers the first 5 km (including exactly 5 km).
     *  - Each additional km, or part of a km, costs 50 cents.
     *  - Students and seniors pay half. Children ride free.
     *  - A negative distance or an unknown rider type throws IllegalArgumentException.
     */
    public static int fareCents(double distanceKm, String rider) {
        if (distanceKm < 0) {
            throw new IllegalArgumentException("distance must be non-negative");
        }
        if (!DISCOUNTS.containsKey(rider)) {
            throw new IllegalArgumentException("unknown rider type: " + rider);
        }
        int extraKm = Math.max(0, (int) (distanceKm - INCLUDED_KM));
        int full = BASE_FARE_CENTS + extraKm * PER_EXTRA_KM_CENTS;
        return (int) Math.round(full * DISCOUNTS.get(rider));
    }
}
