package Animal_registry;

import java.time.LocalDate;

public abstract class DomesticAnimal extends Animal{

    public DomesticAnimal(String name, LocalDate birth_date, String command){
        super(name, birth_date, command);

    }

    public void setCommand(String command) {
        this.command = command;
    }

}
