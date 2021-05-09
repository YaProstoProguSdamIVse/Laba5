package com.example.command;

import com.example.i18n.Messenger;
import com.example.organization.Organization;

import java.util.ArrayDeque;

public class Head extends Command {

    public Head(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);
    }

    @Override
    public String execute(String args) throws Exception {
        if(getOrganizations().size() != 0) {
            return getOrganizations().getFirst().toString();
        } else {
            return getFormatter().formatBooleanOperation(false, getMessenger());
        }
    }

    @Override
    public String toString() {
        return super.toString() + ":" + getMessenger().getMessage("infoHead");
    }
}
