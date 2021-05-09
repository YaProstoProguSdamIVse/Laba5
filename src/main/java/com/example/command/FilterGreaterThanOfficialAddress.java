package com.example.command;

import com.example.i18n.Messenger;
import com.example.input.ConsoleInput;
import com.example.input.Input;
import com.example.organization.Address;
import lombok.Getter;
import lombok.Setter;
import com.example.organization.Organization;

import java.util.ArrayDeque;

@Getter @Setter
public class FilterGreaterThanOfficialAddress extends Command {

    private final Input input;

    public FilterGreaterThanOfficialAddress(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);

        this.input = new ConsoleInput(getMessenger());
    }

    @Override
    public String execute(String args) throws Exception {

        Address address = input.getAddressReader().enterElement();

        ArrayDeque<Organization> filter = new ArrayDeque<>();
        for (Organization v: getOrganizations()) {
            if(v.getOfficialAddress().compareTo(address) > 0) filter.addLast(v);
        }

        return getFormatter().formatCollection(filter);
    }

    @Override
    public String toString() {
        return super.toString() + ":" + getMessenger().getMessage("infoFilterGreaterThanOfficialAddress");
    }
}
