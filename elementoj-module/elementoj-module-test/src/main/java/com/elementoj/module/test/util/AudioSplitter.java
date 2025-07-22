package com.elementoj.module.test.util;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AudioSplitter {

    public static void main(String[] args) {
        String audioFilePath = "C:\\KwDownload\\song\\test.wav";
        String outputDir = "C:\\KwDownload\\song\\output";
        int chunkDurationInSeconds = 10; // 每个分块的时间长度（秒）

        try {
            splitAudioByFrame(audioFilePath, outputDir, chunkDurationInSeconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void splitAudioByFrame(String audioFilePath, String outputDir, int chunkDurationInSeconds) throws UnsupportedAudioFileException, IOException {
        File audioFile = new File(audioFilePath);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
        AudioFormat format = audioInputStream.getFormat();
        float framesPerChunk = format.getFrameRate() * chunkDurationInSeconds;

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = audioInputStream.read(buffer)) != -1) {
            byteStream.write(buffer, 0, bytesRead);
        }

        byte[] audioBytes = byteStream.toByteArray();
        int frameSize = format.getFrameSize();
        int chunkSize = (int) framesPerChunk * frameSize;

        List<byte[]> audioChunks = new ArrayList<>();
        for (int i = 0; i < audioBytes.length; i += chunkSize) {
            int remainingBytes = Math.min(chunkSize, audioBytes.length - i);
            byte[] chunk = new byte[remainingBytes];
            System.arraycopy(audioBytes, i, chunk, 0, remainingBytes);
            audioChunks.add(chunk);
        }

        saveChunks(outputDir, audioChunks, format);

        audioInputStream.close();
    }

    private static void saveChunks(String outputDir, List<byte[]> audioChunks, AudioFormat format) throws IOException {
        for (int i = 0; i < audioChunks.size(); i++) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioChunks.get(i));
            AudioInputStream chunkStream = new AudioInputStream(byteArrayInputStream, format, audioChunks.get(i).length / format.getFrameSize());

            File outputFile = new File(outputDir + File.separator + "chunk_" + (i + 1) + ".wav");
            AudioSystem.write(chunkStream, AudioFileFormat.Type.WAVE, outputFile);

            chunkStream.close();
        }

        System.out.println("音频分块完成，总共分块数: " + audioChunks.size());
    }
}

