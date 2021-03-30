package com.dc.spring.demo.spi;


import java.util.ServiceLoader;

/**
 * @Description
 * @Author DC
 * @Date 2021-03-25
 */
public class MainClass {
    private static ServiceLoader<IParseDoc> iParseDocs = ServiceLoader.load(IParseDoc.class);

    /**
     * SPI机制，读取指定目录下的文件，不需要手动的创建如;  IParseDoc iParseDoc1 = new ExcelParse();
     * 文件在 resources/META-INF/services下，文件名称是接口全类名，文件内容是实现类全类名
     * 通过读取实现类，通过 Class.forName 反射的机制创建对象
     * 作用：解耦
     * 应用；SpringBoot读取 spring.factaries
     *
     * @param args
     */
    public static void main(String[] args) {
        ServiceLoader<IParseDoc> iParseDocs = ServiceLoader.load(IParseDoc.class);
        for (IParseDoc iParseDoc : iParseDocs) {

            iParseDoc.parse();
        }
    }
}
