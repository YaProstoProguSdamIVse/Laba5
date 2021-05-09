package com.example.organization;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;

@Getter @Setter
public class Organization implements Serializable, Comparable<Organization> {

    private static long cur_id = 0;

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float annualTurnover; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null
    private Address officialAddress; //Поле не может быть null

    public Organization(String name,
                        Coordinates coordinates,
                        float annualTurnover,
                        OrganizationType type,
                        Address officialAddress) {
        this.name = name;
        this.coordinates = coordinates;
        this.annualTurnover = annualTurnover;
        this.type = type;
        this.officialAddress = officialAddress;

        // Эти поля пользователь не задает, они генерируются автоматически
        this.id = ++cur_id;
        this.creationDate = ZonedDateTime.now();
    }

    /**
     * Метод возвращает массив полей класса в строковом виде. Это необходимо для преобразования объекта в CSV запись
     */
    public String[] getStringFields() {
        return new String[] {
                String.valueOf(id),
                name,
                coordinates.toString(),
                creationDate.toString(),
                String.valueOf(annualTurnover),
                String.valueOf(type),
                officialAddress.toString()};
    }

    public static Organization setStringFields(List<String> fields) {

        // Здесь каждый элемент из fields - это одно из полей Vehicle
        // Все поля идут по порядку. Первое из них - id

        // id
        long id = Long.parseLong(fields.get(0));

        // name
        String name = fields.get(1);

        // coordinates
        String[] arr = fields.get(2).split("; ");
        Integer x = Integer.parseInt(arr[0].substring(1));
        Double y = Double.parseDouble(arr[1].substring(0, arr[1].length() - 1));
        Coordinates coordinates = new Coordinates(x,y);

        // creation date
        ZonedDateTime creationDate = ZonedDateTime.parse(fields.get(3));

        // annual turnover
        float annualTurnover = Float.parseFloat(fields.get(4));

        // Org type
        OrganizationType type = null;
        if(!fields.get(6).equals("null")) {
            type = OrganizationType.valueOf(fields.get(5));
        }

        // Address
        String[] addressFields = fields.get(6).split(";");
        String street = addressFields[0].substring(1);
        String zipCode = addressFields[1].substring(0, addressFields[1].length() - 1);
        Address address = new Address(street, zipCode);

        // Генерируем конечный объект Vehicle, также назначаем ему id и creationDate (т.к. они не передаются в конструкторе)
        Organization organization = new Organization(name, coordinates, annualTurnover, type, address);
        organization.setId(id);
        organization.setCreationDate(creationDate);

        return organization;
    }

    @Override
    public int compareTo(Organization o) {
        // Объекты сортируются по имени
        return Comparator.comparing(Organization::getName)
                .compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return id == that.id
                && Float.compare(that.annualTurnover, annualTurnover) == 0
                && name.equals(that.name)
                && coordinates.equals(that.coordinates)
                && creationDate.equals(that.creationDate)
                && type == that.type
                && officialAddress.equals(that.officialAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, annualTurnover, type, officialAddress);
    }

    @Override
    public String toString() {
        return new Formatter()
                .format("Name: %s\n" +
                                "ID: %d\n" +
                                "Coordinates: %s\n" +
                                "Annual turnover: %f\n" +
                                "Organization type: %s\n" +
                                "Address: %s\n" +
                                "Creation date: %s\n" +
                                "Hashcode: %d\n",
                        name, id, coordinates, annualTurnover,
                        type, officialAddress, creationDate, hashCode())
                .toString();
    }
}