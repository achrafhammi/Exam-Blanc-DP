package achraf.entities;

import achraf.monitors.Hdmi;
import java.util.HashMap;

public class Container {
    private static Container instance;
    private HashMap<String, Agent> agents;
    private Hdmi hdmiCable;

    private Container() {
        agents = new HashMap<>();
    }

    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }

    public void setHdmiCable(Hdmi hdmiCable) {
        this.hdmiCable = hdmiCable;
    }

    public void addAgent(String name, Agent agent) {
        agents.put(name, agent);
        hdmiCable.showMessage("Agent " + name + " ajouté.");
    }

    public void removeAgent(String name) {
        if (agents.remove(name) != null) {
            hdmiCable.showMessage("Agent " + name + " supprimé.");
        } else {
            hdmiCable.showMessage("Agent " + name + " introuvable.");
        }
    }

    public Agent getAgent(String name) {
        if(agents.containsKey(name)){
            hdmiCable.showMessage("Agent " + name + " trouvé.");
            return agents.get(name);
        }
        hdmiCable.showMessage("Agent " + name + " introuvable.");
        return agents.get(null);
    }
}
