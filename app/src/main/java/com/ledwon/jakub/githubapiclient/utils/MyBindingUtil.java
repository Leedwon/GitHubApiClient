package com.ledwon.jakub.githubapiclient.utils;

import androidx.databinding.BindingConversion;

public class MyBindingUtil {
    private static final String TRUE = "true";
    private static final String FALSE = "false";

    @BindingConversion
    public static String toString(boolean bool){
        return bool ? TRUE : FALSE;
    }
}
