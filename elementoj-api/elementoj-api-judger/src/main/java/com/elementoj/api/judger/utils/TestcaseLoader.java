package com.elementoj.api.judger.utils;

import com.elementoj.api.judger.domain.EleTestcase;
import com.elementoj.common.core.web.utils.OS;

import java.util.ArrayList;
import java.util.List;


public class TestcaseLoader {

    public static String ELE_TESTCASE_DIR = "D:\\ElementOJ\\ElementOJ-NEW\\ElementOJ\\testcase";

    public static List<EleTestcase> loadTestcases(Long pid) {
        List<EleTestcase> testcases = new ArrayList<>();
        for (int i = 1; ; i++) {
            String path = OS.path_join(ELE_TESTCASE_DIR, String.valueOf(pid), i + ".in");
            if (!OS.exists(path)) break;
            else {
            }

        }

    }

//    public static List<EleTestcase> loadSampleTestcases(Long pid) {
//
//    }
}
