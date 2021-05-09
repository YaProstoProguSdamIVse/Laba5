package com.example.input;

import com.example.i18n.Messenger;
import com.example.organization.Address;
import com.example.organization.Organization;

import java.util.Scanner;

/**
 * Класс, предоставляющий логику чтения элементов из файла
 */
public class FileInput extends Input {

    public FileInput(Scanner in, Messenger messenger) {
        super(in, messenger);
    }

    /**
     * Ввод элемента из файла.
     * Здесь логика немного отличается - нам не нужно пытаться вводить каждое поле до тех пор,
     * пока ввод не будет корректным, а просто начать читать каждое поле, до первого брошенного исключения.
     * После исключения прекратить ввод и вывести сообщение об ошибке.
     * @return - Введенный элемент (в случае корректных данных в файле)
     * @throws Exception - исключение, брошенное в случае некорректных данных в файле
     */
    @Override
    public Organization enterElement() throws Exception {

        AddressReader reader = new FileAddressReader();

        return new Organization(readName(),
                readCoordinates(),
                readAnnualTurnover(),
                readOrganizationType(),
                new Address(
                        reader.readStreet(),
                        reader.readZipCode()
                )
        );
    }

    public AddressReader getAddressReader() {
        return new FileAddressReader();
    }

    /**
     * Класс, предоставляющий логику ввода Address из файла
     */
    public class FileAddressReader extends AddressReader {
        @Override
        public Address enterElement() throws Exception {
            return new Address(readStreet(), readZipCode());
        }
    }
}

