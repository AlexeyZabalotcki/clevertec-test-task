package com.test.task.application;

import com.test.task.check.Check;
import com.test.task.factories.CheckFactory;

public class Application {
    private final Check consoleCheck;
    private final Check fileCheck;

    public Application(CheckFactory checkFactory) {
        this.consoleCheck = checkFactory.createConsoleChecks();
        this.fileCheck = checkFactory.createFileChecks();
    }

    public void printCheck(int index, int quantity, int cardNumber) {
        consoleCheck.printCheck(index, quantity, cardNumber);
        fileCheck.printCheck(index, quantity, cardNumber);
    }

    public void printCheck(int index, int quantity) {
        consoleCheck.printCheck(index, quantity, -1);
        fileCheck.printCheck(index, quantity, -1);
    }

    public void headOfCheck() {
        consoleCheck.headOfCheck();
        fileCheck.headOfCheck();
    }

    public void checkBottom() {
        consoleCheck.checkBottom();
        fileCheck.checkBottom();
    }
}
