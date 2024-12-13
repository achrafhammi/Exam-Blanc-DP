package achraf.observer;

import achraf.entities.Transaction;

public interface AgentObserver {
    void update(String agentName, Transaction transaction);
}
