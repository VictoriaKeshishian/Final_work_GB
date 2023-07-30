package Animal_registry;

import java.time.LocalDate;

public abstract class BaggageAnimal extends Animal{

    public BaggageAnimal(String name, LocalDate birth_date, String command){
        super(name, birth_date, command);
    }

    public void setCommand(String command) {
        this.command = command;
    }

}