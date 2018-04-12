package com.lianwei.nmq;


import java.io.*;

/**
 * @author lianweiq
 * @Date 2018/4/12,
 * @Time 20:34
 * @Description 文件操作类
 */
public class FileUtil {


    /**
     *文件读取，返回字节
     * @param path 路径
     * @return 返回的数据流
     */
    public static byte[] read(String path){
        path = "e://"+path;
        File file = new File(path);

        if (!file.exists()){
            return null;
        }
        long fileLen = file.length();
        FileInputStream inputStream = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int) fileLen);
            inputStream = new FileInputStream(file);
            BufferedInputStream in = new BufferedInputStream(inputStream);
            int bufSize = 1024;
            byte[] buf = new byte[bufSize];
            int len = 0;
            while (-1 != (len = in.read(buf,0,bufSize))){
               bos.write(buf,0,len);
            }
            return bos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将数据写入文件
     * @param data 数据
     * @param path 文件路径
     */
    public static void write(byte[] data,String path){
        path = "e://"+path;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
             outputStream = new FileOutputStream(path);
             inputStream = new ByteArrayInputStream(data);
             byte[] buf = new byte[1024];
             int len = 0;
             while (-1 != (len = inputStream.read(buf))){
                 outputStream.write(buf,0,len);
             }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
