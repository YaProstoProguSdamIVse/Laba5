package com.example.command;

import com.example.i18n.Messenger;
import com.example.organization.Organization;

import java.util.ArrayDeque;

public class CountGreaterThanAnnualTurnover extends Command {

    public CountGreaterThanAnnualTurnover(ArrayDeque<Organization> organizations, Messenger messenger) {
        super(organizations, messenger);
    }

    @Override
    public String execute(String args) throws Exception {

        int count = 0;
        float annualTurnover = Float.parseFloat(args);
        for (Organization v: getOrganizations()) {
            if(v.getAnnualTurnover() > annualTurnover) count++;
        }

        return String.valueOf(count);
    }
}
