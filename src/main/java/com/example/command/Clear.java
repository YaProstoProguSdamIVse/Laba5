package com.example.command;

import com.example.i18n.Messenger;
import com.example.organization.Organization;

import java.util.ArrayDeque;

public class Clear extends Command {

    public Clear(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);
    }

    @Override
    public String execute(String ignore) {
        getOrganizations().clear();
        return getFormatter().formatBooleanOperation(true, getMessenger());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoClear");
    }
}
