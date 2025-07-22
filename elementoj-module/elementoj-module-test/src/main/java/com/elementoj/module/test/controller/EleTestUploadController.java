package com.elementoj.module.test.controller;

import com.elementoj.common.core.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@CrossOrigin
public class EleTestUploadController {

    private final String uploadDir = "C:\\Users\\zym\\Desktop\\code\\video\\";
    private final String outputDir = "C:\\Users\\zym\\Desktop\\code\\video\\output\\";

    @PostMapping("/video/test")
    public AjaxResult testvideo(){
        return AjaxResult.success("nihao");
    }

    // 接收每个分块的上传
    @PostMapping("/chunk")
    public ResponseEntity<?> uploadChunk(
            @RequestParam("file") MultipartFile file,
            @RequestParam("filename") String filename,
            @RequestParam("chunkIndex") int chunkIndex,
            @RequestParam("totalChunks") int totalChunks) {

        try {
            // 创建临时文件存放目录
            File uploadDirPath = new File(uploadDir, filename);
            if (!uploadDirPath.exists()) {
                uploadDirPath.mkdirs();
            }

            // 保存每个分块文件
            File chunkFile = new File(uploadDirPath, filename + "-chunk-" + chunkIndex);
            try (InputStream inputStream = file.getInputStream();
                 OutputStream outputStream = Files.newOutputStream(chunkFile.toPath())) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            return ResponseEntity.ok("分块上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("分块上传失败");
        }
    }

//    @PostMapping("/chunks")
//    public ResponseEntity<?> uploadChunk(
//            @RequestParam("file") MultipartFile file,
//            @RequestParam("filename") String filename,
//            @RequestParam("chunkIndex") int chunkIndex,
//            @RequestParam("totalChunks") int totalChunks) {
//
//        try {
//            // 创建临时文件存放目录
//            File uploadDirPath = new File(uploadDir, filename);
//            if (!uploadDirPath.exists()) {
//                uploadDirPath.mkdirs();
//            }
//
//            // 保存每个分块文件
//            File chunkFile = new File(uploadDirPath, filename + "-chunk-" + chunkIndex);
//            try (InputStream inputStream = file.getInputStream();
//                 OutputStream outputStream = Files.newOutputStream(chunkFile.toPath())) {
//                byte[] buffer = new byte[1024];
//                int bytesRead;
//                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//            }
//
//            return ResponseEntity.ok("分块上传成功");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("分块上传失败");
//        }
//    }

    // 合并文件
    @PostMapping("/merge")
    public ResponseEntity<?> mergeChunks(@RequestParam("filename") String filename, @RequestParam("totalChunks") int totalChunks) {
        File uploadDirPath = new File(uploadDir, filename);
        if (!uploadDirPath.exists()) {
            return ResponseEntity.status(400).body("未找到分块文件");
        }

        System.out.println(totalChunks);

        File mergedFile = new File(outputDir, filename);
        System.out.println(mergedFile.getName());
        try (OutputStream outputStream = new FileOutputStream(mergedFile)) {
            // 按顺序合并所有分块文件
            for (int i = 0; i < totalChunks; i++) {
                File chunkFile = new File(uploadDirPath, filename + "-chunk-" + i);
                try (InputStream inputStream = Files.newInputStream(chunkFile.toPath())) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                // 合并之后删除分块文件
                chunkFile.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("文件合并失败");
        }

        // 删除临时分块存放目录
        uploadDirPath.delete();

        return ResponseEntity.ok("文件合并成功");
    }

    @GetMapping("/stream")
    public ResponseEntity<InputStreamResource> streamVideo(
            @RequestHeader HttpHeaders headers) {
        String fileName = "test.mp4";
        try {
            // 视频文件路径
            Path videoPath = Paths.get(outputDir + fileName);
            System.out.println(videoPath);
            // 检查文件是否存在
            if (!Files.exists(videoPath)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "视频文件未找到");
            }

            // 获取视频文件大小
            long videoSize = Files.size(videoPath);
            long rangeStart = 0;
            long rangeEnd = videoSize - 1;

            // 如果请求头包含 Range，则解析请求的范围
            headers.getRange();
            if (!headers.getRange().isEmpty()) {
                HttpRange httpRange = headers.getRange().get(0);
                rangeStart = httpRange.getRangeStart(videoSize);
                rangeEnd = httpRange.getRangeEnd(videoSize);
            }

            long contentLength = rangeEnd - rangeStart + 1;

            // 打开视频文件的输入流
            InputStream inputStream = Files.newInputStream(videoPath.toFile().toPath());
            inputStream.skip(rangeStart);

            // 设置响应头
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(HttpHeaders.CONTENT_TYPE, Files.probeContentType(videoPath));
            responseHeaders.set(HttpHeaders.ACCEPT_RANGES, "bytes");
            responseHeaders.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(contentLength));
            responseHeaders.set(HttpHeaders.CONTENT_RANGE, "bytes " + rangeStart + "-" + rangeEnd + "/" + videoSize);

            // 返回 206 Partial Content 状态码和流式视频数据
            return new ResponseEntity<>(
                    new InputStreamResource(inputStream),
                    responseHeaders,
                    HttpStatus.PARTIAL_CONTENT
            );

        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "读取视频文件时发生错误", e);
        }
    }

    @GetMapping("/streams")
    public ResponseEntity<?> streamsVideo(
            @RequestHeader HttpHeaders headers) throws IOException {

        String fileName1 = "test.mp4";
        String fileName2 = "test1.mp4";
        Path VIDEO_1_PATH = Paths.get(outputDir + fileName1);
        Path VIDEO_2_PATH = Paths.get(outputDir + fileName2);

        long video1Size = Files.size(VIDEO_1_PATH);
        long video2Size = Files.size(VIDEO_2_PATH);
        long totalVideoSize = video1Size + video2Size;

        long rangeStart = 0;
        long rangeEnd = totalVideoSize - 1;

        // 检查是否包含 Range 请求头，并解析请求的范围
        List<HttpRange> ranges = headers.getRange();
        if (!ranges.isEmpty()) {
            HttpRange httpRange = ranges.get(0);
            rangeStart = httpRange.getRangeStart(totalVideoSize);
            rangeEnd = httpRange.getRangeEnd(totalVideoSize);
        }

        // 确保结束位置不超过总视频的大小
        if (rangeEnd > totalVideoSize - 1) {
            rangeEnd = totalVideoSize - 1;
        }

        // 初始化输入流
        InputStream inputStream;
        long contentLength;

        System.out.println("rangeStart:" + rangeStart);
        System.out.println("rangeEnd:" + rangeEnd);
        System.out.println("video1Size:" + video1Size);
        System.out.println("===============");


        // 判断请求的范围是否跨越两个视频
        if (rangeStart < video1Size && rangeEnd < video1Size) {
            // 请求的范围完全在第一个视频
            inputStream = new FileInputStream(VIDEO_1_PATH.toFile());
            inputStream.skip(rangeStart);
            contentLength = rangeEnd - rangeStart + 1;
        } else if (rangeStart >= video1Size) {
            // 请求的范围完全在第二个视频
            inputStream = new FileInputStream(VIDEO_2_PATH.toFile());
            inputStream.skip(rangeStart - video1Size);
            contentLength = rangeEnd - rangeStart + 1;
        } else {
            // 请求的范围跨越了两个视频
            InputStream video1Stream = new FileInputStream(VIDEO_1_PATH.toFile());
            video1Stream.skip(rangeStart);
            long part1Length = video1Size - rangeStart;

            InputStream video2Stream = new FileInputStream(VIDEO_2_PATH.toFile());
            long part2Length = rangeEnd - video1Size + 1;

            // 使用 SequenceInputStream 将两个流合并
            inputStream = new SequenceInputStream(video1Stream, video2Stream);
            contentLength = part1Length + part2Length;
        }

        // 设置响应头
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.CONTENT_TYPE, "video/mp4");
        responseHeaders.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(contentLength));
        responseHeaders.set(HttpHeaders.CONTENT_RANGE, "bytes " + rangeStart + "-" + rangeEnd + "/" + totalVideoSize);
        responseHeaders.set(HttpHeaders.ACCEPT_RANGES, "bytes");

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .headers(responseHeaders)
                .body(new InputStreamResource(inputStream));
    }
}
