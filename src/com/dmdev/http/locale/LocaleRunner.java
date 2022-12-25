package com.dmdev.http.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleRunner {
    public static void main(String[] args) {


        Locale locale = new Locale("ru", "RU");

        var bundle = ResourceBundle.getBundle("translations");
        System.out.println(bundle.getString("page.login.password"));
        System.out.println();

    }
}
