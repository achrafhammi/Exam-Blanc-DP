package achraf.entities;

import achraf.observer.AgentObservable;
import achraf.observer.AgentObserver;
import achraf.strategy.DefaultNotificationStrategy;
import achraf.strategy.NotificationStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Agent implements AgentObservable, AgentObserver {
    private Long id;
    private String name;
    private List<Agent> observers = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private NotificationStrategy notificationStrategy = new DefaultNotificationStrategy();

    public String getName() {
        return "Nom d'agent " + name;
    }

    public void showAllTransactions() {
        System.out.println("Les transactions de l'agent " + name + ": ");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public Transaction getLargestTransaction() {
        return transactions.stream()
                .max(Comparator.comparingDouble(Transaction::getMontant))
                .orElse(null);
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
        notifyObservers(transaction);
    }

    @Override
    public void subscribe(Agent agentObserver) {
        observers.add(agentObserver);
    }

    @Override
    public void unsubscribe(Agent agentObserver) {
        observers.remove(agentObserver);

    }

    @Override
    public void notifyObservers(Transaction transaction) {
        for(Agent a : observers){
            a.update(a.name, transaction);
        }
    }

    @Override
    public void update(String agentName, Transaction transaction) {
        System.out.println(agentName + "a fait une transaction: "+transaction);
    }
}
