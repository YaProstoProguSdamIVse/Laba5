package com.example.i18n;

import java.util.Locale;

public interface Messenger {
    String getMessage(String msg);

    static Locale initLang() {
//        System.out.println("Пожалуйста, выберите язык (ru_RU, uk_UA). Please, choose your language");

        Language language = Language.ru_RU;
//        try {
//            language = Language.valueOf(new Scanner(System.in).nextLine());
//        } catch (Exception e) {
//            System.out.println("Пожалуйста, введите корректные данные");
//            System.exit(-1);
//        }

        String[] array = language.toString().split("_");
        return new Locale(array[0], array[1]);
    }
}
