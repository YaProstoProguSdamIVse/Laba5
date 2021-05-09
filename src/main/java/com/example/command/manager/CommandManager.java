package com.example.command.manager;

import com.example.command.ExecuteScript;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.example.command.Command;
import com.example.i18n.Messenger;
import com.example.print.api.Formatter;
import com.example.print.api.Printer;
import com.example.organization.Organization;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.Set;

@AllArgsConstructor
@Getter @Setter
public class CommandManager {

    private Formatter formatter;
    private Messenger messenger;
    private Printer printer;
    private ArrayDeque<Organization> mDataSet;

    public void executeUserCommand(String input) throws Exception {
        // Разделяем пользовательский ввод на команду и аргументы
        String[] array = input.split(" ");
        String commandName = array[0];

        // Аргументов должно быть либо 0, либо 1
        String args = array.length > 1 ? array[1] : null;

        String result = executeCommand(commandName, args);

        printResult(result);
    }

    public void printResult(String result) {
        printer.printResult(result);
    }

    private String executeCommand(String commandName, String args) throws Exception {

        Reflections reflections = new Reflections("com.example.command");
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);


        final Command[] command = new Command[1];
        classes.forEach(c -> {
            if(c.getSimpleName().equalsIgnoreCase(commandName)) {
                try {
                    if(commandName.equalsIgnoreCase("executescript")) {
                        Constructor<ExecuteScript> constructor = ExecuteScript.class.getConstructor(ArrayDeque.class, Messenger.class);
                        ExecuteScript executeScript = constructor.newInstance(mDataSet, messenger);
                        executeScript.setCommandManager(this);
                        command[0] = executeScript;
                    } else {
                        Constructor<? extends Command> constructor = c.getConstructor(ArrayDeque.class, Messenger.class);
                        command[0] = constructor.newInstance(mDataSet, messenger);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return command[0] != null ? command[0].execute(args) : messenger.getMessage("noSuchCommand");
    }

    public String executeCommand(String commandArgs) throws Exception {
        // Разделяем пользовательский ввод на команду и аргументы
        String[] array = commandArgs.split(" ");
        String commandName = array[0];

        // Аргументов должно быть либо 0, либо 1
        String args = array.length > 1 ? array[1] : null;

        return executeCommand(commandName, args);
    }
}
