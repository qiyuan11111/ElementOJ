package com.elementoj.api.problem.exception;

import static com.elementoj.api.problem.exception.EleProblemExceptionCode.ELE_PROBLEM_NOT_FOUND;

public class EleProblemNotFound extends EleProblemException{
    public EleProblemNotFound(Long pid) {
        super("题目" + pid + "不存在！", ELE_PROBLEM_NOT_FOUND);
    }

    public EleProblemNotFound(Throwable cause) {
        super(cause, ELE_PROBLEM_NOT_FOUND);
    }

    public EleProblemNotFound(Throwable cause, Long pid) {
        super("题目" + pid + "不存在！", cause, ELE_PROBLEM_NOT_FOUND);
    }
}
