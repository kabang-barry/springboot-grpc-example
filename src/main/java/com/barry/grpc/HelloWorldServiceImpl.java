package com.barry.grpc;

import com.barry.grpc.helloworld.Greeting;
import com.barry.grpc.helloworld.HelloWorldServiceGrpc;
import com.barry.grpc.helloworld.Person;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GRpcService
public class HelloWorldServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldServiceImpl.class);

    @Override
    public void sayHello(Person request, StreamObserver<Greeting> responseObserver) {
        LOGGER.info("server received {}", request);

        final String message = "Hello " + request.getFirstName() + " " + request.getLastName() + "!";
        final Greeting greeting = Greeting.newBuilder().setMessage(message).build();
        LOGGER.info("server responded {}", greeting);

        responseObserver.onNext(greeting);
        responseObserver.onCompleted();
    }

}
