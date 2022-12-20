package com.test.task.factories;

import com.test.task.check.Check;

public interface CheckFactory {
    Check createConsoleChecks();

    Check createFileChecks();
}
