package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Double calc(String equation) {
        String [] arrString = equation.split(" ");
        return switch (arrString[1]) {
            case "+" -> Double.parseDouble(arrString[0]) + Double.parseDouble(arrString[2]);
            case "-" -> Double.parseDouble(arrString[0]) - Double.parseDouble(arrString[2]);
            case "/" -> Double.parseDouble(arrString[0]) / Double.parseDouble(arrString[2]);
            case "*" -> Double.parseDouble(arrString[0]) * Double.parseDouble(arrString[2]);
            default -> 0.0;
        }; // тут с 17й джавы можно такие финты делать
    }

    public static String capitalizeEachWord(String str) {
        StringBuilder newString = new StringBuilder();
        for (String word: str.split(" ")){
            newString.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase()).append(" "); // тут вот так норм можно
        }
        return newString.toString().trim();
    }

    public static String getMiddleCharacter(String str) {
        int strLength = str.length();
        if (strLength % 2 == 1){
            return str.substring(strLength / 2, strLength / 2 + 1);
        }
        else return str.substring(strLength / 2 - 1, strLength / 2 + 1);
    }

    public static String removeDuplicateWords(String str) {
        ArrayList<String> list = new ArrayList<>(); // можно в ArrayList'е в <> не писать String, джава сама может с 8й (вродь) версии выводить
        for (String word: str.split(" ")){
            if (!list.contains(word)){
                list.add(word);
            }
        }
        return String.join(" ", list);
    }

    public static Boolean hasSameOccurences(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++){
            if (map.containsKey(str.charAt(i))){
                map.replace(str.charAt(i),map.get(str.charAt(i))+1);
            }
            else map.put(str.charAt(i), 1);
        }
        for (Integer i : map.values()) { // можно интеджером прост сделать и не кастить ничего
            if (i != map.values().toArray()[0]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //Задание 1
        String equation = "123123 + 123123";
        System.out.println(calc(equation)); // результат: 246246
        //Задание 2
        String example = "Каждый охотник желает знать где сидит фазан";
        System.out.println(capitalizeEachWord(example)); // результат: "Каждый Охотник Желает Знать Где Сидит Фазан"
        //Задание 3
        String oddString = "слово";
        System.out.println(getMiddleCharacter(oddString)); // результат: "О"
        String evenString = "пять";
        System.out.println(getMiddleCharacter(evenString)); // результат: "ят"
        //Задание 4
        String example1 = "один один три два два два";
        System.out.println(removeDuplicateWords(example1)); // результат: "один три два"
        //Задание 5
        String sameOccurencesCount = "abcabc";
        System.out.println(hasSameOccurences(sameOccurencesCount)); // результат: true
        String diffOccurencesCount = "abca";
        System.out.println(hasSameOccurences(diffOccurencesCount)); // результат: false
    }
}
