package com.jgc.springsecurity.ctrl;

import com.jgc.springsecurity.domain.Person;
import com.jgc.springsecurity.domain.User;
import com.sun.deploy.net.URLEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelCtrl {

//    private static final Logger log = LoggerFactory.getLogger(ExcelCtrl.class);
//
//    @GetMapping("/down")
//    public void down(HttpServletResponse response) {
//        try {
//
//            List<Person> list = new ArrayList<>();
//            for(int i =1;i<1000;i++) {
//                Person p = new Person();
//                p.setName("郭富城" + i);
//                p.setAddress("中国香港");
//                p.setBirthday(new Date());
//                p.setCompany("香港娱乐");
//                p.setIdCard("555555555" + i);
//                p.setSalary(Double.valueOf("100000.88"));
//                p.setSex(1);
//
//                list.add(p);
//            }
//
//
//            log.info("开始导出excel");
//            response.setContentType("application/vnd.ms-excel");
//            response.setCharacterEncoding("utf-8");
//            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//            String fileName = URLEncoder.encode("测试", "UTF-8");
//            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
////            EasyExcel.write(response.getOutputStream(), Person.class).sheet("模板").doWrite(list);
//            log.info("excel 导出完成");
//        } catch (IOException e) {
//            log.error("excel 导出异常", e);
//        }


//    }
}
