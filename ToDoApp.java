import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class ToDoApp {

    private static final String FILE_NAME = "tasks.csv";

    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public static void main(String[] args) {
        ToDoApp app = new ToDoApp();
        app.loadFromFile();

        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        while (true) {
            app.printMenu();
            System.out.print("Выберите пункт меню: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    app.printTasks();
                    break;
                case "2":
                    app.addTask(scanner);
                    break;
                case "3":
                    app.markTaskDone(scanner);
                    break;
                case "4":
                    app.deleteTask(scanner);
                    break;
                case "5":
                    app.saveToFile();
                    System.out.println("Задачи сохранены. Выход из программы.");
                    return;
                default:
                    System.out.println("Неизвестный пункт меню. Попробуйте ещё раз.\n");
            }
        }
    }

    private void printMenu() {
        System.out.println("========== ToDo Менеджер ==========");
        System.out.println("1. Показать задачи");
        System.out.println("2. Добавить задачу");
        System.out.println("3. Отметить задачу выполненной");
        System.out.println("4. Удалить задачу");
        System.out.println("5. Сохранить и выйти");
        System.out.println("===================================");
    }

    // ======= Операции с задачами =======

    private void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст.\n");
            return;
        }

        System.out.println("Текущие задачи:");
        for (Task task : tasks) {
            System.out.println(task);
        }
        System.out.println();
    }

    private void addTask(Scanner scanner) {
        System.out.print("Введите текст задачи: ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            System.out.println("Пустая задача не может быть добавлена.\n");
            return;
        }

        Task task = new Task(nextId++, description, false);
        tasks.add(task);
        System.out.println("Задача добавлена: " + task + "\n");

        saveToFile();
    }

    private void markTaskDone(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст. Отмечать нечего.\n");
            return;
        }

        System.out.print("Введите ID задачи, которую нужно отметить выполненной: ");
        String line = scanner.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ID.\n");
            return;
        }

        Task task = findTaskById(id);
        if (task == null) {
            System.out.println("Задача с таким ID не найдена.\n");
            return;
        }

        if (task.isDone()) {
            System.out.println("Задача уже отмечена как выполненная: " + task + "\n");
        } else {
            task.setDone(true);
            System.out.println("Задача отмечена как выполненная: " + task + "\n");
            saveToFile();
        }
    }

    private void deleteTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст. Удалять нечего.\n");
            return;
        }

        System.out.print("Введите ID задачи, которую нужно удалить: ");
        String line = scanner.nextLine().trim();
        int id;
        try {
            id = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ID.\n");
            return;
        }

        Task task = findTaskById(id);
        if (task == null) {
            System.out.println("Задача с таким ID не найдена.\n");
            return;
        }

        tasks.remove(task);
        System.out.println("Задача удалена: " + task + "\n");
        saveToFile();
    }

    private Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    // ======= Работа с файлом =======

    private void loadFromFile() {
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) {
            return;
        }

        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            int maxId = 0;

            for (String line : lines) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // Формат: id;done;description
                String[] parts = line.split(";");
                if (parts.length < 3) {
                    System.out.println("Пропускаю некорректную строку в файле: " + line);
                    continue;
                }

                int id;
                boolean done;
                try {
                    id = Integer.parseInt(parts[0]);
                    done = Boolean.parseBoolean(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Пропускаю строку с неверным форматом ID/статуса: " + line);
                    continue;
                }

                StringBuilder descBuilder = new StringBuilder();
                for (int i = 2; i < parts.length; i++) {
                    if (i > 2) descBuilder.append(";");
                    descBuilder.append(parts[i]);
                }
                String description = descBuilder.toString();

                tasks.add(new Task(id, description, done));
                if (id > maxId) {
                    maxId = id;
                }
            }

            nextId = maxId + 1;
            if (!tasks.isEmpty()) {
                System.out.println("Загружено задач из файла: " + tasks.size());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла задач: " + e.getMessage());
        }
        System.out.println();
    }

    private void saveToFile() {
        Path path = Paths.get(FILE_NAME);
        List<String> lines = new ArrayList<>();

        for (Task task : tasks) {
            // Формат: id;done;description
            String line = task.getId() + ";" + task.isDone() + ";" + task.getDescription();
            lines.add(line);
        }

        try {
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении задач в файл: " + e.getMessage());
        }
    }

    // ======= Класс задачи =======

    private static class Task {
        private final int id;
        private String description;
        private boolean done;

        public Task(int id, String description, boolean done) {
            this.id = id;
            this.description = description;
            this.done = done;
        }

        public int getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public boolean isDone() {
            return done;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setDone(boolean done) {
            this.done = done;
        }

        @Override
        public String toString() {
            String status = done ? "[X]" : "[ ]";
            return String.format("%s ID=%d: %s", status, id, description);
        }
    }
}
