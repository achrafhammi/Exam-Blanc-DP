package achraf.aspectj.security;

import achraf.entities.Agent;

import java.util.*;

public class SecurityContext {
    private static SecurityContext instance;
    private String currentUsername;
    private String currentUserRole;
    private boolean logged;
    private List<Agent> usersDb = new ArrayList<>();

    private SecurityContext() {
        usersDb.add(new Agent(1L, "achraf", "1234", "USER"));
        usersDb.add(new Agent(2L, "rachid", "1234", "ADMIN"));
        usersDb.add(new Agent(4L, "mohammed", "1234", "USER"));
        usersDb.add(new Agent(5L, "abdelmoula", "1234", "ADMIN"));
    }

    public static SecurityContext getInstance() {
        if (instance == null) {
            instance = new SecurityContext();
        }
        return instance;
    }

    public void login(String username, String password) {
        for(Agent a:usersDb){
            if(Objects.equals(a.getName(), username) && Objects.equals(a.getPassword(), password)){
                this.currentUsername = username;
                this.currentUserRole = a.getRole();
                this.logged=!this.logged;
                return;
            }
        }
        System.out.println("user n'existe pas!!!!");
    }

    public void logout() {
        this.currentUsername = "";
        this.currentUserRole = "";
        this.logged=!this.logged;
    }

    public boolean hasRole(String role) {
        return currentUserRole != null && currentUserRole.contains(role);
    }

    public boolean hasAnyRole(String[] requiredRoles) {
        if (currentUserRole == null) return false;
        return Arrays.stream(requiredRoles)
                .anyMatch(currentUserRole::contains);
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}