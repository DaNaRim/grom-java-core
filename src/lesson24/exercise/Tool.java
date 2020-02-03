package lesson24.exercise;

public class Tool {
    private String name;
    private String description;

    public Tool(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
