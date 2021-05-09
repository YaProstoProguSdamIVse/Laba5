package com.example.print.implementation;
import com.example.i18n.Messenger;
import com.example.print.api.Formatter;

import java.util.Collection;

public class FormatterImpl implements Formatter {

    /**
     * Метод, который выводит коллекцию в более красивом виде
     */
    public String formatCollection(Collection<?> collection) {
        return collection
                .toString()
                .substring(1, collection.toString().length()-1)
                .replaceAll(", ", "\n");
    }

    public String formatBooleanOperation(boolean bool, Messenger messenger) {
        if(bool) {
            return messenger.getMessage("booleanOpTrue");
        } else {
            return messenger.getMessage("booleanOpFalse");
        }
    }
}
