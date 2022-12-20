package com.test.task.factories;

import com.test.task.check.Check;
import com.test.task.check.ConsoleCheck;
import com.test.task.check.FileCheck;

public class ChecksFactory implements CheckFactory {

    @Override
    public Check createConsoleChecks() {
        return new ConsoleCheck(new ItemsFactory(), new CardsFactory());
    }

    @Override
    public Check createFileChecks() {
        return new FileCheck(new ItemsFactory(), new CardsFactory());
    }
}

