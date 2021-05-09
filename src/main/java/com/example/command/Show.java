package com.example.command;


import com.example.i18n.Messenger;
import com.example.organization.Organization;

import java.util.ArrayDeque;

public class Show extends Command {

    public Show(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);
    }

    @Override
    public String execute(String ignore) {
        return getFormatter().formatCollection(getOrganizations());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoShow");
    }
}
