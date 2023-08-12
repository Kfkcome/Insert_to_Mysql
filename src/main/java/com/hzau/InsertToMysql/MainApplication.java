package com.hzau.InsertToMysql;

import com.hzau.InsertToMysql.Service.CompartmentService;
import com.hzau.InsertToMysql.Service.LoopService;
import com.hzau.InsertToMysql.Service.RnaService;
import com.hzau.InsertToMysql.Service.TissueService;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainApplication {
    static Logger logger = Logger.getLogger("MyLogger");


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String cmd;
        endLoop:
        while (true) {
            System.out.println("请输入指令：");
            cmd = scanner.nextLine();
            System.out.println("你输入的指令为：" + cmd);
            switch (cmd) {
                case "insertTissue_Cultivar": {
                    TissueService tissueService = new TissueService();
                    //插入新的组织
                    tissueService.addToTissue_Cultivar("D:\\DNA\\list.txt");
                    break;
                }
                case "insertCompartment": {
                    int count = 0;
                    File file = new File("./");
                    File[] files = file.listFiles();
                    if (files != null) {
                        for (File file1 : files) {
                            System.out.println("进入文件夹" + file1.getName());
                            File[] files1 = file1.listFiles();
                            if (files1 != null) {
                                for (File file2 : files1) {
                                    count++;
                                    System.out.println("当前插入的文件是：" + file2.getName());
                                    CompartmentService compartmentService = new CompartmentService();
                                    System.out.println("更新了条数：" + compartmentService.addPointFromFile(file2.getPath()));
                                }
                            }
                        }
                    }
                    System.out.println("总共插入了 " + count + " 个文件");
                    break;
                }
                case "insertLoop": {
                    long time = System.currentTimeMillis();
                    int count = 0;
                    File file = new File("./");
                    File[] files = file.listFiles();
                    if (files != null) {
                        for (File file1 : files) {
                            System.out.println("进入文件夹" + file1.getName());
                            File[] files1 = file1.listFiles();
                            if (files1 != null) {
                                for (File file2 : files1) {
                                    count++;
                                    System.out.println("当前插入的文件是：" + file2.getName());
                                    LoopService loopService = new LoopService();
                                    System.out.println("更新了条数" + loopService.insertFromOneFile(file2.getPath()));
                                }
                            }
                        }
                    }
                    System.out.println("总共插入了" + count + "个文件");
                    System.out.printf("总共耗时间：%d", System.currentTimeMillis() - time);
                    break;
                }
                case "insertRna": {
                    long time = System.currentTimeMillis();
                    int count = 0;

                    File file = new File("./");
                    File[] files = file.listFiles();
                    if (files != null) {
                        for (File file1 : files) {
                            RnaService rnaService = new RnaService();
                            System.out.println("当前插入的文件是:" + file1.getName());
                            System.out.println("更新了条数：" + rnaService.insertRnaFromFile(file1.getPath()));
                            count++;
                        }
                    }
                    System.out.println("总共插入了" + count + "个文件");
                    System.out.printf("总共耗时间：%d", System.currentTimeMillis() - time);
                    break;

                }
                case "exit": {
                    break endLoop;
                }
                default:
                    break;
            }

        }

    }
}
