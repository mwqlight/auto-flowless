package cc.flyflow.im.client;

import cc.flyflow.im.config.IMConfig;
import cc.flyflow.im.handler.IMClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Netty客户端
 */
@Slf4j
@Component
public class IMClient {
    
    @Autowired
    private IMConfig imConfig;
    
    private EventLoopGroup group;
    
    public void start() throws Exception {
        group = new NioEventLoopGroup();
        
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    // 基于长度的帧解码器
                                    .addLast(new LengthFieldBasedFrameDecoder(1024 * 1024, 0, 4, 0, 4))
                                    // 长度字段预处理器
                                    .addLast(new LengthFieldPrepender(4))
                                    // 字符串解码器
                                    .addLast(new StringDecoder(CharsetUtil.UTF_8))
                                    // 字符串编码器
                                    .addLast(new StringEncoder(CharsetUtil.UTF_8))
                                    // 自定义业务处理器
                                    .addLast(new IMClientHandler());
                        }
                    });
            
            // 客户端需要连接到服务器，这里先注释掉，实际使用时需要配置服务器地址
            // ChannelFuture future = bootstrap.connect("localhost", imConfig.getServerPort()).sync();
            // log.info("IM客户端已连接到服务器: localhost:{}", imConfig.getServerPort());
            
            // future.channel().closeFuture().sync();
            
        } finally {
            // group.shutdownGracefully();
        }
    }
    
    public void stop() {
        if (group != null) {
            group.shutdownGracefully();
        }
    }
}
