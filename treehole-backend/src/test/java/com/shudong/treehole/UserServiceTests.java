package com.shudong.treehole;

import com.shudong.treehole.entity.Userinfo;
import com.shudong.treehole.mapper.ThumbupMapper;
import com.shudong.treehole.mapper.UserinfoMapper;
import com.shudong.treehole.service.ThumbupService;
import com.shudong.treehole.service.UserinfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: treehole
 * @description: Userinfo测试
 * @author: 王珺玉
 * @create: 2022-06-09 10:33
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TreeholeApplication.class)
@EnableAutoConfiguration
public class UserServiceTests {
    @Autowired
    UserinfoService userinfoService;

    @Autowired
    ThumbupService thumbupService;

    /**
     * 要进行测试的模块名：checkUserInfo(Long uid)
     * 测试例设计总思路：
     * 黑盒测试：
     * 测试例1：测试形参为null时的返回值，以及会不会报错
     * 测试例2：测试形参格式正确，但是数据库里无匹配数据时的返回值，以及会不会报错
     * 测试例3：测试形参格式正确，且数据库里有匹配数据时的返回值，以及会不会报错
     */

    @Test
    public void UserinfoService_checkUserInfo_test1() {
        //测试例1：测试形参为null时的返回值，以及会不会报错

        Long uid = null;
        boolean result = false;

        //case 1
        System.out.println("case 1:");

        try {
            result = userinfoService.checkUserInfo(uid);
        } catch (Exception e) {
            System.out.println("case 1 出错！");
            e.printStackTrace();
        }

        System.out.println(result);
    }

    @Test
    public void UserinfoService_checkUserInfo_test3() {
        //测试例3：测试形参格式正确，且数据库里有匹配数据时的返回值，以及会不会报错

        Long uid = null;
        boolean result = false;

        //case 1
        System.out.println("case 1:");
        uid = 1L;

        try {
            result = userinfoService.checkUserInfo(uid);
        } catch (Exception e) {
            System.out.println("case 1 出错！");
            e.printStackTrace();
        }

        System.out.println(result);

        //case 2
        System.out.println("case 2:");
        uid = 2L;

        try {
            result = userinfoService.checkUserInfo(uid);
        } catch (Exception e) {
            System.out.println("case 2 出错！");
            e.printStackTrace();
        }

        System.out.println(result);
    }

    @Test
    public void UserinfoService_checkUserInfo_test2() {
        //测试例2：测试形参格式正确，但是数据库里无匹配数据时的返回值，以及会不会报错

        Long uid = null;
        boolean result = false;

        //case 1
        System.out.println("case 1:");
        uid = 3L;

        try {
            result = userinfoService.checkUserInfo(uid);
        } catch (Exception e) {
            System.out.println("case 1 出错！");
            e.printStackTrace();
        }

        System.out.println(result);
    }

    /**
     * 要进行测试的模块名：thumbUp(Long uid,Long tid) [王珺玉]
     * 测试例设计总思路：
     * 黑盒测试：
     * 测试例1：两个测试形参都为空时的返回值和数据库的结果，以及会不会报错
     * 测试例2：两个形参有一个为空时的返回值和数据库的结果，以及会不会报错
     * 测试例3：形参有效且两个都不符合数据库中现有数据时的返回值和结果，以及会不会报错
     * 测试例4：形参有效且两个都符合数据库中条件，且thumbState为0（取消点赞状态）时的返回值和结果，以及会不会报错
     * 测试例5：形参有效且两个都符合数据库中条件，且thumbState为1（点赞状态）时的返回值和结果，以及会不会报错
     */

    @Test
    public void ThumbUpService_thumbUp_test1() {
        //测试例1：两个测试形参都为空时的返回值和数据库的结果，以及会不会报错
        Long uid = null;
        Long tid = null;
        int result = 0;

        //case 1
        try {
            result = thumbupService.thumbUp(uid, tid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("case 1 出错！");
        }

        System.out.println(result);
    }

    @Test
    public void ThumbUpService_thumbUp_test2() {
        //测试例2：两个形参有一个为空时的返回值和数据库的结果，以及会不会报错
        Long uid = null;
        Long tid = null;
        int result = 0;

        //case 1
        uid = 1L;

        try {
            result = thumbupService.thumbUp(uid, tid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("case 1 出错！");
        }

        System.out.println(result);

        //case 2
        uid = null;
        tid = 1L;

        try {
            result = thumbupService.thumbUp(uid, tid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("case 2 出错！");
        }

        System.out.println(result);
    }

    @Test
    public void ThumbUpService_thumbUp_test3() {
        //测试例3：形参有效且两个都不符合数据库中现有数据时的返回值和结果，以及会不会报错
        Long uid = null;
        Long tid = null;
        int result = 0;

        //case 1
        uid = 1L;
        tid = 5L;

        try {
            result = thumbupService.thumbUp(uid, tid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("case 1 出错！");
        }

        System.out.println(result);

    }

    @Test
    public void ThumbUpService_thumbUp_test4() {
        //测试例4：形参有效且两个都符合数据库中条件，且thumbState为0（取消点赞状态）时的返回值和结果，以及会不会报错
        Long uid = null;
        Long tid = null;
        int result = 0;

        //case 1
        uid = 1L;
        tid = 4L;

        try {
            result = thumbupService.thumbUp(uid, tid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("case 1 出错！");
        }

        System.out.println(result);

    }

    @Test
    public void ThumbUpService_thumbUp_test5() {
        //测试例5：形参有效且两个都符合数据库中条件，且thumbState为1（点赞状态）时的返回值和结果，以及会不会报错，以及会不会报错
        Long uid = null;
        Long tid = null;
        int result = 0;

        //case 1
        uid = 1L;
        tid = 4L;

        try {
            result = thumbupService.thumbUp(uid, tid);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("case 1 出错！");
        }

        System.out.println(result);
    }

    @Autowired
    UserinfoMapper userinfoMapper;

    @Test
    public void UserInfoMapper_SelectUidByUOpenID_test1(){
        String uOpenId = null;
        uOpenId = "dwodwoiehfw89w7kjdwl";
        Long result = userinfoMapper.selectUidByUOpenId(uOpenId);
        System.out.println(result);
    }
}
