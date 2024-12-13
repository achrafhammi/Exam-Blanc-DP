package achraf.entities;

import achraf.aspectj.cachable.Cachable;
import achraf.aspectj.logs.Log;
import achraf.aspectj.security.SecuredBy;
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
    private String password;
    private String role;
    private List<Agent> observers = new ArrayList<>();
    private List<Transaction> transactions = new ArrayList<>();
    private NotificationStrategy notificationStrategy;

    public Agent(Long id, String name,String password,String role) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.password = password;
        notificationStrategy = new DefaultNotificationStrategy();
    }

    public String getName() {
        return name;
    }

    @SecuredBy(roles = "USER")
    public void showAllTransactions() {
        System.out.println("Les transactions de l'agent " + name + ": ");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public void setNotificationStrategy(NotificationStrategy notificationStrategy) {
        this.notificationStrategy = notificationStrategy;
    }

    public NotificationStrategy getNotificationStrategy() {
        return notificationStrategy;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Cachable
    public Transaction getLargestTransaction() {
        return transactions.stream()
                .max(Comparator.comparingDouble(Transaction::getMontant))
                .orElse(null);
    }

    @SecuredBy(roles = "ADMIN")
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
        notificationStrategy.processNotification(name, transaction);
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
            System.out.print("notification pour: "+ a.name+" | ");
            a.update(name, transaction);
        }
    }

    @Override
    public void update(String agentName, Transaction transaction) {
        System.out.println(agentName + " a fait une transaction: "+transaction);
    }
}
