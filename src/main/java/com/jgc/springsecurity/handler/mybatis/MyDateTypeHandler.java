package com.jgc.springsecurity.handler.mybatis;

import org.apache.ibatis.type.DateTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: springsecurity534
 * @description: 自定义日期处理，解决mysql毫秒自动四舍五入的问题
 *
 * @author:
 * @create: 2021-09-09 10:51
 */
public class MyDateTypeHandler extends DateTypeHandler {

    private static final Logger log = LoggerFactory.getLogger(MyDateTypeHandler.class);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String d1 = sdf.format(parameter);
        String d2 = sdf2.format(parameter);
        log.info("带毫秒时间：{}, 不带毫秒时间：{}", d2, d1);
        try {
            parameter = sdf.parse(d1);
        } catch (ParseException e) {
            log.error("日期去除毫秒异常");
        }

        super.setNonNullParameter(ps, i, parameter, jdbcType);
    }
}
