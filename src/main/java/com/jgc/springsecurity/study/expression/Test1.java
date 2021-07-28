package com.jgc.springsecurity.study.expression;


import com.jgc.springsecurity.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.SpelParseException;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class Test1 {

    private static final Logger log = LoggerFactory.getLogger(Test1.class);

    public static void main(String[] args) {
        new Test1().f3();
    }

    public void f1() {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("nihao","hellosdfeg nihao");
        ExpressionParser parser = new SpelExpressionParser();

        String str = parser.parseExpression("#nihao").getValue(context, String.class);
        log.info("str:" + str);
    }

    public void f2() {
        StandardEvaluationContext context = new StandardEvaluationContext();
        Person p = new Person();
        p.setName("jgc");
        context.setVariable("per",p);
        ExpressionParser parser1 = new SpelExpressionParser();
        ExpressionParser parser2 = new SpelExpressionParser();
        Expression expression1 = parser1.parseExpression("#per.readBook('c++')");
        Expression expression2 = parser1.parseExpression("#per.name");
        Expression expression3 = parser2.parseExpression("#per.name");
        String str1 = expression1.getValue(context,String.class);
        String str2 = expression1.getValue(context,String.class);
        String str3 = expression3.getValue(context,String.class);
        log.info("str1:" + str1);
        log.info("str2:" + str2);
        log.info("str3:" + str3);
    }

    public void f3() {
        StandardEvaluationContext context = new StandardEvaluationContext();
        Person p = new Person();
        p.setName("jgc");
        context.setRootObject(p);//设置rootObject后，之后取值不需要#和对象，直接写属性或者方法名
        ExpressionParser parser1 = new SpelExpressionParser();
        ExpressionParser parser2 = new SpelExpressionParser();
        Expression expression1 = parser1.parseExpression("readBook('c++')");//不需要#
        Expression expression2 = parser2.parseExpression("name");//不需要#
        String str1 = expression1.getValue(context,String.class);
        String str2 = expression2.getValue(context,String.class);
        log.info("str1:" + str1);
        log.info("str2:" + str2);
    }
}
