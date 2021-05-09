package com.example.command;

import com.example.i18n.Messenger;
import com.example.organization.Organization;
import com.example.service.CSVConverter;

import java.util.ArrayDeque;

public class Save extends Command {

    public Save(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);
    }

    @Override
    public String execute(String ignore) {

        CSVConverter.write(getOrganizations(), System.getenv("CSV_ORGANIZATIONS_LIST_FILE_NAME"));

        return getFormatter().formatBooleanOperation(true, getMessenger());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoSave");
    }
}
