package com.example.command;

import com.example.i18n.Messenger;
import com.example.organization.Organization;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintDescending extends Command {
    public PrintDescending(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);
    }

    @Override
    public String execute(String args) throws Exception {
        List<Organization> sorted = new ArrayList<>(getOrganizations());
        Collections.sort(sorted);
        Collections.reverse(sorted);

        return getFormatter().formatCollection(sorted);
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoPrintDescending");
    }
}
