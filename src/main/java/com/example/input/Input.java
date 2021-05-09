package com.example.input;

import lombok.AllArgsConstructor;
import com.example.i18n.Messenger;
import com.example.organization.Address;
import com.example.organization.Coordinates;
import com.example.organization.Organization;
import com.example.organization.OrganizationType;

import java.util.Scanner;

/**
 * Класс, который отвечает за источник ввода элементов
 * Хранит логику ввода в зависимости от источника
 */
@AllArgsConstructor
public abstract class Input {

    private final Scanner in;
    private final Messenger messenger;

    /**
     * Логика ввода элемента. Метод нужно переопределить в суб-классах
     * @return - Введенный элемент
     * @throws Exception - исключение, возникающее в случае некорректного ввода
     */
    public abstract Organization enterElement() throws Exception;

    // Все последующие методы предоставляют логику ввода отдельных полей
    // Пользователь вводит строку, а метод преобразует ее в нужный тип
    // Методы также хранят логику ограничений значений
    // (Метод не допустит ввод значения -10, если поле должно быть больше нуля)
    public String readName() throws Exception {
        try {
            String name = in.nextLine();
            if(name.equals("")) throw new IllegalArgumentException();
            return name;
        } catch (Exception e) {
            throw new Exception("Некорректный ввод.");
        }
    }

    public Coordinates readCoordinates() throws Exception {
        try {
            String[] arr = (in.nextLine()).split(" ");
            int x = Integer.parseInt(arr[0]);
            if(x > 310) throw new IllegalArgumentException("Значение поля должно быть не больше 310");
            Double y = Double.parseDouble(arr[1]);
            return new Coordinates(x,y);
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception("Некорректный ввод.");
        }
    }

    public float readAnnualTurnover() throws Exception {
        try {
            float result = Float.parseFloat(in.nextLine());
            if(result <= 0) throw new IllegalArgumentException("Значение поля должно быть больше нуля!");
            return result;
        } catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            throw new Exception("Некорректный ввод.");
        }
    }

    public OrganizationType readOrganizationType() throws Exception {
        try {
            String response = in.nextLine();
            OrganizationType organizationType;
            if(!response.equals("")) {
                organizationType = OrganizationType.valueOf(response);
            } else {
                throw new Exception("Поле не может быть null");
            }
            return organizationType;
        } catch (Exception e) {
            throw new Exception("Некорректный ввод.");
        }
    }

    public abstract AddressReader getAddressReader();

    /**
     * Класс, предоставляющий логику ввода объектов Address
     */
    public abstract class AddressReader {

        /**
         * Метод в котором будет вводиться элемент Address
         * @return - нужный объект
         * @throws Exception - в случае ошибок ввода
         */
        public abstract Address enterElement() throws Exception;

        public String readStreet() throws Exception {
            try {
                String name = in.nextLine();
                if(name.equals("")) throw new IllegalArgumentException("Строка не может быть пустой.");
                return name;
            } catch (IllegalArgumentException e) {
                throw new Exception(e.getMessage());
            } catch (Exception e) {
                throw new Exception("Некорректный ввод");
            }
        }

        public String readZipCode() throws Exception {
            try {
                String zipCode = in.nextLine();
                if(zipCode == null || zipCode.length() < 4)
                    throw new IllegalArgumentException("Зип код должен содержать минимум 4 цифры");

                return zipCode;
            } catch (IllegalArgumentException e) {
                throw new Exception(e.getMessage());
            } catch (Exception e) {
                throw new Exception("Некорректный ввод");
            }
        }
    }
}
