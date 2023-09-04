import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Ticket {
    private final BigDecimal costPerKm = BigDecimal.valueOf(0.21);
    private final BigDecimal OVER_65_DISCOUNT = BigDecimal.valueOf(40).setScale(2, RoundingMode.HALF_UP);
    private final BigDecimal UNDER_18_DISCOUNT = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_UP);

    private final int FLEXIBLE_DAYS = 90;
    private final int NORMAL_DAYS = 30;

    private int km;
    private int passengerAge;
    private LocalDate date;
    private boolean flexible;

    public Ticket(int km, int passengerAge, LocalDate date, boolean flexible) {
        this.km = isValidKm(km);
        this.passengerAge = isValidAge(passengerAge);
        this.date = date;
        this.flexible = flexible;
    }

    public BigDecimal getPrice() {
        BigDecimal price = costPerKm.multiply(BigDecimal.valueOf(km));

        if (flexible)
            price = price.add(price.multiply(BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP))).setScale(2, RoundingMode.HALF_UP);

        return applyDiscount(price);
    }

    private LocalDate getExpirationDate() {
        return date.plusDays(flexible ? FLEXIBLE_DAYS : NORMAL_DAYS);
    }

    private BigDecimal applyDiscount(BigDecimal price) {
        if (passengerAge > 65) {
            BigDecimal percentage =
                    OVER_65_DISCOUNT.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

            return price.subtract(price.multiply(percentage)).setScale(2, RoundingMode.HALF_UP);
        } else if (passengerAge < 18) {

            BigDecimal percentage =
                    UNDER_18_DISCOUNT.divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

            return price.subtract(price.multiply(percentage)).setScale(2, RoundingMode.HALF_UP);
        } else return price;
    }

    private int isValidKm(int km) throws IllegalArgumentException {
        if (km > 0) return km;
        else throw new IllegalArgumentException("Km must be greater than 0");
    }

    private int isValidAge(int age) throws IllegalArgumentException {
        if (age > 0) return age;
        else throw new IllegalArgumentException("Age must be greater than 0");
    }

    @Override
    public String toString() {
        return "Ticket{" + "\n"
                + "\tDistanza: " + km + " chilometri" + "\n"
                + "\tEtà passeggero: " + passengerAge + " anni" + "\n"
                + "\tPrezzo del biglietto: " + getPrice() + "€" + "\n"
                + "\tData di scadenza: " + getExpirationDate() + "\n"
                + "}";
    }
}
