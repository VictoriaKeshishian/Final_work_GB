package Animal_registry;

import java.io.FileWriter;
import java.io.IOException;

public class Counter implements AutoCloseable {
    private int count;
    private boolean resourceClosed;
    private FileWriter fileWriter; // Внешний ресурс - файловый поток

    public Counter() throws IOException {
        count = 0;
        resourceClosed = false;
        fileWriter = new FileWriter("output.txt"); // Создаем файловый поток для записи в файл "output.txt"
    }

    public void add() {
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void close() throws IOException {
        if (!resourceClosed) {
            // Выполняем освобождение ресурса - закрываем файловый поток
            fileWriter.close();

            resourceClosed = true;
            System.out.println("Ресурс закрыт. Значение счетчика: " + count);
        } else {
            // Бросаем исключение, если метод close() вызывается повторно или ресурс уже закрыт
            throw new IllegalStateException("Ресурс уже закрыт.");
        }
    }
}
