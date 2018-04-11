package com.lianweiq.netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author lianweiq
 * @Date 2018/4/2,
 * @Time 15:49
 * @Description
 */
public class NewDecoder extends LengthFieldBasedFrameDecoder{


    private static final int HEADER_SIZE = 5;
    private byte type;
    private int length;
    private String msgBody;
    /**
     * Creates a new instance.
     *
     * @param maxFrameLength    the maximum length of the frame.  If the length of the frame is
     *                          greater than this value, {@link TooLongFrameException} will be
     *                          thrown.
     * @param lengthFieldOffset the offset of the length field
     * @param lengthFieldLength
     */
    public NewDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    /**
     * 自定义消息解析
     * @param ctx
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in == null){
            return null;
        }
        if (in.readableBytes() < HEADER_SIZE){
            throw new Exception("错误的消息");
        }

        /**
         * 指针在往前跳
         */
        //读取的第一个字节，表示消息类型
        type = in.readByte();
        //剩余的可读字节表示消息长度
        length = in.readableBytes();


        //校验消息长度是否正确，不正确表示消息丢失
        if (in.readableBytes() < length){
            throw new Exception("消息不正确");
        }

        ByteBuf buf = in.readBytes(length);

        byte[] b = new byte[buf.readableBytes()];

        buf.readBytes(b);

        msgBody = new String(b,"UTF-8");

        Message msg = new Message(type,length,msgBody);
        return msg;
    }
}
