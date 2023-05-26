package ru.job4j.cache.menu;

import java.util.Scanner;
import ru.job4j.cache.DirFileCache;

public class Emulator {
    private DirFileCache cache;

    @SuppressWarnings("checkstyle:InnerAssignment")
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("1. Указать кэшируемую директорию");
            System.out.println("2. Загрузить содержимое файла в кэш");
            System.out.println("3. Получить содержимое файла из кэша");
            System.out.println("4. Выход");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> setCacheDirectory(scanner);
                case 2 -> loadFileToCache(scanner);
                case 3 -> getFileFromCache(scanner);
                case 4 -> running = false;
                default -> System.out.println("Некорректный выбор. Попробуйте снова.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private void setCacheDirectory(Scanner scanner) {
        System.out.print("Введите путь к кэшируемой директории: ");
        String cachingDir = scanner.nextLine();
        cache = new DirFileCache(cachingDir);
        System.out.println("Кэшируемая директория установлена.");
    }

    private void loadFileToCache(Scanner scanner) {
        if (cache == null) {
            System.out.println("Кэшируемая директория не установлена.");
            return;
        }

        System.out.print("Введите имя файла для загрузки в кэш: ");
        String fileName = scanner.nextLine();
        cache.get(fileName);
        System.out.println("Файл загружен в кэш.");
    }

    private void getFileFromCache(Scanner scanner) {
        if (cache == null) {
            System.out.println("Кэшируемая директория не установлена.");
            return;
        }

        System.out.print("Введите имя файла для получения из кэша: ");
        String fileName = scanner.nextLine();
        String fileContents = cache.get(fileName);

        if (fileContents != null) {
            System.out.println("Содержимое файла: " + fileContents);
        } else {
            System.out.println("Файл не найден в кэше.");
        }
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        emulator.start();
    }
}