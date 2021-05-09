package com.example.command;

import com.example.i18n.Messenger;
import com.example.input.ConsoleInput;
import com.example.input.Input;
import com.example.organization.Organization;

import java.util.ArrayDeque;

public class RemoveGreater extends Command {

    private Input input;

    public RemoveGreater(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);

        this.input = new ConsoleInput(getMessenger());
    }

    @Override
    public String execute(String args) throws Exception {

        Organization organization = input.enterElement();

        return getFormatter().formatBooleanOperation(
                getOrganizations().removeIf(o -> o.compareTo(organization) > 0),
                getMessenger()
        );
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoRemoveGreater");
    }
}
