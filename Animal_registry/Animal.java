package Animal_registry;

import java.time.LocalDate;

public abstract class Animal {

    private String name;
    private LocalDate birth_date;
    public String command;

    public Animal(String name, LocalDate birth_date, String command) {
        this.name = name;
        this.birth_date = birth_date;
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public String getCommand() {
        return command;
    }

    public abstract void doCommand(String command);
}
