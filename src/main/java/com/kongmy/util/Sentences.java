/*
 */
package com.kongmy.util;

import java.util.List;

/**
 *
 * @author Kong My
 */
public class Sentences {
    
    public static String JoinArray(List<String> arr) {
        if (arr.isEmpty()) {
            return "";
        } else if (arr.size() == 1) {
            return arr.get(0);
        } else {
            return String.join(", ", arr.subList(0, arr.size() - 1))
                    + " and "
                    + arr.get(arr.size() - 1);
        }
    }

    public static String FormatAsSentence(String sentence) {
        if(sentence == null || sentence.isEmpty())
            return "";
        else {
            if(!sentence.endsWith("."))
                sentence = sentence + ".";
            
            char ch = sentence.charAt(0);
            if(Character.isLetter(ch) &&
                    !Character.isUpperCase(ch))
                sentence = Character.toUpperCase(ch) + sentence.substring(1);
        }
        return sentence;
    }
    
}
