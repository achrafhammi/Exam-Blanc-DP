package achraf.strategy;

import achraf.entities.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HistoryNotificationStrategy implements NotificationStrategy {
    private List<Transaction> history = new ArrayList<>();
    @Override
    public void processNotification(String agentObserver, Transaction transaction) {
        history.add(transaction);
        System.out.println(transaction + " crée par "+ agentObserver + " est ajouté a l'historique");
    }

    public List<Transaction> getHistory() {
        return history;
    }
}
