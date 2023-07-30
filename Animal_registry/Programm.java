package Animal_registry;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programm {

    private Map<String, List<Animal>> animalsByClass;

    public Programm() {
        animalsByClass = new HashMap<>();
    }

    public void addAnimal() {
        Scanner scanner = new Scanner(System.in, "UTF-8");

        System.out.println("Введите имя животного: ");
        String name = scanner.nextLine();

        System.out.print("Введите дату рождения животного в формате (гггг-мм-дд): ");
        String birthDateStr = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(birthDateStr);

        System.out.print("Выберите класс животного (например, Dog, Cat, и т.д.): ");
        String className = scanner.nextLine();

        if (!name.isEmpty() && !birthDateStr.isEmpty() && !className.isEmpty()) {
            try (Counter counter = new Counter()) {
                counter.add(); 
                Animal animal;
                switch (className) {
                    case "Dog":
                        animal = new Dog(name, birthDate, className);
                        break;
                    case "Cat":
                        animal = new Dog(name, birthDate, className);
                        break;
                    case "Hamster":
                        animal = new Dog(name, birthDate, className);
                        break;
                    case "Horse":
                        animal = new Dog(name, birthDate, className);
                        break;
                    case "Donkey":
                        animal = new Dog(name, birthDate, className);
                        break;
                    case "Camel":
                        animal = new Dog(name, birthDate, className);
                        break;
                    default:
                        System.out.println("_____________________________________");
                        System.out.println("Выбранный класс не поддерживается.");
                        return;
                }

        animalsByClass.computeIfAbsent(className, k -> new ArrayList<>()).add(animal);
        System.out.println("_____________________________________");
        System.out.println("Животное успешно добавлено в реестр.");
           }catch (Exception e) {
        System.out.println("Ошибка при добавлении животного: " + e.getMessage());
    }
} else {
    System.out.println("Не все поля заполнены. Животное не добавлено в реестр.");
}
    }

    public void classifyAnimal() {
        Scanner scanner = new Scanner(System.in, "UTF-8");

        System.out.println("Введите имя животного: ");
        String name = scanner.nextLine();

        String className = "Неизвестный тип";
        for (Map.Entry<String, List<Animal>> entry : animalsByClass.entrySet()) {
            List<Animal> animals = entry.getValue();
            for (Animal animal : animals) {
                if (animal.getName().equals(name)) {
                    className = entry.getKey();
                    break;
                }
            }
        }
        System.out.println("_____________________________________");
        System.out.println("Животное " + name + " относится к классу: " + className);
    }

    public void listCommands() {
        Scanner scanner = new Scanner(System.in, "UTF-8");

        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        System.out.print("Введите класс животного (например, Dog, Cat, и т.д.): ");
        String className = scanner.nextLine();

        List<Animal> animals = animalsByClass.getOrDefault(className, new ArrayList<>());
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                System.out.println("_____________________________________");
                System.out.println("Животное: " + animal.getName() + ", Дата рождения: " + animal.getBirth_date());
                System.out.println("Команды: " + animal.getCommand());
                break;
            }
        }
    }

    public void teachCommand() {
        Scanner scanner = new Scanner(System.in, "UTF-8");

        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        System.out.print("Введите класс животного (например, Dog, Cat, и т.д.): ");
        String className = scanner.nextLine();

        List<Animal> animals = animalsByClass.getOrDefault(className, new ArrayList<>());
        Animal selectedAnimal = null;
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                selectedAnimal = animal;
                break;
            }
        }

        if (selectedAnimal == null) {
            System.out.println("_____________________________________");
            System.out.println("Животное с указанным именем и классом не найдено.");
            return;
        }

        System.out.print("Введите новую команду для животного: ");
        String newCommand = scanner.nextLine();
        selectedAnimal.doCommand(newCommand);
    }

    public void viewAllAnimals() {
        if (animalsByClass.isEmpty()) {
            System.out.println("_____________________________________");
            System.out.println("Реестр домашних животных пуст.");
            return;
        }
        System.out.println("_____________________________________");
        System.out.println("Все добавленные животные:");

        for (Map.Entry<String, List<Animal>> entry : animalsByClass.entrySet()) {
            String className = entry.getKey();
            List<Animal> animals = entry.getValue();

            for (Animal animal : animals) {
                System.out.println("_____________________________________");
                System.out.println("Животное: " + animal.getName() + ", Дата рождения: " + animal.getBirth_date());
                System.out.println("Команды: " + animal.getCommand());
            }
        }
    }

    public void deliteAnimal() {
        Scanner scanner = new Scanner(System.in, "UTF-8");

        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        System.out.print("Введите класс животного: ");
        String className = scanner.nextLine();

        
        if (!animalsByClass.containsKey(className)) {
            System.out.println("_____________________________________");
            System.out.println("Класс " + className + " не найден в реестре.");
            return;
        }

        List<Animal> animals = animalsByClass.get(className);
       
        boolean found = false;
        for (Animal animal : animals) {
            if (animal.getName().equals(name)) {
                animals.remove(animal); 
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("_____________________________________");
            System.out.println("Животное " + name + " успешно удалено из реестра.");
        } else {
            System.out.println("_____________________________________");
            System.out.println("Животное " + name + " не найдено в классе " + className + ".");
        }
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        int choice = 0;

        do {
            System.out.println("_____________________________________");
            System.out.println("Меню:");
            System.out.println("1. Добавить животное");
            System.out.println("2. Определить класс животного");
            System.out.println("3. Посмотреть список команд животного");
            System.out.println("4. Обучить животное новой команде");
            System.out.println("5. Просмотреть всех добавленных животных");
            System.out.println("6. Удалить животное");
            System.out.println("7. Выход");

            System.out.print("Выберите пункт меню (1-6): ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    classifyAnimal();
                    break;
                case 3:
                    listCommands();
                    break;
                case 4:
                    teachCommand();
                    break;
                case 5:
                    viewAllAnimals();
                    break;
                case 6:
                    deliteAnimal();
                    break;
                case 7:
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    scanner.nextLine();
            }
        } while (choice != 7);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        Programm program = new Programm();
        program.showMenu();
    }

}
