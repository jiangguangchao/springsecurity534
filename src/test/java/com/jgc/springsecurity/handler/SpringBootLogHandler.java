package com.jgc.springsecurity.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
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
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @program: springsecurity534
 * @description: 从spring启动日志中提取bean，并按照bena的依赖关系生成xml，注意：需要将spring日志调为trace模式
 * @author:
 * @create: 2021-08-31 16:02
 */
public class SpringBootLogHandler {

    private static final Logger log = LoggerFactory.getLogger(SpringBootLogHandler.class);

    @Test
    public void  handlerLog() {
        String start = "Creating instance of bean";
        String end = "Finished creating instance of bean";

        String basePath = "D:\\logs\\";

        //日志文件
        String logFile = basePath + "log.txt";
        //xml文件生成位置
        String xmlFile = basePath + "log.xml";

        BufferedReader br = null;
        List<String> list = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(logFile));
            String line = br.readLine();
            Document document = getDocument();
            Element root = document.createElement("beans");

            int m = 0;
            list.add("beans" + "___" + m);
            Element current = root;
            while (line != null) {
                if (line.contains(start)) {

                    String beanStr = getBeanName(line,start);
                    if (StringUtils.isNotBlank(beanStr)) {
                        log.info("读取到bean[{}] 开始创建, current[{}]" , beanStr, current != null ? current.getNodeName() : "空");
                        m++;
                        list.add(beanStr + "___" + m);
                        Element bean = document.createElement(beanStr);
                        current.appendChild(bean);
                        current = bean;
                    }
                } else if (line.contains(end)) {
                    String beanStr = getBeanName(line,end);
                    list.add(beanStr + "___" + m);
                    m--;
                    String currentNodeName = current.getNodeName();
                    if (!beanStr.equals(currentNodeName)) {
                        log.error("当前bean名称[{}]和节点名称[{}]不一致", beanStr, currentNodeName);
                    }
                    current = (Element) current.getParentNode();
                    log.info("当前bean[{}]创建完成,current变为[{}]", beanStr,current != null ? current.getNodeName() : "空");
                }
                line = br.readLine();
            }
            document.appendChild(root);
            writeXmlToFile(document, xmlFile);
        } catch (Exception e) {
            log.error("日志文件解析异常", e);

        } finally {


            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            /**
             * 排查问题代码，如果不能正常生成xml文件，可以这段代码排查
             */
//            if (!CollectionUtils.isEmpty(list)) {
//                String path = "D:\\logs\\list.txt";
//                try {
//                    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)));
//                    for (String s : list) {
//                        if (StringUtils.isBlank(s)) {
//                            continue;
//                        }
//
//                        String str = getBeanNameStr(s);
//                        bw.write(str);
//                        bw.newLine();
//                    }
//                    bw.flush();
//                    bw.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
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
            beanStr = beanStr.replace("#", "_J_");
            beanStr = beanStr.replace("$", "_S_");
            return beanStr;
        }

        return null;

    }

    public String getBeanNameStr(String s) {
        String[] arr = s.split("___");
        String beanName = arr[0];
        int n = Integer.valueOf(arr[1]);
        String ss = "";
        for (int i = 0; i < n; i++) {
            ss = ss + "-";
        }
        return ss + beanName;
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
