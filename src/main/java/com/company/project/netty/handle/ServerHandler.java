package com.company.project.netty.handle;


import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义服务端处理器
 */

@ChannelHandler.Sharable
@Component
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static ServerHandler serverHandler;
    private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);
    @PostConstruct
    public void init(){
        serverHandler = this;
    }


//    @Autowired
//    private GisLocationService gisLocationService;
//    @Autowired
//    private MonitorService monitorService;
//    @Autowired
//    private SysLogService sysLogService;
//    @Autowired
//    private CarMapper carMapper;
//    @Autowired
//    private ParameterRecordService parameterRecordService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public final static  List<ChannelHandlerContext>  chs = new ArrayList<ChannelHandlerContext>();
    /**
     * 在与客户端的连接已经建立之后将被调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        chs.add(ctx);
        logger.info("netty客户端与服务端连接开始...");
    }

    /**
     * 当从客户端接收到一个消息时被调用
     * msg 就是硬件传送过来的数据信息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//          //这是下面自己写的业务逻辑处理的方法
        System.out.println(msg.toString());

    }



    /**
     * 客户端与服务端断开连接时调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("netty客户端与服务端连接关闭...");
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        logger.info("信息接收完毕...");
    }

    /**
     * 在处理过程中引发异常时被调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
        System.out.println("异常信息：rn " + cause.getMessage());
    }



    /**
     * 获取定位数据逻辑
     *
     * @param msg
     * @return
     */
//    @Transactional(rollbackFor = Exception.class)
//    private void getGisLocation(String msg) {
//        String[] msgArr = msg.replace("#", "").split(",");
//        if ("CMD-T".equals(msgArr[1]) || "CMD-F".equals(msgArr[1])) {
//            GisLocation gisLocation = new GisLocation();
//            gisLocation.setDevCode(msgArr[0]);
//            if ("A".equals(msgArr[2])) {
//                if(!StringUtils.isEmpty(msgArr[5]) && !StringUtils.isEmpty(msgArr[6])) {
//                    gisLocation.setLatitude(
//                            msgArr[5].substring(msgArr[5].indexOf(":") + 1, msgArr[5].length() - 1));
//                    gisLocation.setLongitude(
//                            msgArr[6].substring(msgArr[6].indexOf(":") + 1, msgArr[6].length() - 1));
//                    gisLocation.setSpeed(Double.parseDouble(
//                            msgArr[7].substring(msgArr[7].indexOf(":") + 1)));
//                    gisLocation.setDirection(Integer.parseInt(msgArr[9]));
//                    gisLocation.setLatitudeFlag(String.valueOf(msgArr[5].charAt(msgArr[5].length() - 1)));
//                    gisLocation.setLongitudeFlag(String.valueOf(msgArr[6].charAt(msgArr[6].length() - 1)));
//                    gisLocation.setType("GPS");
//                    // TODO 后续逻辑处理
//                    gisLocationService.save(gisLocation);
//                }
//            }
//        } else if ("ALM-I".equals(msgArr[1])) {
//            Monitor monitor = new Monitor();
//            monitor.setDevCode(msgArr[0]);
//            monitor.setStatus(msgArr[2]);
//            // TODO 后续逻辑处理
//            monitorService.save(monitor);
//            List<Car> carList=carMapper.selectByHardwareMac(msgArr[0]);
//            if(CollectionUtils.isEmpty(carList)) {
//                String content="该车未与硬件MAC值进行绑定/绑定有误,请及时处理";
//                SysLog sysLog=new SysLog(1,content);
//                sysLogService.save(sysLog);
//            }else {
//                List<GisLocation> glList=gisLocationService.lambdaQuery().eq(GisLocation::getDevCode, msgArr[0]).orderByDesc(GisLocation::getAddTime).list();
//                //给小车赋值
//                if(!CollectionUtils.isEmpty(glList)) {
//                    carList.get(0).setLatitude(glList.get(0).getLatitude());
//                    carList.get(0).setLongitude(glList.get(0).getLongitude());
//                    carMapper.updateById(carList.get(0));
//                }
//                //给台账赋值
//                List<ParameterRecord> prLi=parameterRecordService.lambdaQuery().eq(ParameterRecord::getCarId, carList.get(0).getId())
//                        .ne(ParameterRecord::getStatus, "4").orderByAsc(ParameterRecord::getCreateTime).list();
//                if("ON".equals(msgArr[2])) {//上道
//                    if(!CollectionUtils.isEmpty(prLi) && prLi.size()>1) {
//                        if("1".equals(prLi.get(0).getStatus())) {
//                            if(!CollectionUtils.isEmpty(glList)) {
//                                prLi.get(0).setStatus("2");
//                                prLi.get(0).setUpTime(sdf.format(new Date()));
//                                prLi.get(0).setLongitude(glList.get(0).getLongitude());
//                                prLi.get(0).setLatitude(glList.get(0).getLatitude());
//                                parameterRecordService.lambdaUpdate().update(prLi.get(0));
//                            }
//                        }
//                    }else {
//                        List<ParameterRecord> prList=parameterRecordService.lambdaQuery().eq(ParameterRecord::getCarId, carList.get(0).getId())
//                                .eq(ParameterRecord::getStatus, "1").orderByAsc(ParameterRecord::getCreateTime).list();
//                        if(!CollectionUtils.isEmpty(prList) && !CollectionUtils.isEmpty(glList)) {
//                            prList.get(0).setStatus("2");
//                            prList.get(0).setUpTime(sdf.format(new Date()));
//                            prList.get(0).setLongitude(glList.get(0).getLongitude());
//                            prList.get(0).setLatitude(glList.get(0).getLatitude());
//                            parameterRecordService.lambdaUpdate().update(prList.get(0));
//                        }
//                    }
//                }else {//下道
//                    if(!CollectionUtils.isEmpty(prLi) && prLi.size()>1) {
//                        if("2".equals(prLi.get(0).getStatus())) {
//                            if(!CollectionUtils.isEmpty(glList)) {
//                                prLi.get(0).setStatus("3");
//                                prLi.get(0).setDownTime(sdf.format(new Date()));
//                                prLi.get(0).setDownLongitude(glList.get(0).getLongitude());
//                                prLi.get(0).setDownLatitude(glList.get(0).getLatitude());
//                                parameterRecordService.lambdaUpdate().update(prLi.get(0));
//                            }
//                        }
//                    }else {
//                        List<ParameterRecord> prList=parameterRecordService.lambdaQuery().eq(ParameterRecord::getCarId, carList.get(0).getId())
//                                .eq(ParameterRecord::getStatus, "2").orderByAsc(ParameterRecord::getCreateTime).list();
//                        if(!CollectionUtils.isEmpty(prList) && !CollectionUtils.isEmpty(glList)) {
//                            prList.get(0).setStatus("3");
//                            prList.get(0).setDownTime(sdf.format(new Date()));
//                            prList.get(0).setDownLongitude(glList.get(0).getLongitude());
//                            prList.get(0).setDownLatitude(glList.get(0).getLatitude());
//                            parameterRecordService.lambdaUpdate().update(prList.get(0));
//                        }
//                    }
//                }
//            }
//        }
//    }
}
