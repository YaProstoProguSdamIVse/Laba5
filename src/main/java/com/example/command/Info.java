package com.example.command;

import lombok.Getter;
import lombok.Setter;
import com.example.i18n.Messenger;
import com.example.organization.Organization;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayDeque;
import java.util.Formatter;

@Getter @Setter
public class Info extends Command {

    public Info(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);
    }

    @Override
    public String execute(String ignore) {
        String fileCreationDate = null;
        String elementType = null;

        try {
            BasicFileAttributes attr = Files.readAttributes(new File(System.getenv("CSV_ORGANIZATIONS_LIST_FILE_NAME")).toPath(), BasicFileAttributes.class);
            fileCreationDate = String.valueOf(attr.creationTime());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Field hashSet = this.getClass().getSuperclass().getDeclaredField("organizations");
            elementType = String.valueOf(hashSet.getGenericType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return new Formatter()
                .format(getMessenger().getMessage("type") + ": %s\n" +
                                getMessenger().getMessage("size") + ": %d\n" +
                                getMessenger().getMessage("creationDate") +": %s\n",
                        elementType, getOrganizations().toArray().length, fileCreationDate)
                .toString();
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoInfo");
    }
}
