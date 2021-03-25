package com.dc.spring.demo.spi;

/**
 * @Description
 * @Author DC
 * @Date 2021-03-25
 */
public class WordParse implements IParseDoc {
    @Override
    public void parse() {
        System.out.println("解析Word");
    }
}
