package com.hand;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws IOException, TransformerException, ParserConfigurationException {

        //sz300170
        System.out.println("股票编码：sz300170");

        String num = "sz300170";
        //绝对路径构建
        URL url = new URL("http://hq.sinajs.cn/list="+num);
        System.out.println("开始获取数据");
        //使用转换流
        BufferedReader br =
                new BufferedReader(new InputStreamReader(url.openStream(),"gbk"));

        String msg = null;
        StringBuilder strs = new StringBuilder();
        while ((msg=br.readLine())!=null){
            strs.append(msg);
        }
        String[] strsData = strs.toString().split("\"");

        TestJson testJson = new TestJson();
        testJson.testjson(num);

        XmlData xmlData = new XmlData();
        xmlData.xmldata(num);

        br.close();

    }
}
