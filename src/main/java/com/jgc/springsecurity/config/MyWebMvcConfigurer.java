package com.jgc.springsecurity.config;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.SimpleType;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


@Component
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MyMsgCovt());
    }

    class MyMsgCovt extends AbstractHttpMessageConverter {

        public MyMsgCovt() {
            customMediaTypes();
        }

        @Override
        protected boolean supports(Class clazz) {
            return true;
        }



        @Override
        protected Object readInternal(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

            //这里仅仅是示例，默认请求参数比较少，如果请求参数比较多，字节流的字节个数大于1024，需要循环读取，直到全部读取完毕
            byte[] bytes = new byte[1024];
            inputMessage.getBody().read(bytes);
            String string = new String(bytes);//这里得到的字符串形式  username=小明&age=10

            JSONObject jsonObject = new JSONObject();
            String[] split = string.split("&");
            for (String s : split) {
                String[] kv = s.split("=");
                jsonObject.put(kv[0], URLDecoder.decode(kv[1], "utf-8"));
            }

            String jsonString = jsonObject.toJSONString();
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleType javaType = SimpleType.constructUnsafe(clazz);
            Object o = objectMapper.readValue(jsonString, javaType);

            return o;
        }

        @Override
        protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        }


        private void customMediaTypes() {
            MediaType myMediaType = new MediaType("application", "x-www-form-urlencoded");
            List<MediaType> list = new ArrayList<>();
            list.add(myMediaType);
            setSupportedMediaTypes(list);
        }

    }

}
