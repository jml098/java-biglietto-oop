public class Travel {
    private int id;
    private int km;
    private String from;
    private String to;

    public Travel(int id, int km, String from, String to) {
        this.id = id;
        this.km = km;
        this.from = from;
        this.to = to;
    }


    public int getId() {
        return id;
    }

    public int getKm() {
        return km;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return getFrom() + " ---> " + getTo() + " | " + getKm() + " chilometri.";
    }
}
