package achraf.observer;

import achraf.entities.Agent;
import achraf.entities.Transaction;

public interface AgentObservable {
    void subscribe(Agent observer);
    void unsubscribe(Agent observer);
    void notifyObservers(Transaction transaction);
}
