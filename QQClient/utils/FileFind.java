package QQClient.utils;
 
import java.io.File;
import java.io.IOException;
 
/*
    去D判断搜索 WeChar.exe文件
 */
public class FileFind {
    public static void main(String[] args) {
 
        //2.传入目录 和 文件名称
        searchFile(new File("D:/"),"WeChat.exe");
    }
 
    /**
     * 1.搜索某个目录下的全部文件，找到我们想要的文件。
     * @param dir 被搜索的源目录
     * @param filename 被搜索的文件名称
     */
    public static void searchFile(File dir,String filename){
        //3.判断dir是否是目录
        if (dir != null && dir.isDirectory()){
            //4.提取当前目录下的一级文件对象
            File[] files = dir.listFiles();
            //5.判断是否存在一级文件对象，存在才能遍历
            if (files != null && files.length > 0){
                for (File file : files) {
                    //6.判断当前遍历的一级文件对象是文件 还是 目录
                    if (file.isFile()){
                        //7.判断是不是我们要找的，是则将其路径输出
                        if (file.getName().contains(filename)){
                            System.out.println("找到了" + file.getAbsolutePath());
                            //启动它
                            try {
                                Runtime r = Runtime.getRuntime();
                                r.exec(file.getAbsolutePath());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }else {
                        //8.是文件夹，需要继续递归寻找
                        searchFile(file,filename);
                    }
                }
            }
        }else {
            System.out.println("对不起，当前搜索的位置不是文件夹！");
        }
    }
}