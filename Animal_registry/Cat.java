package Animal_registry;

import java.time.LocalDate;

public class Cat extends DomesticAnimal {

    public Cat(String name, LocalDate birth_date, String command) {
        super(name, birth_date, command);
    }

    @Override
    public void doCommand(String command) {
        System.out.println(getName() + " выполняет команду: " + command);
        setCommand(command); 
    }


    @Override
    public String getCommand() {
        return super.getCommand();
    }
}