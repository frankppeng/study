package com.github.whvixd.demo.thrift.service;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import java.util.logging.Logger;

/**
 * Created by wangzhx on 2020/5/19.
 */
public class GreetingServer {
    private static final Logger logger = Logger.getLogger(GreetingServer.class.getName());

//    public static void main(String[] args) {
//        try {
//
//            TServerSocket serverTransport = new TServerSocket(9090);
//            TBinaryProtocol.Factory proFactory = new TBinaryProtocol.Factory();
//
//            /**
//             * 关联处理器与GreetingService服务实现
//             */
//            TProcessor processor = new GreetingService.Processor(new GreetingServiceImpl());
//
//            TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(serverTransport);
//            serverArgs.processor(processor);
//            serverArgs.protocolFactory(proFactory);
//            TServer server = new TThreadPoolServer(serverArgs);
//            logger.info("Start server on port 9090...");
//
//            server.serve();
//        } catch (TTransportException e) {
//            e.printStackTrace();
//        }
//    }
}
