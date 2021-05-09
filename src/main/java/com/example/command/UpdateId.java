package com.example.command;

import lombok.Getter;
import lombok.Setter;
import com.example.i18n.Messenger;
import com.example.input.ConsoleInput;
import com.example.input.Input;
import com.example.organization.Organization;

import javax.validation.ConstraintViolation;
import java.util.ArrayDeque;
import java.util.Set;

@Getter @Setter
public class UpdateId extends Command {

    private Input userInput;

    public UpdateId(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);

        userInput = new ConsoleInput(messenger);
    }

    @Override
    public String execute(String id) {

        for (Organization o: getOrganizations()) {
            if(o.getId() == Long.parseLong(id)) {
                try {
                    Organization organization = userInput.enterElement();
                    Set<ConstraintViolation<Organization>> violations = getValidator().validate(organization);

                    if(violations.isEmpty()) {
                        o = organization;
                        return getFormatter().formatBooleanOperation(true, getMessenger());
                    }
                    violations.forEach(v -> System.err.println(v.getMessage()));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return getFormatter().formatBooleanOperation(false, getMessenger());
    }

    @Override
    public String toString() {
        return super.toString() + ": " + getMessenger().getMessage("infoUpdateId");
    }
}
