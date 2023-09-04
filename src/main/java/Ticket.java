import java.math.BigDecimal;
import java.math.RoundingMode;

public class Ticket {
    private final BigDecimal costPerKm = new BigDecimal("0.21").setScale(2, RoundingMode.HALF_UP);
    private final BigDecimal OVER_65_DISCOUNT = new BigDecimal("40").setScale(2, RoundingMode.HALF_UP);
    private final BigDecimal UNDER_18_DISCOUNT = new BigDecimal("20").setScale(2, RoundingMode.HALF_UP);

    private int km;
    private int passengerAge;

    public Ticket(int km, int passengerAge) {
        this.km = km;
        this.passengerAge = passengerAge;
    }

    public BigDecimal getPrice() {
        BigDecimal price = costPerKm.multiply(BigDecimal.valueOf(km));
    }

    private BigDecimal applyDiscount(BigDecimal price) {

    }

    private int isValidKm(int km) throws IllegalArgumentException {
        if (km > 0) return km;
        else throw new IllegalArgumentException("Km must be greater than 0");
    }

    private int isValidAge(int age) throws IllegalArgumentException {
        if (age > 0) return age;
        else throw new IllegalArgumentException("Age must be greater than 0");
    }
}
