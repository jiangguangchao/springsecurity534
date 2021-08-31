package com.jgc.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @program: springsecurity534
 * @description:
 * @author:
 * @create: 2021-08-31 16:02
 */
public class SpringBootLogHandler {

    private static final Logger log = LoggerFactory.getLogger(SpringBootLogHandler.class);

    @Test
    public void  handlerLog() {
        String start = "Creating shared instance of singleton bean";
        String end = "Finished creating instance of bean";
        String filePath = "C:\\Users\\Administrator\\Desktop\\111.txt";

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = br.readLine();
            Document document = getDocument();
            Element root = document.createElement("beans");
            Element parent = root;
            while (line != null) {
                if (line.contains(start)) {

                    String beanStr = getBeanName(line,start);
                    if (StringUtils.isNotBlank(beanStr)) {
                        log.info("读取到bean[{}], parent[{}]" , beanStr, parent.getNodeName());
                        Element bean = document.createElement(beanStr);
                        parent.appendChild(bean);
                        parent = bean;
                    }
                } else if (line.contains(end)) {
                    parent = (Element) parent.getParentNode();
                }
                line = br.readLine();
            }
            document.appendChild(root);
            writeXmlToFile(document, null);
        } catch (Exception e) {
            log.error("日志文件解析异常", e);
        }
    }

    public String getBeanName(String line, String match) {
        if (StringUtils.isBlank(line)) {
            return null;
        }

        int n1 = line.indexOf(match) + match.length();
        int n2 = line.indexOf("'", n1);
        int n3 = line.indexOf("'", n2 + 1);
        if (n1 > 0 && n2 > 0 && n2 > n1) {
            String beanStr = line.substring(n2 + 1, n3);
            beanStr = beanStr.replace("#", "_");
            beanStr = beanStr.replace("$", "-");
            return beanStr;
        }

        return null;

    }

    @Test
    public void f() {


        String filePath = "D:\\logs\\book1.xml";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document document = db.newDocument();
            Element beans = document.createElement("beans");
            Element ctrl = document.createElement("ctrl");
//            Element service = document.createElement("service");//com.jgc.springsecurity.Springsecurity534Application#MapperScannerRegistrar#0
            Element service = document.createElement("com.jgc.springsecurity.Springsecurity534Application_MapperScannerRegistrar_0");//com.jgc.springsecurity.Springsecurity534Application#MapperScannerRegistrar#0
//            service.setAttribute("name","com.jgc.springsecurity.Springsecurity534Application#MapperScannerRegistrar#0");
            service.setAttribute("name","ajflj");
            ctrl.appendChild(service);
            beans.appendChild(ctrl);
            document.appendChild(beans);
            Element d = (Element)service.getParentNode();
            log.info(JSON.toJSONString(document));


            // 创建TransformerFactory对象
            TransformerFactory tff = TransformerFactory.newInstance();
            // 创建 Transformer对象
            Transformer tf = tff.newTransformer();

            // 输出内容是否使用换行
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            // 创建xml文件并写入内容
            tf.transform(new DOMSource(document), new StreamResult(new File(filePath)));
            log.info("生成xml成功");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Document getDocument() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document document = db.newDocument();

            return document;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public void writeXmlToFile(Document document, String filePath) {
        if (StringUtils.isBlank(filePath)) {
            filePath = "D:\\logs\\book1.xml";
        }
        try {
            // 创建TransformerFactory对象
            TransformerFactory tff = TransformerFactory.newInstance();
            // 创建 Transformer对象
            Transformer tf = tff.newTransformer();

            // 输出内容是否使用换行
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            // 创建xml文件并写入内容
            tf.transform(new DOMSource(document), new StreamResult(new File(filePath)));
            log.info("生成xml成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
