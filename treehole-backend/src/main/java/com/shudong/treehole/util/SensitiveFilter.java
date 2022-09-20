package com.shudong.treehole.util;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/**
 * @program: treehole-backend
 * @description: 敏感词过滤器
 * @author: 王珺玉
 * @create: 2022-06-09 08:39
 **/

@Component
public class SensitiveFilter {
    private static final TrieNode rootNode = new TrieNode();

    private static class TrieNode{
        //关键词结束的标识
        private boolean isKeyWordEnd = false;
        //子节点(key 代表下级的节点字符， value是下级节点)
        private Map<Character, TrieNode> subNode = new HashMap<>();

        public boolean isKeyWordEnd() {
            return isKeyWordEnd;
        }

        public void setKeyWordEnd(boolean keyWordEnd) {
            isKeyWordEnd = keyWordEnd;
        }

        //添加子节点
        public void addSubNode(Character key,TrieNode subNode){
            this.subNode.put(key,subNode);
        }
        //获取子节点
        public TrieNode getSubNode(Character key){
            return subNode.get(key);
        }
    }

    @PostConstruct
    public void init(){
        try(
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        ){
            String keyword;
            while((keyword = reader.readLine()) != null){
                this.addKeyWord(keyword);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addKeyWord(String keyword){
        TrieNode tempNode = rootNode;
        for(int i = 0 ; i< keyword.length() ; ++i){
            char key = keyword.charAt(i);
            TrieNode subNode = tempNode.getSubNode(key);
            if(subNode == null){
                subNode = new TrieNode();
                tempNode.addSubNode(key,subNode);
            }
            tempNode = subNode;
            if(i == keyword.length() -1){
                tempNode.setKeyWordEnd(true);
            }
        }
    }

    /**
     * 过滤敏感词
     * @param text 待过滤的文本
     * @return    过滤后的文本
     */
    public boolean filter(String text){
        TrieNode tempNode = rootNode;
        int begin = 0;
        int position = 0;
        StringBuilder result = new StringBuilder();
        while(position < text.length()){
            char c = text.charAt(position);
            if(isSymbol(c)){
                if(tempNode == rootNode){
                    result.append(c);
                    begin++;
                }
                position++;
                continue;
            }
            tempNode = tempNode.getSubNode(c);
            if(tempNode == null){
                result.append(text.charAt(begin));
                position = ++begin;
                tempNode  = rootNode;
            }else if(tempNode.isKeyWordEnd()){
                return true;
            }else{
                position++;
            }
        }
        result.append(text.substring(begin));
        return false;
    }

    public boolean isSymbol(Character c){
        // 东亚文字 + 合法范围
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9fff);
    }
}
