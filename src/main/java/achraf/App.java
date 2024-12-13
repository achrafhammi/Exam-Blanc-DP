package achraf;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import achraf.adapter.HdmiVgaAdapter;
import achraf.aspectj.security.SecurityContext;
import achraf.entities.*;
import achraf.monitors.Hdmi;
import achraf.monitors.HdmiDisplay;
import achraf.monitors.VgaDisplay;
import achraf.strategy.HistoryNotificationStrategy;
import achraf.strategy.ScoringNotificationStrategy;

import static achraf.entities.TransactionType.ACHAT;
import static achraf.entities.TransactionType.VENTE;

public class App {
    public static void main(String[] args) {
        // test pour builder question 1:
        //testTransactionBuilder();

        // test pour les operations de agent avec leur design patterns:
        //testAgentOperations();
        //testAgentSubscription();

        // test pour strategy pattern
        //testNotificationStrategies();

        // Test Container Operations et adapter design pattern
        //testAgentContainer();

        // Test Security Aspect
        testSecurityAspect();

        // Test Logging Aspect
       // testLoggingAspect();

        // Test Caching Aspect
        //testCachingAspect();
    }

    private static void testTransactionBuilder() {
        System.out.println("=== test de builder ===");
        Transaction transaction = Transaction.builder()
                .id("01")
                .date(LocalDate.now())
                .montant(1000.0)
                .build();

        System.out.println("transaction crée: " + transaction);
    }

    private static void testAgentOperations() {
        System.out.println("=== test de les operations d'agent ===");
        Agent agent = new Agent(1L, "achraf", "1234", "1234");
        Transaction t1 = Transaction.builder()
                .id("01")
                .date(LocalDate.now())
                .montant(1000.0)
                .transactionType(VENTE)
                .build();
        Transaction t2 = Transaction.builder()
                .id("02")
                .date(LocalDate.now().plusWeeks(5))
                .montant(120)
                .transactionType(ACHAT)
                .build();

        agent.addTransaction(t1);
        agent.addTransaction(t2);

        System.out.println(agent.getName());
        agent.showAllTransactions();
        Transaction maxTransaction = agent.getLargestTransaction();
        System.out.println("Max Montant Transaction: " + maxTransaction);
    }

    private static void testAgentSubscription() {
        System.out.println("\n=== Testing observer pattern sur agent ===");
        Agent agent1 = new Agent(1L, "achraf", "1234", "USER");
        Agent agent2 = new Agent(2L, "rachid", "1234", "ADMIN");
        Agent agent3 = new Agent(4L, "mohammed", "1234", "USER");

        agent1.subscribe(agent2);
        agent1.subscribe(agent3);

        Transaction transaction = Transaction.builder()
                .id("01")
                .date(LocalDate.now())
                .montant(1000.0)
                .transactionType(VENTE)
                .build();

        agent1.addTransaction(transaction);
        agent1.unsubscribe(agent3);
        System.out.println("on supprime mohammed de la liste des observers");
        agent1.addTransaction(transaction);
    }

    private static void testNotificationStrategies() {
        System.out.println("=== Testing Notification Strategies ===");
        Agent agent = new Agent(1L, "achraf", "1234", "USER");
        Transaction transaction1 = Transaction.builder()
                .id("01")
                .date(LocalDate.now())
                .montant(1000.0)
                .transactionType(VENTE)
                .build();
        Transaction transaction2 = Transaction.builder()
                .id("02")
                .date(LocalDate.now().plusWeeks(5))
                .montant(120)
                .transactionType(ACHAT)
                .build();
        // Default strategy
        agent.addTransaction(transaction1);
        // Scoring Strategy
        agent.setNotificationStrategy(new ScoringNotificationStrategy());
        agent.addTransaction(transaction1);
        agent.addTransaction(transaction2);
        // History Strategy
        agent.setNotificationStrategy(new HistoryNotificationStrategy());
        agent.addTransaction(transaction1);
        agent.addTransaction(transaction2);
    }

    private static void testAgentContainer() {
        System.out.println("=== Testing Container ===");
        Container container = Container.getInstance();
        Hdmi hdmiDisplay = new HdmiDisplay();
        Hdmi vgaAdapter = new HdmiVgaAdapter(new VgaDisplay());

        Agent agent1 = new Agent(1L, "achraf", "1234", "USER");
        Agent agent2 = new Agent(2L, "rachid", "1234", "ADMIN");

        container.setHdmiCable(hdmiDisplay);
        container.addAgent(agent1.getName(), agent1);
        container.setHdmiCable(vgaAdapter);
        container.addAgent(agent2.getName(), agent2);

        container.setHdmiCable(hdmiDisplay);
        Agent retrievedAgent = container.getAgent("achraf");
        container.setHdmiCable(vgaAdapter);
        container.removeAgent("rachid");
    }
//
    // Test Security Aspect
    private static void testSecurityAspect() {
        System.out.println("=== Testing Security Aspect ===");
        SecurityContext securityContext = SecurityContext.getInstance();

        // Test with ADMIN role
        Agent agent1 = new Agent(1L, "achraf", "1234", "USER");
        Agent agent2 = new Agent(2L, "rachid", "1234", "ADMIN");
        securityContext.login("achraf", "1234");
        Transaction transaction = Transaction.builder()
                .id("01")
                .date(LocalDate.now())
                .montant(1000.0)
                .transactionType(VENTE)
                .build();
        // ca va pas passer car agent1 est un USER
        //agent1.addTransaction(transaction);
        securityContext.logout();
        securityContext.login("aaa", "453");
        securityContext.login("rachid", "1234");
        // ca va  passer car agent2 est un admin
        agent2.addTransaction(transaction);
    }
//
//    // Test Logging Aspect
    private static void testLoggingAspect() {
        System.out.println("=== Testing Logging Aspect ===");
        Agent agent1 = new Agent(1L, "achraf", "1234", "USER");
        Transaction transaction1 = Transaction.builder()
                .id("01")
                .date(LocalDate.now())
                .montant(1000.0)
                .transactionType(VENTE)
                .build();
        Transaction transaction2 = Transaction.builder()
                .id("01")
                .date(LocalDate.now())
                .montant(510.0)
                .transactionType(ACHAT)
                .build();
        // method a l'annotation de log
        agent1.addTransaction(transaction1);
        agent1.addTransaction(transaction2);
        System.out.println("largest transaction: "+agent1.getLargestTransaction());
    }

    private static void testCachingAspect() {
        System.out.println("=== Testing Caching Aspect ===");
        Agent agent1 = new Agent(1L, "achraf", "1234", "USER");
        Transaction transaction1 = Transaction.builder()
                .id("01")
                .date(LocalDate.now())
                .montant(1000.0)
                .transactionType(VENTE)
                .build();
        Transaction transaction2 = Transaction.builder()
                .id("01")
                .date(LocalDate.now())
                .montant(510.0)
                .transactionType(ACHAT)
                .build();
        agent1.addTransaction(transaction1);
        agent1.addTransaction(transaction2);
        // il va calculer premier fois, puis deuxieme fois il utilise cache
        Transaction maxTransaction1 = agent1.getLargestTransaction();
        Transaction maxTransaction2 = agent1.getLargestTransaction();

        System.out.println("Max Transaction: " + maxTransaction1);
    }
}
