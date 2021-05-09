package com.example.command;

import com.example.i18n.Messenger;
import com.example.input.ConsoleInput;
import com.example.input.Input;
import lombok.Getter;
import lombok.Setter;
import com.example.organization.Organization;

import javax.validation.ConstraintViolation;
import java.util.ArrayDeque;
import java.util.Set;

@Getter @Setter
public class Add extends Command {

    private Input consoleUserInput;

    public Add(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);

        consoleUserInput = new ConsoleInput(messenger);

    }

    @Override
    public String execute(String ignore) throws Exception {

        Organization organization = consoleUserInput.enterElement();

        Set<ConstraintViolation<Organization>> violations = getValidator().validate(organization);

        if(violations.isEmpty()) {
            getOrganizations().add(organization);
            return getFormatter().formatBooleanOperation(true, getMessenger());
        }

        violations.forEach(v -> System.err.println(v.getMessage()));
        return getFormatter().formatBooleanOperation(false, getMessenger());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoAdd");
    }
}
