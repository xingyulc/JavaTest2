package com.hand;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * @program: JavaTest2
 * @description:
 * @author: lichao
 * @create: 2018-07-21 10:11
 **/

public class XmlData {
    public  void xmldata(String num) throws TransformerException, ParserConfigurationException, IOException {

        //绝对路径构建
        URL url = new URL("http://hq.sinajs.cn/list="+num);
        //使用转换流
        BufferedReader br =
                new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));

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
        File file = new File(directory.getAbsolutePath() + File.separatorChar + "dataxml.xml");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw =
                new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));



            //DOM
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElement("stock");
            Element name1 = document.createElement("name");
            name1.setTextContent(datas[0]);

        Element open = document.createElement("open");
        open.setTextContent(datas[1]);

        Element close = document.createElement("close");
        close.setTextContent(datas[2]);

        Element current = document.createElement("current");
        current.setTextContent(datas[3]);


        Element high = document.createElement("high");
        high.setTextContent(datas[4]);

        Element low = document.createElement("low");
        low.setTextContent(datas[5]);


        root.appendChild(name1);
        root.appendChild(open);
        root.appendChild(close);
        root.appendChild(current);
        root.appendChild(high);
        root.appendChild(low);
        document.appendChild(root);

            //-------------

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("encoding", "UTF-8");

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            System.out.println(writer.toString());

            transformer.transform(new DOMSource(document), new StreamResult(new File(directory.getAbsolutePath() + File.separatorChar + "dataxml.xml")));

        System.out.println("解析Xml成功");
    }



}

