package com.elementoj.module.judgeserver.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.elementoj.api.solution.domain.EleCompileConfig;
import com.elementoj.api.solution.domain.EleSolution;
import com.elementoj.api.solution.domain.EleSubmission;
import com.elementoj.common.core.web.utils.OS;
import com.elementoj.module.judgeserver.utils.Compiler;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import static com.elementoj.module.judgeserver.utils.JudgeConstant.*;

@Service
public class EleJudgerService {

    public Triple<String, String, String> initTestcaseDir(String submissionId, Long pid) {

        String workDir = OS.path_join(JUDGER_WORKSPACE_BASE, submissionId);
        String outputDir = OS.path_join(TEST_CASE_DIR, "user_out", submissionId);
        String testCaseDir = OS.path_join(TEST_CASE_DIR, "in", String.valueOf(pid));

        try {
            FileUtil.mkdirsSafely(new File(workDir), 20, 20);
            FileUtil.mkdirsSafely(new File(outputDir), 20, 20);
            if (SystemUtils.IS_OS_LINUX){
                OS.chown(workDir, COMPILER_USER_UID, RUN_GROUP_GID);
                OS.chmod(workDir, 711);
                OS.chown(outputDir, COMPILER_USER_UID, RUN_GROUP_GID);
                OS.chmod(outputDir, 711);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new MutableTriple<>(outputDir, workDir, testCaseDir);
    }

    public String compile(EleSolution solution, EleCompileConfig compileConfig, Triple<String, String, String> dirs) {
        String userOutputDir = dirs.getLeft(), workDir = dirs.getMiddle(), testCaseDir = dirs.getRight();
        String srcPath = OS.path_join(workDir, compileConfig.getSrcName());

        FileUtil.writeUtf8String(solution.getCode(), srcPath);

        if (SystemUtils.IS_OS_LINUX){
            OS.chown(srcPath, COMPILER_USER_UID, "0");
            OS.chmod(srcPath, 400);
        }


        String exePath = Compiler.compile(solution.getSolutionId(), compileConfig, srcPath, userOutputDir);
        return "";
    }



}
