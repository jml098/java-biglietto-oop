import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);



        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_treni", "jaita91", "JAITA91")) {

            String query = """
                    SELECT *
                    FROM tratte t
                    """;

            PreparedStatement prepareStatement = connection.prepareStatement(query);

            boolean exit = false;
            do {
                ArrayList<Travel> travels = new ArrayList<>();

                try (ResultSet rs = prepareStatement.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        int km = rs.getInt("km");
                        String from = rs.getString("partenza");
                        String to = rs.getString("arrivo");

                        travels.add(new Travel(id, km, from, to));
                    }
                } catch (SQLException e) {
                    System.out.println("Errore nell'iterazione del ResultSet.");
                }

                System.out.println("Tratte disponibili: ");

                for (int i = 0; i < travels.size(); i++) {
                    System.out.println(i + " - " + travels.get(i));
                }

                System.out.print("Seleziona tratta: ");
                int selection = Integer.parseInt(scanner.nextLine());

                System.out.print("Inserisci Età: ");
                int age = Integer.parseInt(scanner.nextLine());

                System.out.print("Inserisci Flessibile (si/no): ");
                boolean flexible = scanner.nextLine().equalsIgnoreCase("si");

                try {
                    Ticket ticket = new Ticket(travels.get(selection).getKm(), age, LocalDate.now(), flexible);

                    System.out.println(ticket);
                    exit = true;
                } catch (RuntimeException e) {
                    System.out.println("Hai inserito dati non validi, riprova.");
                }
            } while (!exit);




        } catch (SQLException e) {
            System.out.println("Connessione al database non riuscita.");
        }


    }
}
