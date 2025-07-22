package com.elementoj.module.test.controller;


import com.elementoj.common.core.web.domain.AjaxResult;
import com.elementoj.module.test.domain.EleTest;
import com.elementoj.module.test.service.EleTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/test")
public class EleTestController {

    @Resource
    EleTestService eleTestService;

    @GetMapping("/stream-video")
    public void streamVideo(@RequestParam("filePath") String videoFilePath,
                            @RequestParam("subtitlesPath") String subtitlesPath,
                            HttpServletResponse response) {

        // 设置响应内容类型为视频流
        response.setContentType("video/mp4");

        try {
            // 使用 FFmpeg 处理视频和字幕，输出到响应流
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "ffmpeg",
                    "-i", videoFilePath,
                    "-vf", "subtitles=" + subtitlesPath,
                    "-f", "mp4",  // 使用 mp4 格式进行流式传输
                    "-movflags", "frag_keyframe+empty_moov",  // 确保支持 HTTP 流式传输
                    "-c:v", "libx264",  // 编码为 H.264 视频格式
                    "-preset", "fast",  // 使用快速编码
                    "-f", "segment",  // 使用分段格式
                    "-segment_time", "10",  // 每 10 秒分段
                    "-reset_timestamps", "1",  // 重置时间戳
                    "pipe:1"  // 输出到标准输出流
            );

            Process process = processBuilder.start();

            // 从 FFmpeg 获取输出，并写入 HTTP 响应输出流
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                 OutputStream outputStream = response.getOutputStream()) {

                int bytesRead;
                byte[] buffer = new byte[4096];
                while ((bytesRead = process.getInputStream().read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    outputStream.flush();
                }

                // 等待 FFmpeg 进程结束
                process.waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/what")
    public AjaxResult test(HttpServletRequest request) {
        ExecutorService executor = Executors.newFixedThreadPool(1000);

        List<EleTest>[] lists = new List[2000];
        Random rand = new Random();

        for (int i = 1; i <= 1000; i++) {
            List<EleTest> list = new ArrayList<>();

            for (int j = 1; j <= 1000; j++) {
                int id = rand.nextInt(1001999) + 1;
                id = 1001 - (int) Math.sqrt(id * 1.0f);
                list.add(new EleTest((i - 1) * 1000 + j, id, id, id, "" + id));
            }

            lists[i] = list;
        }

        Instant start = Instant.now();
        for (int i = 1; i < 1000; i++) {
            final int threadIndex = i;
            executor.submit(() -> {
                eleTestService.test2(lists[threadIndex]);
            });
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait until all tasks are completed
        }

        Instant end = Instant.now();
        long duration = java.time.Duration.between(start, end).toMillis();

        return AjaxResult.success(duration);
    }

    @PostMapping("/add")
    public AjaxResult getRandom(HttpServletRequest request) {
        ExecutorService executor = Executors.newFixedThreadPool(1000);

        Instant start = Instant.now();
        for (int i = 0; i < 1000; i++) {
            final int threadIndex = i;
            executor.submit(() -> {
                for (int j = 0; j < 1; j++) {
                    eleTestService.test();
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait until all tasks are completed
        }

        Instant end = Instant.now();
        long duration = java.time.Duration.between(start, end).toMillis();

        return AjaxResult.success(duration);
    }
}
