package com.reacher.test;

import com.reacher.config.SpringConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
* 测试queryRunner是否是单例的
* */
public class QueryRunnerTest {

    @Test
    public void test(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        QueryRunner runner1 = (QueryRunner)ac.getBean("runner");
        QueryRunner runner2 = ac.getBean("runner",QueryRunner.class);
        System.out.println(runner1==runner2);
    }

}
