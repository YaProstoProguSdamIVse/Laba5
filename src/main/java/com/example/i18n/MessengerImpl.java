package com.example.i18n;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

@AllArgsConstructor
@Getter @Setter
public class MessengerImpl implements Messenger {

    private ResourceBundle rb;

    @Override
    public String getMessage(String msg) {
        return new String(rb.getString(msg).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
