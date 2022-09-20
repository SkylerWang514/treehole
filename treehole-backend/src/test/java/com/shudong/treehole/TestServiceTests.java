package com.shudong.treehole;

import com.shudong.treehole.service.TestService;
import com.shudong.treehole.vo.UserInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: treehole
 * @description: 测试类方法测试
 * @author: 王珺玉
 * @create: 2022-06-09 10:16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TreeholeApplication.class)
@EnableAutoConfiguration
public class TestServiceTests {
    @Autowired
    TestService testService;

    /**
    * 要进行测试的模块名：TestService
    * 测试例设计总思路：
    * 黑盒测试：
     *      1、测试是否能返回正确的值
    */
    @Test
    public void TestService_testDB(){
        //测试例1：测试两个形参分别为null时的返回值，以及会不会报错
        Long uid = null;
        UserInfoVO result = new UserInfoVO();

        //case 1
        System.out.println("case 1:");
        uid = 1L;

        try {
            result = testService.getUserInfoByID(uid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("case 1出错");
        }

        System.out.println(result);
    }
}
