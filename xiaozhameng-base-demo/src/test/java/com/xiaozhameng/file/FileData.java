package com.xiaozhameng.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * <p>Description: </p>
 * <p>Copyright: @2018</p>
 * <p>Company: YeePay</p>
 *
 * @author fengjun.qiao
 * @version V1.0 2018/5/27
 */
public class FileData {

    /**
     * 实现目录列表器
     */
    public static void main(String[] args) {
        File path = new File("G:\\个人学习");
        String[] list ;
        if (args.length ==0){
            list = path.list();
        }else {
            list = path.list(new DirFilter(args[0]));
        }

        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        for (String file: list){
            System.out.println(file);
        }
    }

    static class DirFilter implements FilenameFilter{
        private Pattern pattern;
        public DirFilter(String regex){
            pattern = Pattern.compile(regex);
        }
        @Override
        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    }

}
