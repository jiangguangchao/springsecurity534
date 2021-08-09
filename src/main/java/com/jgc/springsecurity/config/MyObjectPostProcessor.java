package com.jgc.springsecurity.config;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @program: springsecurity534
 * @description: 自定义ObjectPostProcessor
 * @author:
 * @create: 2021-08-03 11:47
 */
public class MyObjectPostProcessor implements ObjectPostProcessor {
    @Override
    public Object postProcess(Object object) {
        if (object instanceof AffirmativeBased) {
            System.out.println("找到决策器，将添加投票器");
            AffirmativeBased based = (AffirmativeBased) object;
            List<AccessDecisionVoter<?>> voterList =  based.getDecisionVoters();
            if (CollectionUtils.isEmpty(voterList)) {
                System.out.println("暂不添加投票器");
            } else {
                System.out.println("投票器：" + voterList);
                voterList.remove(0);
                voterList.add(new MyWebExpressionVoter());
            }
        }

        return object;
    }
}
