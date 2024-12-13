package achraf.entities;

import java.util.HashMap;

public class Container {
    private static Container instance;
    private HashMap<String, Agent> agents;

    private Container() {
        agents = new HashMap<>();
    }

    public static synchronized Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    public void addAgent(String name, Agent agent) {
        agents.put(name, agent);
        System.out.println("Agent " + name + " ajouté.");
    }

    public void removeAgent(String name) {
        if (agents.remove(name) != null) {
            System.out.println("Agent " + name + " supprimé.");
        } else {
            System.out.println("Agent " + name + " introuvable.");
        }
    }

    public Agent getAgent(String name) {
        return agents.get(name);
    }
}
