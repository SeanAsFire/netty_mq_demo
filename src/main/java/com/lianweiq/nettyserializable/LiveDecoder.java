package com.lianweiq.nettyserializable;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

/**
 * @author lianweiq
 * @Date 2018/4/4,
 * @Time 14:54
 * @Description
 */
public class LiveDecoder extends ReplayingDecoder<LiveDecoder.LiveState> {

    public enum LiveState {
        LENGTH, CONTENT
    }

    private LiveMessage message = new LiveMessage();

    /**
     * Creates a new instance with no initial state (i.e: {@code null}).
     */
    public LiveDecoder() {
        super(LiveState.LENGTH);
    }

    /**
     * Decode the from one {@link ByteBuf} to an other. This method will be called till either the input {@link ByteBuf}
     * has nothing to read when return from this method or till nothing was read from the input {@link ByteBuf}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link ByteToMessageDecoder} belongs to
     * @param in the {@link ByteBuf} from which to read data
     * @param out the {@link List} to which decoded messages should be added
     * @throws Exception is thrown if an error occurs
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        switch (state()) {
        case LENGTH:
            int length = in.readInt();
            if (length > 0) {
                checkpoint(LiveState.CONTENT);
            } else {
                out.add(message);
            }
            break;
        case CONTENT:
            byte[] bytes = new byte[message.getLength()];
            in.readBytes(bytes);
            String content = new String(bytes);
            message.setContent(content);
            break;
        default:
            throw new IllegalStateException("Invaliid State!");
        }
    }
}
