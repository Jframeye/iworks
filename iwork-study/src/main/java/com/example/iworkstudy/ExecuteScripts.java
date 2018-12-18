package com.example.iworkstudy;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 功能描述: 脚本执行
 *
 * @auther: yehl
 * @date: 2018/12/18 14:13
 */
public class ExecuteScripts {

    public static void main(String[] args) throws Exception {
        //可以执行脚本
        String command = "D:\\script.bat";
        Process ps = Runtime.getRuntime().exec(command);
        int exitValue = ps.waitFor();
        //当返回值为0时表示执行成功
        if (0 == exitValue) {
            BufferedInputStream inputStream = new BufferedInputStream(ps.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            inputStream.close();
            br.close();
        }
    }
}
