package com.elementoj.module.judgeserver.utils;

import com.elementoj.common.core.web.utils.OS;

public class JudgeConstant {
    public static final String JUDGER_WORKSPACE_BASE = "D:\\ElementOJ\\ElementOJ-NEW\\ElementOJ\\judge\\run";
    public static final String LOG_BASE = "D:\\ElementOJ\\ElementOJ-NEW\\ElementOJ\\log";

    public static final String COMPILER_LOG_PATH = OS.path_join(LOG_BASE, "compile.log");
    public static final String JUDGER_RUN_LOG_PATH = OS.path_join(LOG_BASE, "judger.log");
    public static final String SERVER_LOG_PATH = OS.path_join(LOG_BASE, "judge_server.log");

    public static final String RUN_USER_UID = OS.getpwnam("code");
    public static final String RUN_GROUP_GID = OS.getgrnam("code");

    public static final String COMPILER_USER_UID = OS.getpwnam("compiler");
    public static final String COMPILER_GROUP_GID = OS.getgrnam("compiler");

    public static final String SPJ_USER_UID = OS.getpwnam("spj");
    public static final String SPJ_GROUP_GID = OS.getgrnam("spj");

    public static final String TEST_CASE_DIR = "D:\\ElementOJ\\ElementOJ-NEW\\ElementOJ\\testcase";
    public static final String SPJ_SRC_DIR = "/judger/spj";
    public static final String SPJ_EXE_DIR = "/judger/spj";
    public static final String SO_DIR = "/usr/lib/judger/libjudger.so ";
}
