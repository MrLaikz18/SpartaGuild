package fr.mrlaikz.spartaguild;

public enum Rank {

    MEMBER("membre"),
    MODERATOR("modo"),
    ADMINS("admin"),
    OWNER("fondateur");

    private String name;

    Rank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
