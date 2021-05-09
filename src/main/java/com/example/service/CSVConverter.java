package com.example.service;

import com.example.i18n.Messenger;
import com.example.organization.Organization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Класс, который считывает и записывает данные в CSV файл
 */
public class CSVConverter {

    public static Messenger messenger;

    /**
     * Метод считывает данные из CSV файла
     * @return - Коллекция, преобразованная из CSV записей файла
     */
    public static ArrayDeque<Organization> read(String fileName) {

        File file = new File(fileName);

        try(Scanner scanner = new Scanner(file)) {
            if(file.length() == 0) {
                System.out.println("Файл пустой. Вы должны заполнить его, прежде чем использовать.");
                return new ArrayDeque<>();
            } else {
                ArrayDeque<Organization> vehicles = new ArrayDeque<>();
                while (scanner.hasNextLine()) {
                    // Здесь мы непосредственно считываем строку из CSV файла и,
                    // с помощью метода Organization.setStringFields(List<String>) преобразуем CSV запись в POJO
                    Organization vehicle = Organization.setStringFields(getRecordFromLine(scanner.nextLine()));
                    vehicles.add(vehicle);
                }

                settleIds(vehicles);

                return vehicles;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден");
        }
        throw new RuntimeException("Ошибка чтения");
    }

    /**
     * Метод преобразует CSV запись в коллекцию строк
     * @param line - CSV запись, которую необходимо проанализировать
     * @return - коллекция строк, каждая из которых хранит строковое значения полей класса Organization
     */
    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    /**
     * Метод записывает коллекцию в CSV файл
     * @param dataSet - Коллекция объектов, которую необходимо преобразовать в массив CSV записей
     * @return - Строка, сигнализирующая об успехе операции
     */
    public static String write(ArrayDeque<Organization> dataSet, String fileName) {
        try(PrintWriter pw = new PrintWriter(fileName)) {
            if(dataSet.size() == 0) {
                pw.write("");
            } else {
                StringBuilder result = new StringBuilder();
                for (Organization v: dataSet) {
                    result.append(convertToCSV(v.getStringFields())).append("\n");
                }
                pw.write(result.toString());
            }
            return "Коллекция была сохранена";
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Ошибка сохранения");
    }

    /**
     * Метод конвертирует массив строк в CSV запись
     * @param data - массив строк, которые необходимы для создания CSV записи
     * @return - CSV запись в строковом виде
     */
    private static String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(CSVConverter::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    /**
     * Метод, который учитывает специальные символы в строке
     * (Например, в одном  из полей могут быть запятые, их необходимо оставить)
     * @param data - рассматриваемая строка
     * @return - преобразованная строка
     */
    private static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    private static void settleIds(ArrayDeque<Organization> organizations) {
        int id = 0;
        for (Organization vehicles : organizations) {
            vehicles.setId(++id);
        }
    }
}
