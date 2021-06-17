package com.company.project;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.company.project.netty.server.NettyServer;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetAddress;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableAsync
@MapperScan("com.company.project.mapper")
@Slf4j
public class CompanyProjectApplication implements CommandLineRunner {
    @Autowired
    NettyServer nettyServer;
    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext application = SpringApplication.run(CompanyProjectApplication.class, args);

        Environment env = application.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Login: \thttp://{}:{}/login\n\t" +
                        "Doc: \thttp://{}:{}/doc.html\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"));
    }

    @Override
    public void run(String... args) throws Exception {
        nettyServer.start();
    }
}
