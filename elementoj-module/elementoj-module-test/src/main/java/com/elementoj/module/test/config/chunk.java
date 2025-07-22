package com.elementoj.module.test.config;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLOutput;

public class chunk {
    public static void main(String[] args) {
        Double A = 100.0;
        Double b = 100.0;
        System.out.println(A.equals(b));

    }

//    @RestController
//    @RequestMapping("/video")
//    public class VideoUploadController {
//
//        private final String uploadDir = "/path/to/upload/";
//        private final Map<String, Set<Integer>> uploadedChunks = new ConcurrentHashMap<>(); // 记录已上传的分块
//
//        @PostMapping("/uploadChunk")
//        public ResponseEntity<String> uploadChunk(@RequestParam("file") MultipartFile file,
//                                                  @RequestParam("chunkIndex") int chunkIndex,
//                                                  @RequestParam("totalChunks") int totalChunks,
//                                                  @RequestParam("videoId") String videoId) {
//            try {
//                // 创建视频文件的临时目录
//                File videoDir = new File(uploadDir + videoId);
//                if (!videoDir.exists()) {
//                    videoDir.mkdirs();
//                }
//
//                // 保存当前分块文件
//                String chunkFileName = String.format("%s/chunk_%d", videoDir.getAbsolutePath(), chunkIndex);
//                file.transferTo(new File(chunkFileName));
//
//                // 记录当前上传的分块
//                uploadedChunks.putIfAbsent(videoId, new HashSet<>());
//                uploadedChunks.get(videoId).add(chunkIndex);
//
//                // 如果所有分块都上传完成，触发合并操作
//                if (uploadedChunks.get(videoId).size() == totalChunks) {
//                    mergeChunks(videoId, totalChunks);
//                    uploadedChunks.remove(videoId); // 合并后清理记录
//                }
//
//                return ResponseEntity.ok("Chunk uploaded successfully");
//            } catch (IOException e) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading chunk");
//            }
//        }
//    }
//
//    private void mergeChunks(String videoId, int totalChunks) throws IOException {
//        File videoDir = new File(uploadDir + videoId);
//        File finalVideoFile = new File(uploadDir + videoId + ".mp4");
//
//        try (FileOutputStream fos = new FileOutputStream(finalVideoFile)) {
//            for (int i = 0; i < totalChunks; i++) {
//                File chunkFile = new File(videoDir, "chunk_" + i);
//                if (chunkFile.exists()) {
//                    byte[] chunkData = Files.readAllBytes(chunkFile.toPath());
//                    fos.write(chunkData);
//                    chunkFile.delete(); // 合并后删除临时分块文件
//                } else {
//                    throw new IOException("Missing chunk: " + i); // 若缺失分块，则抛出异常
//                }
//            }
//        }
//
//        // 删除分块存储目录
//        videoDir.delete();
//    }


}
