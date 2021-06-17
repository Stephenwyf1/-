package com.company.project.netty.server;

import com.company.project.netty.handle.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;


/**
 * Netty服务   服务启动监听器
 * @author zl
 * @date 2019-12-11
 *
 */
@Component
public class NettyServer {

    //定义分隔符号
    protected final static String DELIMITER = "\r\n";

    //引入spring系统环境变量
    @Autowired
    private Environment environment;

    //定义netty服务的端口好
    @Value("${netty.port}")
    private int port;

    //定义了一个用于接受netty服务对象的变量(常驻内存中)
    public static ServerSocketChannel serverSocketChannel;

    //定义了事件循环组
    private final EventLoopGroup boss = new NioEventLoopGroup();
    private final EventLoopGroup worker = new NioEventLoopGroup();


    /**
     * 定义服务销毁的方法
     */
    public void destroy() {
        if(serverSocketChannel != null) {
            serverSocketChannel.close();
        }
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }


    /**
     * 定义了一个开始的服务
     * @return
     * @throws Exception
     */
    public ChannelFuture   start() throws Exception {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 有数据立即发送
                .option(ChannelOption.TCP_NODELAY, true)
                // 保持连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {//设置了客户端连接socket属性。
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        // 在管道内做解码的动作
                        // 把分隔符转换成字节
                        ByteBuf delimiter = Unpooled.copiedBuffer(DELIMITER.getBytes());
                        ChannelPipeline p = sc.pipeline();
                        //往这个管道当中解码
                        p.addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
                        p.addLast(new StringEncoder(Charset.forName("utf-8")));
                        p.addLast(new StringDecoder(Charset.forName("utf-8")));
                        p.addLast(new ServerHandler());
                    }
                });


        // 绑定端口，同步等待成功
        ChannelFuture future = null;
        try {
            future = bootstrap.bind(port).sync();//真正让netty跑起来的重点
            if (future.isSuccess()) {
                serverSocketChannel = (ServerSocketChannel) future.channel();
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅地退出，释放线程池资源
            if (future != null && future.isSuccess()) {

            } else {

            }
        }
        return future;
    }
}
