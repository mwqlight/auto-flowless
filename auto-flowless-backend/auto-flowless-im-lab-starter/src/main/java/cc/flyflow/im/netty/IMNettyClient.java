package cc.flyflow.im.netty;

import cc.flyflow.im.handler.IMClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IMNettyClient {
    private final String host;
    private final int port;
    private Channel channel;
    private EventLoopGroup group;

    public IMNettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws InterruptedException {
        group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new IMClientHandler());
                        }
                    });

            log.info("IM Netty Client connecting to {}:{}", host, port);
            channel = bootstrap.connect(host, port).sync().channel();
            log.info("IM Netty Client connected to {}:{}", host, port);

        } finally {
            // 不要在这里关闭group，客户端需要保持连接
        }
    }

    public void sendMessage(String message) {
        if (channel != null && channel.isActive()) {
            channel.writeAndFlush(message);
        } else {
            log.error("Client is not connected to server");
        }
    }

    public void disconnect() throws InterruptedException {
        if (channel != null) {
            channel.close().sync();
        }
        if (group != null) {
            group.shutdownGracefully();
        }
        log.info("IM Netty Client disconnected");
    }

    public boolean isConnected() {
        return channel != null && channel.isActive();
    }
}