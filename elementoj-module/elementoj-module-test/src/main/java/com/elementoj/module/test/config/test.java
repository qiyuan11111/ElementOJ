package com.elementoj.module.test.config;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FFmpegFrameFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class test {

    @GetMapping("/stream-video")
    public void streamVideo(HttpServletResponse response,
                            @RequestParam("videoPath") String videoFilePath,
                            @RequestParam("subtitlePath") String subtitleFilePath,
                            @RequestParam(value = "segmentDuration", defaultValue = "10") int segmentDurationInSeconds) throws IOException {
        // 设置 HTTP 响应头，告诉前端这是一个视频流
        response.setContentType("video/mp4");
        response.setHeader("Content-Disposition", "inline; filename=\"output.mp4\"");

        // 获取响应的输出流
        try (OutputStream outputStream = response.getOutputStream()) {
            // 创建一个 FFmpegFrameGrabber 来读取视频文件
            FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoFilePath);
            try {
                grabber.start();  // 开始读取视频

                int frameRate = (int) grabber.getFrameRate();  // 获取视频的帧率（每秒多少帧）
                int framesPerSegment = frameRate * segmentDurationInSeconds;  // 计算每个视频段的帧数

                // 使用 FFmpegFrameFilter 来应用字幕滤镜
                FFmpegFrameFilter subtitleFilter = new FFmpegFrameFilter("subtitles=" + subtitleFilePath, grabber.getImageWidth(), grabber.getImageHeight());
                subtitleFilter.start();  // 启动字幕滤镜

                Frame frame;
                FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputStream, grabber.getImageWidth(), grabber.getImageHeight(), grabber.getAudioChannels());
                recorder.setFrameRate(grabber.getFrameRate());  // 设置帧率
                recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);  // 设置视频编码器
                recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);  // 设置像素格式
                recorder.start();  // 开始录像

                int frameCount = 0;  // 记录已处理的帧数

                // 循环读取每一帧
                while ((frame = grabber.grab()) != null) {
                    subtitleFilter.push(frame);  // 将帧推入字幕滤镜
                    Frame filteredFrame = subtitleFilter.pull();  // 从滤镜中获取带有字幕的帧

                    // 将当前帧记录到输出流中
                    recorder.record(filteredFrame);
                    frameCount++;

                    // 如果到达分块的帧数限制，可以选择手动 flush 或结束当前块的传输（可选）
                    if (frameCount % framesPerSegment == 0) {
                        outputStream.flush();  // 将已处理的数据推送到前端
                    }
                }

                recorder.stop();  // 停止录像
                recorder.release();  // 释放资源

                subtitleFilter.stop();  // 停止字幕滤镜
                grabber.stop();  // 停止视频抓取器
            } catch (Exception e) {
                e.printStackTrace();  // 捕获并打印异常
            } finally {
                try {
                    grabber.release();  // 释放资源
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
