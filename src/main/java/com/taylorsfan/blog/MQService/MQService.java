package com.taylorsfan.blog.MQService;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Service
public class MQService {
	
	public static final String Add="add";
	public static final String DELETE="delete";
	public static final String UPDATE="update";
	public static final String DELETEbYlIST="deleteList";

	@Resource(name="jmsQueueTemplate") 
	JmsTemplate queuejsmTemplate ;
	
	@Resource(name="jmsTopicTemplate")
	JmsTemplate topicjsmTemplate ;
	
	public void sendQueueMsg(String destination,String type,Object o){

		queuejsmTemplate.send(destination, new MessageCreator(){

			@Override
			public Message createMessage(Session session) throws JMSException {
				HashMap<String, String> map=new HashMap<>();
				
				Gson gson=new Gson();
				map.put(type, gson.toJson(o));
				String result=gson.toJson(map);			
				return session.createTextMessage(result);
			}
			
		});
	}
	
}
