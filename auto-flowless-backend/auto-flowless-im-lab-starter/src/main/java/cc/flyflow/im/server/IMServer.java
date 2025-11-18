package cc.flyflow.im.server;

import cc.flyflow.im.config.IMConfig;
import cc.flyflow.im.handler.IMServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Netty服务器
 */
@Slf4j
@Component
public class IMServer {
    
    @Autowired
    private IMConfig imConfig;
    
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    
    public void start() throws Exception {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup(imConfig.getClusterSize());
        
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
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
                                    .addLast(new IMServerHandler());
                        }
                    });
            
            ChannelFuture future = bootstrap.bind(imConfig.getServerPort()).sync();
            log.info("IM服务器已启动，端口: {}", imConfig.getServerPort());
            
            // 监听服务器关闭
            future.channel().closeFuture().sync();
            
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    
    public void stop() {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
    }
}
