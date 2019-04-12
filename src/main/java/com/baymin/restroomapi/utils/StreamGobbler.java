package com.baymin.restroomapi.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;


/**
 * 用于处理Runtime.getRuntime().exec产生的错误流及输出流
 * Created by baymin on 18-4-18.
 */
@Slf4j
public class StreamGobbler extends Thread {
    InputStream is;
    String type;
    OutputStream os;

    StreamGobbler(InputStream is, String type) {
        this(is, type, null);
    }

    StreamGobbler(InputStream is, String type, OutputStream redirect) {
        this.is = is;
        this.type = type;
        this.os = redirect;
    }

    public void run() {
        InputStreamReader isr = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            if (os != null)
                pw = new PrintWriter(os);

            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line=null;
            while ( (line = br.readLine()) != null) {
                if (pw != null)
                    pw.println(line);
                log.info("这里可以做手脚");
                log.info("{}>{}",type,line);
            }

            if (pw != null)
                pw.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

