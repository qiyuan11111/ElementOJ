package com.elementoj.module.judgeserver.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.elementoj.api.solution.domain.EleCompileConfig;
import com.elementoj.common.core.web.utils.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Component
public class Compiler {

    public static String compile(Long SolutionId, EleCompileConfig compileConfig, String srcPath, String outputDir) {

        String command = compileConfig.getCommand();
        final String exePath = OS.path_join(outputDir, compileConfig.getExeName());

        command = StrUtil.format(command, new HashMap<String, String>() {
            {
                this.put("src_path", srcPath);
                this.put("exe_dir", outputDir);
                this.put("exe_path", exePath);
            }
        });

        String compilerOut = OS.path_join(outputDir, "compiler.out");
        List<String> _command = Arrays.asList(command.split(" "));
        List<String> env = new ArrayList<>();

        if (!StrUtil.isEmptyIfStr(System.getenv("PATH"))) {
            env.add("PATH=" + System.getenv("PATH"));
        }

        List<String> finalEnv = env;
        JSONObject result = CompileUtils.run(new HashMap<String, Object>() {
            {
                this.put("max_cpu_time", compileConfig.getMaxCpuTime());
                this.put("max_real_time", compileConfig.getMaxRealTime());
                this.put("max_memory", compileConfig.getMaxMemory());
                this.put("max_stack", 134217728L);
                this.put("max_output_size", 20971520L);
                this.put("max_process_number", CompileUtils.UNLIMITED);
                this.put("exe_path", _command.get(0));
                this.put("input_path", srcPath);
                this.put("output_path", compilerOut);
                this.put("error_path", compilerOut);
                this.put("args", _command.subList(1, _command.size()));
                this.put("env", finalEnv);
                this.put("log_path", JudgeConstant.COMPILER_LOG_PATH);
                this.put("seccomp_rule_name", null);
                this.put("uid", JudgeConstant.COMPILER_USER_UID);
                this.put("gid", JudgeConstant.COMPILER_GROUP_GID);
            }
        });
        System.out.println(result.toJSONString());
        if (!result.getInteger("result").equals(CompileUtils.RESULT_SUCCESS)) {
            solutionService.updataProblemResult(SolutionId, OJ_PROERROR_COMPILATION_ERROR);
            if (OS.exists(compilerOut)) {
                try {
                    FileInputStream in = new FileInputStream(new File(compilerOut));
                    byte[] b = new byte[in.available()];
                    in.read(b);
                    String error = (new String(b)).trim();
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "";
        } else {
            OS.remove(compilerOut);
            return exePath;
        }
    }
}
