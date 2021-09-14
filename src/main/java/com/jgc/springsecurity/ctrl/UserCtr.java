package com.jgc.springsecurity.ctrl;

import com.alibaba.fastjson.JSON;
import com.jgc.springsecurity.domain.Person;
import com.jgc.springsecurity.domain.User;
import com.jgc.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/user")
//@Transactional
public class UserCtr {


    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String list() {
        return "userList";
    }

    @GetMapping("/u1")
    public User getUser(@RequestParam String username) {
        User u = userService.getUser(username);
        System.out.println(JSON.toJSONString(u));
        System.out.println("birthday:" + formatDate(u.getBirthday(), "UTC"));
        System.out.println("createTime:" + formatDate(u.getCreateTime(), "UTC"));
        System.out.println("leaveTime:" + formatDate(u.getLeaveTime(), "UTC"));
        System.out.println("startWorkTime:" + formatDate(u.getStartWrokTime(), "UTC"));
        return u;
    }


    @GetMapping("/update")
    public String update() {
        return "userUpdate";
    }

    @PostMapping("/add")
    public String add(User user) {
        System.out.println("打印userService:" + userService.getClass().getName());
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encoderPassword = encoder.encode(user.getPassword());
//        user.setPassword(encoderPassword);
        userService.saveUser(user);
        System.out.println("添加用户成功 userService:" + userService);
        return "user add success";
    }

    /**
     * 测试map在mybatis中的处理
     * @return
     */
    @GetMapping("/mapUser")
    public String addMapUser() {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = new Random().nextInt(100);
        map.put("username","dfbb" + n);
        map.put("password","dfbb");
        map.put("createTime",new Date());
        map.put("startWrokTime",new Date());
        map.put("birthday",new Date());
        map.put("leaveTime",new Date());

        userService.saveUserForMap(map);
        return "mapUser add success";

    }

    /**
     * 测试map在mybatis中的处理,使用这种转map的方式保存，如果对应的mapper.xml文件
     * 不指定任何的typeHandler，会报错。因为这种转map的方法是先将对象转字符串，Date类型
     * 在转字符串的时候会转为毫秒数（类型变为long），如果没指定typeHandler，则使用默认的
     * LongTypeHandler。所以会报错  Data truncation: Incorrect datetime value: '1631579998547' for column 'createTime' at row 1
     * @return
     */
    @GetMapping("/mapUser2")
    public String addMapUser2() {
        Map<String, Object> map = new HashMap<String, Object>();
        User u = new User();
        int n = new Random().nextInt(100);
        u.setUsername("ybq" + n);
        u.setPassword("ybq");
        u.setCreateTime(new Date());
        u.setLeaveTime(new Date());
        u.setStartWrokTime(new Date());
        u.setBirthday(new Date());

        map = JSON.parseObject(JSON.toJSONString(u), Map.class);
        userService.saveUserForMap(map);
        return "mapUser2 add success";

    }


    /**
     * 测试字符串类型的日期能否保存到数据库
     * @return
     */
    @GetMapping("/mapUser3")
    public String addMapUser3() {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = new Random().nextInt(100);
        map.put("username","dfbb" + n);
        map.put("password","dfbb");
        String str = formatDate(null, null);
        map.put("createTime",str);
        map.put("startWrokTime",str);
        map.put("birthday",str);
        map.put("leaveTime",str);

        userService.saveUserForMap(map);
        return "mapUser3 add success";

    }

    /**
     * 测试特殊日期字符串类型的日期能否保存到数据库，数据库有如下日期或时间类型和对应的范围（网上搜的）。
     *
     * 类型名称	            日期格式	                                日期范围	                                            存储需求
     * YEAR	                YYYY	                                1901 ~ 2155	                                        1 个字节
     * TIME	            HH:MM:SS	                        -838:59:59 ~ 838:59:59	                                    3 个字节
     * DATE	            YYYY-MM-DD	                        1000-01-01 ~ 9999-12-3	                                    3 个字节
     * DATETIME	    YYYY-MM-DD HH:MM:SS	                1000-01-01 00:00:00 ~ 9999-12-31 23:59:59	                    8 个字节
     * TIMESTAMP	YYYY-MM-DD HH:MM:SS	            1980-01-01 00:00:01 UTC ~ 2040-01-19 03:14:07 UTC	                4 个字节
     *
     * 比如Date类型不行小于1000年，可以将其对应的字符串设为900，然后测试数据库能否正常保存
     *
     * 经过测试发现，除了最后一个leaveTime字段，必须在范围之内，其他都可以在范围之外（
     * startWrokTime字段没测试），而且还可以正常读取，正常显示。说明上面的范围有部分是错误的。
     *
     * 网上查到另一种表述，说支持上面的时间段，然后说“支持”意味着尽管更早的值可能工作，但不能保证他们可以。
     *
     * @return
     */
    @GetMapping("/mapUser4")
    public String addMapUser4() {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = new Random().nextInt(100);
        map.put("username","dfbb" + n);
        map.put("password","dfbb");
        String str = formatDate(null,null);
        map.put("createTime","888-01-01 00:00:00");//对应数据库字段是datetime类型
        map.put("startWrokTime",str);//对应数据库字段是time类型
        map.put("birthday","777-01-01");//对应数据库字段是date类型
        map.put("leaveTime","1980-01-01 00:00:01");//对应数据库字段是timestamp类型

        userService.saveUserForMap(map);
        return "mapUser4 add success";

    }

    /**
     * 测试特殊日期字符串插入数据库。
     * 经过测试，下面不符合实际月份和日期的字符串可以正常插入数据库，但是读取的时候，转日期类型会报错
     * @return
     */
    @GetMapping("/mapUser5")
    public String addMapUser5() {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = new Random().nextInt(100);
        map.put("username","dfbb" + n);
        map.put("password","dfbb");
        String str = formatDate(null, null);
        map.put("createTime","888-00-00 00:00:00");//对应数据库字段是datetime类型
        map.put("startWrokTime",str);//对应数据库字段是time类型
        map.put("birthday","777-00-00");//对应数据库字段是date类型
        map.put("leaveTime","1980-01-01 00:00:01");//对应数据库字段是timestamp类型

        userService.saveUserForMap(map);
        return "mapUser5 add success";

    }


    /**
     * 测试特殊日期字符串插入数据库。
     * 经过测试，下面不符合实际月份和日期的字符串可以正常插入数据库，但是读取的时候，转日期类型会报错
     * @return
     */
    @GetMapping("/mapUser6")
    public String addMapUser6() {
        Map<String, Object> map = new HashMap<String, Object>();
        int n = new Random().nextInt(100);
        map.put("username","dfbb" + n);
        map.put("password","dfbb");
        String str = formatDate(null, null);
        map.put("createTime","1078-00-00 00:00:00");//对应数据库字段是datetime类型
        map.put("startWrokTime",str);//对应数据库字段是time类型
        map.put("birthday","1777-00-00");//对应数据库字段是date类型
        map.put("leaveTime","1980-01-01 00:00:01");//对应数据库字段是timestamp类型

        userService.saveUserForMap(map);
        return "mapUser6 add success";

    }

    private String formatDate(Date date,String timezoneStr) {
        if (date == null) {
            date = new Date();
        }

        if (timezoneStr == null) {
            timezoneStr = "GMT+8";
        }

        TimeZone timeZone = TimeZone.getTimeZone(timezoneStr);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(date);
    }



    public static void main(String[] args) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encoderPassword = encoder.encode("jgc");
//        System.out.println(encoderPassword);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
    }
}
