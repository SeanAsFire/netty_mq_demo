package com.lianweiq.util;

import java.io.*;

/**
 * @author lianweiq
 * @Date 2018/3/29,
 * @Time 16:31
 * @Description 字节流工具类
 */
public class ByteUtil {

    public static byte[] objectToBytes(Object ob) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(ob);
            objectOutputStream.flush();
            byte[] bytes = outputStream.toByteArray();
            return bytes;
        } catch (IOException e) {
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return null;
    }

    public static Object bytesToObject(byte[] bytes) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(inputStream));
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
