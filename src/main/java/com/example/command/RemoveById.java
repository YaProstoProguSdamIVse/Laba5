package com.example.command;

import com.example.i18n.Messenger;
import com.example.organization.Organization;

import java.util.ArrayDeque;

public class RemoveById extends Command {

    public RemoveById(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);
    }

    @Override
    public String execute(String id) {
        return getFormatter().formatBooleanOperation(
                getOrganizations().removeIf(d -> d.getId() == Long.parseLong(id)),
                getMessenger()
        );
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoRemoveById");
    }
}
