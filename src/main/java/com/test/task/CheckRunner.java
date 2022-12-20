package com.test.task;

import com.test.task.application.Application;
import com.test.task.factories.CheckFactory;
import com.test.task.factories.ChecksFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CheckRunner {
    public static void main(String[] args) {

        CheckFactory checkFactory = new ChecksFactory();
        Application app = new Application(checkFactory);
//        String[] some = {"1-3 2-4, 5-6 2-5 5"};
        init(app, args);

    }

    public static void init(Application app, String[] args) {
        List<String> strings = new ArrayList<>(Arrays.asList(args));
        String delimeter = "-";
        List<String> newStrings = new ArrayList<>();
        for (int i = 0; i < strings.size(); i++) {
            List<String> newS = new ArrayList<>(Arrays.asList(strings.get(i).split(delimeter)));
            newStrings.addAll(newS);
        }
        List<Integer> integers = newStrings.stream().map(Integer::parseInt).collect(Collectors.toList());
        app.headOfCheck();
        while (!integers.isEmpty()) {
            if (integers.size() == 3) {
                app.printCheck(integers.get(0), integers.get(1), integers.get(2));
                break;
            } else {
                app.printCheck(integers.get(0), integers.get(1));
            }
            if (integers.size() > 3) {
                integers.remove(0);
                integers.remove(0);
            } else {
                break;
            }
        }
        app.checkBottom();
    }

}
