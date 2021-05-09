package com.example.command;

import com.example.i18n.Messenger;
import com.example.organization.Organization;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.Set;

public class Help extends Command {

    public Help(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);
    }

    @Override
    public String execute(String ignore) {

        Reflections reflections = new Reflections("com.example.command");
        Set<Class<? extends Command>> classes = reflections.getSubTypesOf(Command.class);

        StringBuilder result = new StringBuilder("");

        classes.forEach(c -> {

            try {
                Constructor<? extends Command> constructor = c.getConstructor(ArrayDeque.class, Messenger.class);
                Command command = constructor.newInstance(getOrganizations(), getMessenger());
                result.append(command.toString()).append("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return result.toString();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoHelp");
    }
}
