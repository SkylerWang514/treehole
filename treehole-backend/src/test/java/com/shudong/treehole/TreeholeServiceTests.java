package com.shudong.treehole;

import com.baomidou.mybatisplus.annotation.TableField;
import com.shudong.treehole.entity.Treehole;
import com.shudong.treehole.mapper.TreeholeMapper;
import com.shudong.treehole.service.TreeholeService;
import com.shudong.treehole.vo.TreeholeVO;
import com.shudong.treehole.vo.UserInfoVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TreeholeApplication.class)
@EnableAutoConfiguration
public class TreeholeServiceTests {
    @Autowired
    TreeholeService treeholeService;

    /**
    * 要进行测试的模块名：getTreeholeListWithWord(word)
    * 测试例设计总思路：
    * 黑盒测试：
     * 测试例1：测试形参为null时的返回值，以及会不会报错
     * 测试例2：测试形参格式正确，但是数据库里无匹配数据时的返回值，以及会不会报错
     * 测试例3：测试形参格式正确，且数据库里有匹配数据时的返回值，以及会不会报错
    */
    @Test
    public void TreeholeService_testGetTreeholeListWithWord_test1(){
        //测试例1：测试形参为null时的返回值，以及会不会报错
        String word = null;
        List<TreeholeVO> treeholeList = new ArrayList<>();

        //case 1
        System.out.println("case 1:");

        try {
            treeholeList = treeholeService.getTreeholeListWithWord(word);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("case 1出错");
        }

        System.out.println(treeholeList);
    }

    @Test
    public void TreeholeService_testGetTreeholeListWithWord_test2(){
        //测试例2：测试形参格式正确，但是数据库里无匹配数据时的返回值，以及会不会报错
        String word = null;
        List<TreeholeVO> treeholeList = new ArrayList<>();

        //case 1
        System.out.println("case 1:");
        word = "你";

        try {
            treeholeList = treeholeService.getTreeholeListWithWord(word);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("case 1出错");
        }

        System.out.println(treeholeList);
    }

    @Test
    public void TreeholeService_testGetTreeholeListWithWord_test3(){
        //测试例3：测试形参格式正确，且数据库里有匹配数据时的返回值，以及会不会报错
        String word = null;
        List<TreeholeVO> treeholeList = new ArrayList<>();

        //case 1
        System.out.println("case 1:");
        word = "这是";

        try {
            treeholeList = treeholeService.getTreeholeListWithWord(word);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("case 1出错");
        }

        System.out.println(treeholeList);
    }

    /**
    * 要进行测试的模块名：
    * 测试例设计总思路：
    * 黑盒测试：
    */
    @Test
    public void TreeholeService_testGetTreeholeList(){
        List<TreeholeVO> treeholeList = new ArrayList<>();
        try {
            treeholeList = treeholeService.getTreeholeList(1L,2,3);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("case 1出错");
        }
    }

    @Autowired
    TreeholeMapper treeholeMapper;

    @Test
    public void TreeholeMapper_testGetTidListByUid_test1(){
        Long uid = 1L;

        List<Treehole> tidList = new ArrayList<>();

        tidList = treeholeMapper.getTreeholeListByUid(uid);

        System.out.println(tidList);
    }
}
