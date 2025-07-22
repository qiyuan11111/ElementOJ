package com.elementoj.api.solution.domain;

import com.elementoj.common.core.web.constant.LangConstants;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class EleLanguageConfig {

    public EleRunConfig eleRunConfig;

    public EleCompileConfig eleCompileConfig;

    private static Map<Integer, EleLanguageConfig> lang_config = new HashMap<Integer, EleLanguageConfig>() {{

        put(LangConstants.ELE_LANG_C, new EleLanguageConfig()
                .setEleRunConfig(new EleRunConfig()
                        .setCommand("{exe_path}")
                        .setSeccompRule("c_cpp")
                        .setEnv(new ArrayList<String>() {{
                            add("LANG=en_US.UTF-8");
                            add("LANGUAGE=en_US:en");
                            add("LC_ALL=en_US.UTF-8");
                        }}))
                .setEleCompileConfig(new EleCompileConfig()
                        .setSrcName("main.c")
                        .setExeName("main")
                        .setMaxCpuTime(3000L)
                        .setMaxRealTime(6000L)
                        .setMaxMemory(268435456L)
                        .setCommand("/usr/bin/gcc -DONLINE_JUDGE -O2 -w -fmax-errors=3 -std=c99 {src_path} -lm -o {exe_path}")
                )
        );

        put(LangConstants.ELE_LANG_CPLUSPLUS, new EleLanguageConfig()
                .setEleRunConfig(new EleRunConfig()
                        .setCommand("{exe_path}")
                        .setSeccompRule("c_cpp")
                        .setEnv(new ArrayList<String>() {{
                            add("LANG=en_US.UTF-8");
                            add("LANGUAGE=en_US:en");
                            add("LC_ALL=en_US.UTF-8");
                        }}))
                .setEleCompileConfig(new EleCompileConfig()
                        .setSrcName("main.c")
                        .setExeName("main")
                        .setMaxCpuTime(3000L)
                        .setMaxRealTime(6000L)
                        .setMaxMemory(268435456L)
                        .setCommand("/usr/bin/g++ -DONLINE_JUDGE -O2 -w -fmax-errors=3 {src_path} -lm -o {exe_path}")
                )
        );

        put(LangConstants.ELE_LANG_CPLUSPLUS11, new EleLanguageConfig()
                .setEleRunConfig(new EleRunConfig()
                        .setCommand("{exe_path}")
                        .setSeccompRule("c_cpp")
                        .setEnv(new ArrayList<String>() {{
                            add("LANG=en_US.UTF-8");
                            add("LANGUAGE=en_US:en");
                            add("LC_ALL=en_US.UTF-8");
                        }}))
                .setEleCompileConfig(new EleCompileConfig()
                        .setSrcName("main.c")
                        .setExeName("main")
                        .setMaxCpuTime(3000L)
                        .setMaxRealTime(6000L)
                        .setMaxMemory(268435456L)
                        .setCommand("/usr/bin/g++ -DONLINE_JUDGE -O2 -w -fmax-errors=3 -std=c++11 {src_path} -lm -o {exe_path}")
                )
        );
    }};

    public static EleLanguageConfig getLangConfig(Integer lang) {
        return lang_config.get(lang);
    }

}
