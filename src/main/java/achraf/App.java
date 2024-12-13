package achraf;

import achraf.entities.Transaction;

import java.time.LocalDate;

public class App 
{
    public static void main( String[] args ) {
        Transaction transaction = Transaction.builder()
                .id("123")
                .date(LocalDate.now().plusYears(54))
                .montant(256)
                .build();

        System.out.println(transaction);
    }
}
