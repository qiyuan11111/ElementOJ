package com.elementoj.module.judgeserver.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ctguacm.judgeserver.utils.OJInformation.*;
import static com.ctguacm.judgeserver.utils.OJInformation.SO_DIR;

public class CompileUtils {
    static Integer UNLIMITED = -1;
    public static Integer VERSION = 131329;
    static Integer RESULT_SUCCESS = 0;
    static Integer RESULT_WRONG_ANSWER = -1;
    public static Integer RESULT_CPU_TIME_LIMIT_EXCEEDED = 1;
    public static Integer RESULT_REAL_TIME_LIMIT_EXCEEDED = 2;
    public static Integer RESULT_MEMORY_LIMIT_EXCEEDED = 3;
    public static Integer RESULT_RUNTIME_ERROR = 4;
    public static Integer RESULT_SYSTEM_ERROR = 5;
    public static Integer ERROR_INVALID_CONFIG = -1;
    public static Integer ERROR_FORK_FAILED = -2;
    public static Integer ERROR_PTHREAD_FAILED = -3;
    public static Integer ERROR_WAIT_FAILED = -4;
    public static Integer ERROR_ROOT_REQUIRED = -5;
    public static Integer ERROR_LOAD_SECCOMP_FAILED = -6;
    public static Integer ERROR_SETRLIMIT_FAILED = -7;
    public static Integer ERROR_DUP2_FAILED = -8;
    public static Integer ERROR_SETUID_FAILED = -9;
    public static Integer ERROR_EXECVE_FAILED = -10;
    public static Integer ERROR_SPJ_ERROR = -11;
    private static String[] str_list_vars = new String[]{"args", "env"};
    private static String[] int_vars = new String[]{"max_cpu_time", "max_real_time", "max_memory", "max_stack", "max_output_size", "max_process_number", "uid", "gid", "memory_limit_check_only"};
    private static String[] str_vars = new String[]{"exe_path", "input_path", "output_path", "error_path", "log_path"};

    static JSONObject run(Map<String, Object> values) {
        List<String> proc_args = new ArrayList<>();
        for (String var : str_list_vars) {
            List<String> value = (List) values.get(var);
            if (value != null) {
                for (String item : value) {
                    proc_args.add(String.format("--%s=%s", var, item));
                }
            }
        }

        for (String var : int_vars) {
            Object o = values.get(var);
            if (o != null) {
                Long value = Long.valueOf(o.toString());
                proc_args.add(String.format("--%s=%d", var, value));
            }
        }

        for (String var :str_vars) {
            Object o = values.get(var);
            if (o != null && !"".equals(o.toString())) {
                String value = o.toString();
                proc_args.add(String.format("--%s=%s", var, value));
            }
        }

        Object o = values.get("seccomp_rule_name");
        if (o != null && !"".equals(o.toString())) {
            proc_args.add(String.format("--seccomp_rule_name=%s", o.toString()));
        }

        StringBuilder optBuilder = new StringBuilder();
        for (String proc_arg : proc_args) {
            optBuilder.append(proc_arg).append(" ");
        }
        String opt = optBuilder.toString();

        System.out.println(SO_DIR + opt);
        return JSONObject.parseObject(OS.exce(SO_DIR + opt));
    }
}
