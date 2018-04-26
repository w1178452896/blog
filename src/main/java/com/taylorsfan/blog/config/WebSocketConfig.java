package com.taylorsfan.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * web socket配置
 *
 * @author taylorsfan
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * registerStompEndpoints(StompEndpointRegistry registry)
     * 这个方法的作用是添加一个服务端点，来接收客户端的连接。
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //registry.addEndpoint("/socket")表示添加了一个/socket端点，客户端就可以通过这个端点来进行连接
        // withSockJS()的作用是开启SockJS支持
        registry.addEndpoint("/socket").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //registry.enableSimpleBroker("/topic")表示客户端订阅地址的前缀信息，
        // 也就是客户端接收服务端消息的地址的前缀信息
        config.enableSimpleBroker("/topic", "/chat");
        //registry.setApplicationDestinationPrefixes("/app")指服务端接收地址的前缀，
        // 意思就是说客户端给服务端发消息的地址的前缀
        config.setApplicationDestinationPrefixes("/app");
        //一对一需要加前缀/user
        config.setUserDestinationPrefix("/chat/");
    }
}
