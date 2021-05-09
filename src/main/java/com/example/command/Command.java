package com.example.command;

import com.example.print.implementation.FormatterImpl;
import lombok.*;
import com.example.i18n.Messenger;
import com.example.print.api.Formatter;
import com.example.organization.Organization;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayDeque;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public abstract class Command {

    private ArrayDeque<Organization> organizations;

    private Formatter formatter;

    private Messenger messenger;

    private Validator validator;

    public Command(ArrayDeque<Organization> organizations, Messenger messenger) {
        this.organizations = organizations;
        this.messenger = messenger;
        this.formatter = new FormatterImpl();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public abstract String execute(String args) throws Exception;

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
