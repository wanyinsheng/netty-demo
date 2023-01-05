package cn.itcast.nio.c3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @Author wanys
 * @Date 2023/1/5 23:58
 * @Version 1.0
 **/
public class TestPaths {
    public static void main(String[] args) throws IOException {
//        Path source1 = Paths.get("1.txt"); // 相对路径 使用 user.dir 环境变量来定位 1.txt
//
//        Path source2 = Paths.get("d:\\1.txt"); // 绝对路径 代表了  d:\1.txt
//
//        Path source3 = Paths.get("d:/1.txt"); // 绝对路径 同样代表了  d:\1.txt

        Path projects = Paths.get("d:\\data", "projects"); // 代表了  d:\data\projects



        Path path1 = Paths.get("d:\\data\\projects\\a\\..\\b");
        System.out.println(path1);//d:\data\projects\a\..\b
        System.out.println(path1.normalize()); // 正常化路径  d:\data\projects\b


        //检查文件是否存在
        Path path2 = Paths.get("helloword/data.txt");
        System.out.println(Files.exists(path2));

        //创建一级目录
        Path path3 = Paths.get("helloword/d1");
        Files.createDirectory(path3);

        //创建多级目录用
        Path path4 = Paths.get("helloword/d1/d2");
        Files.createDirectories(path4);

        //拷贝文件
        Path source = Paths.get("helloword/data.txt");
        Path target = Paths.get("helloword/target.txt");
        Files.copy(source, target);


        //移动文件
        Path source1 = Paths.get("helloword/data.txt");
        Path target1 = Paths.get("helloword/data.txt");
        Files.move(source1, target1, StandardCopyOption.ATOMIC_MOVE);

        //删除文件
        Path target2 = Paths.get("helloword/target.txt");
        Files.delete(target2);


        //删除目录
        Path target3 = Paths.get("helloword/d1");
        Files.delete(target3);

    }
}
