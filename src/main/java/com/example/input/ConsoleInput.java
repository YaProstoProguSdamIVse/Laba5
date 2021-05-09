package com.example.input;

import com.example.i18n.Messenger;
import com.example.organization.Address;
import com.example.organization.Coordinates;
import com.example.organization.Organization;
import com.example.organization.OrganizationType;

import java.util.Scanner;

/**
 * Класс, предоставляющий логику чтения элементов с консоли
 */
public class ConsoleInput extends Input {

    public ConsoleInput(Messenger messenger) {
        super(new Scanner(System.in), messenger);
    }

    /**
     * Вводя элемент с консоли, мы должны вводить каждое поле в цикле до тех пор, пока ввод не будет корректным.
     * В случае некорректного ввода - бросается исключение и программа снова просит нас ввести значение
     * @return - введенный объект
     */
    @Override
    public Organization enterElement() throws Exception {

        String name;
        Coordinates coordinates;
        float annualTurnover;
        OrganizationType type;

        while (true) {
            System.out.println("Введите имя:");
            try {
                name = readName();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите координаты (Integer, Double через пробел):");
            try {
                coordinates = readCoordinates();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите ежегодный оборот:");
            try {
                annualTurnover = readAnnualTurnover();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.println("Введите тип организации (COMMERCIAL, PUBLIC, GOVERNMENT, TRUST):");
            try {
                type = readOrganizationType();
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        AddressReader reader = new ConsoleAddressReader();
        Address address = reader.enterElement();

        return new Organization(name, coordinates, annualTurnover, type, address);
    }

    public AddressReader getAddressReader() {
        return new ConsoleAddressReader();
    }


    /**
     * Класс, предоставляющий логику ввода Address из консоли
     */
    public class ConsoleAddressReader extends AddressReader {

        @Override
        public Address enterElement() {

            Address address = new Address();

            while (true) {
                System.out.println("Введите название улицы:");
                try {
                    address.setStreet(readStreet());
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
                System.out.println("Введите зип-код:");
                try {
                    address.setZipCode(readZipCode());
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            return address;
        }
    }
}

