package com.hand;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * @program: JavaTest2
 * @description:
 * @author: lichao
 * @create: 2018-07-21 10:41
 **/


public class TestJson {
    public  void testjson(String num) throws IOException {
        //绝对路径构建
        URL url = new URL("http://hq.sinajs.cn/list="+num);
        //使用转换流
        BufferedReader br =
                new BufferedReader(new InputStreamReader(url.openStream(),"gbk"));

        String msg = null;
        StringBuilder strs = new StringBuilder();
        while ((msg=br.readLine())!=null){
            strs.append(msg);
        }

        String[] strsData = strs.toString().split("\"");
        String[] datas = strsData[1].split(",");


        File directory = new File("Exam3/tmp");
        if(!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(directory.getAbsolutePath() + File.separatorChar + "datajson.json");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw =
                new BufferedWriter(new OutputStreamWriter(fos,"gbk"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",datas[0]);
        jsonObject.put("open",Double.valueOf(datas[1]));
        jsonObject.put("close",Double.valueOf(datas[2]));
        jsonObject.put("current",Double.valueOf(datas[3]));
        jsonObject.put("high",Double.valueOf(datas[4]));
        jsonObject.put("low",Double.valueOf(datas[5]));
        bw.write(jsonObject.toString());
        bw.flush();
        bw.close();
        br.close();

        System.out.println("解析为json成功！");
    }
}
