package itis.khabibullina.models;

public class Program {
    private int id;
    private String name;
    private int peopleCount;

    public Program(int id, String name, int peopleCount) {
        this.id = id;
        this.name = name;
        this.peopleCount = peopleCount;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
