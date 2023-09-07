package com.hzau.InsertToMysql;

import com.hzau.InsertToMysql.Service.CompartmentService;
import com.hzau.InsertToMysql.Service.LoopService;
import com.hzau.InsertToMysql.Service.RnaService;
import com.hzau.InsertToMysql.Service.TissueService;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * 主应用程序
 * 启动入口，输入命令执行相应功能
 *
 * @author kfk
 * @date 2023/08/12
 */
public class MainApplication {
    static Logger logger = Logger.getLogger("MyLogger");


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String cmd;
        endLoop:
        while (true) {
            System.out.println(" 1. insertTissue_Cultivar " +
                    "\n 2. insertCompartment" +
                    "\n 3. insertLoop" +
                    "\n 4. insertRna" +
                    "\n 5. exit");
            System.out.println("请输入指令前面的序号：");
            cmd = scanner.nextLine();
            System.out.println("你输入的指令为：" + cmd);
            switch (cmd) {
                case "1": {
                    TissueService tissueService = new TissueService();
                    //插入新的组织
                    tissueService.addToTissue_Cultivar("D:\\DNA\\list.txt");
                    break;
                }
                case "2": {
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
                case "3": {
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
                case "4": {
                    //处理Gff3文件，插入t_rna表和t_rna_structure表。

                    long time = System.currentTimeMillis();
                    int count = 0;

                    File file = new File("./");//读取程序目录下的所有文件
                    File[] files = file.listFiles();
                    if (files != null) {
                        for (File file1 : files) {//遍历插入该目录下所有文件
                            RnaService rnaService = new RnaService();
                            System.out.println("当前处理的文件数量是：" + count);
                            System.out.println("当前处理的文件是:" + file1.getName());
                            System.out.println("更新了条数：" + rnaService.insertRnaFromFile(file1.getPath()));
                            count++;
                        }
                    }
                    System.out.println("总共插入了" + count + "个文件");
                    System.out.printf("总共耗时间：%d", System.currentTimeMillis() - time);
                    break;

                }
                case "5": {
                    System.out.println("程序结束");
                    break endLoop;
                }
                default:
                    System.out.println("输出指令序号无效请重新输入");
                    break;
            }

        }

    }
}
