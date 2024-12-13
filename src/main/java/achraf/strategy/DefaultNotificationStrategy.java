package achraf.strategy;

import achraf.entities.Transaction;

public class DefaultNotificationStrategy implements NotificationStrategy {
    @Override
    public void processNotification(String agentName, Transaction transaction) {
        System.out.println(agentName + " a fait la transaction "+ transaction);
    }
}
