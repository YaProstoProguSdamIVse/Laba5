package com.example.service;

import com.example.command.manager.CommandManager;
import com.example.i18n.Messenger;
import com.example.print.api.Formatter;
import com.example.print.api.Printer;
import lombok.Getter;
import lombok.Setter;
import com.example.command.Exit;

import java.util.Scanner;

/**
 * Класс, предоставляющий пользовательский терминал для работы с коллекцией (через Service)
 * Все пользовательские команды поступают сюда, а затем вызывается соответствующи метод Service
 */
@Getter @Setter
public class UserTerminal {

    private CommandManager manager;
    private Formatter formatter;
    private Printer printer;
    private Messenger messenger;

    public UserTerminal(CommandManager manager, Formatter formatter, Printer printer, Messenger messenger) {
        this.manager = manager;
        this.formatter = formatter;
        this.printer = printer;
        this.messenger = messenger;
    }

    /**
     * Метод, запускающий терминал
     * Непрерывно слушает пользовательский ввод, вызывает соответствующую команду и выводит результат
     */
    public void start() {

        System.out.println("Вас приветствует терминал управления списком пользователей.\n" +
                "Введите одну из команд для продолжения (help - список доступных команд)");

        Scanner sc = new Scanner(System.in);
        String response;
        while (!(response = sc.nextLine()).equals("exit")) {
            try {
                // Вызываем команду и выводим результат
                // В случае, если метод execute_command бросил исключение,
                // оно обрабатывается, а программа продолжает работу.
                if(response.equals("")) {
                    System.out.println(getManager().getMessenger().getMessage("enterValidData"));
                } else {
                    manager.executeUserCommand(response);
                }

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        // После того, как пользователь ввёл exit,
        // необходимо вызвать соответствующий метод Service и закончить выполнение программы
        new Exit(null, null).execute(null);
    }

}
