package achraf.strategy;

import achraf.entities.Transaction;
import static achraf.entities.TransactionType.VENTE;

public class ScoringNotificationStrategy implements NotificationStrategy {
    private double solde = 0;
    @Override
    public void processNotification(String agentObserver, Transaction transaction) {
        if(transaction.getTransactionType().equals(VENTE)){
            solde+=transaction.getMontant();
        }else{
            solde-=transaction.getMontant();
        }
        System.out.println("Nouveau balance: "+ solde);
    }
    public double getSolde() {
        return solde;
    }
}
