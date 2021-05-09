package com.example;

import com.example.command.manager.CommandManager;
import com.example.i18n.Messenger;
import com.example.i18n.MessengerImpl;
import com.example.print.implementation.FormatterImpl;
import com.example.print.implementation.PrinterImpl;
import com.example.service.UserTerminal;
import com.example.service.CSVConverter;

import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {

        Messenger messenger = new MessengerImpl(ResourceBundle.getBundle("text", Messenger.initLang()));

        UserTerminal terminal = new UserTerminal(
                new CommandManager(
                        new FormatterImpl(),
                        messenger,
                        new PrinterImpl(),
                        CSVConverter.read(System.getenv("CSV_ORGANIZATIONS_LIST_FILE_NAME"))
                ),
                new FormatterImpl(),
                new PrinterImpl(),
                messenger
        );

        // Запускаем терминал
        terminal.start();
    }
}
