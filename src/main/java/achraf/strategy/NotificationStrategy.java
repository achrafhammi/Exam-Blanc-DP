package achraf.strategy;

import achraf.entities.Transaction;

public interface NotificationStrategy {
    void processNotification(String agentObserver, Transaction transaction);
}
