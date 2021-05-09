package com.example.command;

import com.example.i18n.Messenger;
import com.example.organization.Organization;

import java.util.ArrayDeque;

public class RemoveHead extends Command {

    public RemoveHead(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);
    }

    @Override
    public String execute(String args) throws Exception {
        if(getOrganizations().size() != 0) {
            getOrganizations().removeFirst();
            return getFormatter().formatBooleanOperation(true, getMessenger());
        } else {
            return getFormatter().formatBooleanOperation(false, getMessenger());
        }
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoRemoveHead");
    }
}
