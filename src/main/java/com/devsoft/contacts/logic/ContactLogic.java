package com.devsoft.contacts.logic;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sergey on 30.04.2016.
 */
public class ContactLogic {
    public static boolean isValidRegex(String str, String regex){
        boolean isMatcher = false;
        if(!StringUtils.isEmpty(str) && !StringUtils.isEmpty(regex)) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            isMatcher = matcher.matches();
        }
        return isMatcher;
    }
}
