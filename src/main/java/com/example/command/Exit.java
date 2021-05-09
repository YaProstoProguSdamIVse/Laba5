package com.example.command;


import com.example.i18n.Messenger;
import com.example.organization.Organization;

import java.util.ArrayDeque;

public class Exit extends Command {

    public Exit(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);
    }

    @Override
    public String execute(String ignore) {
        System.exit(0);
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoExit");
    }
}
