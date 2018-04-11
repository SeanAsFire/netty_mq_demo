package com.lianweiq;


import com.lianweiq.Block.BlockHead;
import com.lianweiq.util.ByteUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author lianweiq
 * @Date 2018/3/28,
 * @Time 15:13
 * @Description
 *
 * 1.selector作为一个容器，在内部注册channel
 * 2.通过遍历selector的key可以拿到channel，以及当前channel已经就绪的事件
 * 3.key可以注册对象来提高辨识度(attach()连接)
 */
public class SelectorTest {

    public static void test(String[] args){
        try {
            SocketAddress address = new InetSocketAddress("127.0.0.1",8080);
            SocketChannel socketChannel = SocketChannel.open(address);

            Selector selector = Selector.open();
            //非阻塞状态通道
            socketChannel.configureBlocking(false);
            int interestKey = SelectionKey.OP_ACCEPT | SelectionKey.OP_READ;
            //表示监听的范围，对读监听,通道触发就绪事件
            SelectionKey key = socketChannel.register(selector,interestKey);

            int interestSet = key.interestOps();

            boolean isInterestedInAccept = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;

            int readySet = key.readyOps();

            key.isAcceptable();

            Channel channel = key.channel();

            Selector selector1 = key.selector();
            //关联对象
            key.attach(new ArrayList<>());

            SelectionKey selectionKey = socketChannel.register(selector,SelectionKey.OP_ACCEPT,new ArrayList<>());

            selector.select();

            Set selectKeys = selector.keys();

            Iterator keyIterator = selectKeys.iterator();

            while (keyIterator.hasNext()){
                SelectionKey selectionKey1 = (SelectionKey) keyIterator.next();
                if (selectionKey1.isAcceptable()){
                    //TODO  接收就绪
                }else if (selectionKey1.isConnectable()){
                    //TODO 连接就绪
                }else if (selectionKey1.isReadable()){
                    //TODO 读取数据就绪
                }else if (selectionKey1.isWritable()){
                    //TODO 写入数据就绪
                }
                keyIterator.remove();
                //节约空间，用不到的key
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){

        BlockHead blockHead = new BlockHead();
        blockHead.setCurrentHash("sdjbcsbdv");
        blockHead.setMerkleRootHash("dcuygsdvy");
        blockHead.setParentHash("aaaaaaaaaaaaaa");
        blockHead.setVersion("1.0");
        blockHead.setDate(new Date());
        byte[] data = ByteUtil.objectToBytes(blockHead);

        BlockHead head = (BlockHead) ByteUtil.bytesToObject(data);

        System.out.println(head.toString());
    }


}
