package by.mishastoma.di.utils;

import java.util.regex.Pattern;

public class StringUtils {

    public boolean isCorrectAnnotationValue(String val) {
        return Pattern.matches("^\\$\\{.*}$", val);
    }

    public String convertAnnotationValue(String val) {
        return val.substring(2, val.length() - 1);
    }
}
