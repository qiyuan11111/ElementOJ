import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class Main {
    private static boolean flag = false;
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (!flag) {}
            count++;
        }).start();
        Thread.sleep(12);
        flag = true;
        Thread.sleep(12);
        System.out.println(count++);
    }

}
