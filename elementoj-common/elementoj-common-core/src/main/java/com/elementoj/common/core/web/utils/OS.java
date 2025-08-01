package com.elementoj.common.core.web.utils;

import java.io.*;

public class OS {
    public static String path_join(String... paths) {
        StringBuffer finpath = new StringBuffer("");
        boolean first = true;
        int var4 = paths.length;

        for (String path : paths) {
            if (first) {
                finpath = new StringBuffer(path);
                first = false;
            } else if (finpath.substring(finpath.length() - 1).equals("/") && path.substring(0, 1).equals("/")) {
                finpath.append(path.substring(1));
            } else if (finpath.substring(finpath.length() - 1).equals("/") && !path.substring(0, 1).equals("/")) {
                finpath.append(path);
            } else if (!finpath.substring(finpath.length() - 1).equals("/") && path.substring(0, 1).equals("/")) {
                finpath.append(path);
            } else if (!finpath.substring(finpath.length() - 1).equals("/") && !path.substring(0, 1).equals("/")) {
                finpath.append("/");
                finpath.append(path);
            }
        }

        return finpath.toString();
    }

    static String dirname(String path) {
        return (new File(path)).getParentFile().getPath();
    }

    public static boolean mkdir(String path) {
        return (new File(path)).mkdirs();
    }

    public static void chown(String path, String uid, String gid) {
        if (!"-1".equals(uid) || !"-1".equals(gid)) {
            String exce = "chown " + ("-1".equals(uid) ? "" : uid) + ("-1".equals(gid) ? "" : ":" + gid) + " " + path;
            exce(exce);
        }
    }

    public static void chmod(String path, Integer permission) {
        String exce = "chmod " + permission + " " + path;
        exce(exce);
    }

    public static String pwd() {
        String exce = "pwd";
        return exce(exce);
    }

    public static Boolean exists(String path) {
        return (new File(path)).exists();
    }

    static boolean remove(String path) {
        return (new File(path)).delete();
    }

    public static String getpwnam(String username) {
        String exce = "id -u " + username;
        String infos = exce(exce).trim();
        return "".equals(infos) ? "-1" : infos;
    }

    public static String getgrnam(String groupname) {
        String exce = "id -g " + groupname;
        String infos = exce(exce).trim();
        return "".equals(infos) ? "-1" : infos;
    }

    public static String old(String path) {
        StringBuilder new_path = new StringBuilder();

        for(int i = 0; i < path.length(); i = i + 1) {
            char c = path.charAt(i);
            if (i == 92) {
                new_path.append("\\\\");
            } else {
                new_path.append(String.valueOf(c));
            }
        }

        return new_path.toString();
    }

    static String exce(String cmd) {
        Runtime run = Runtime.getRuntime();

        try {
            String result = "";
            Process process = run.exec(cmd);
            process.waitFor();
            InputStream in = process.getInputStream();
            new BufferedReader(new InputStreamReader(in));
            StringBuilder out = new StringBuilder();
            byte[] b = new byte[8192];
            int n;
            while((n = in.read(b)) != -1) {
                out.append(new String(b, 0, n));
            }

            result = result + out.toString();
            in.close();
            process.destroy();
            return result;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
