package com.taylorsfan.blog.MQService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.taylorsfan.blog.util.SolrUtil;
import com.taylorsfan.blog.vo.BlogVo;



@Service
public class QueueReceiver implements MessageListener {
	
	@Autowired
	SolrUtil solrUtil;

	@Override
	public void onMessage(Message message) {
		
		 try {
			String result=((TextMessage)message).getText();
			Gson gson=new Gson();
			
			HashMap<String, String> map=gson.fromJson(result, HashMap.class);
			
			for (String s : map.keySet()) {
				
				if(MQService.Add.equals(s) || MQService.UPDATE.equals(s)){
					
					solrUtil.addDoc(gson.fromJson(map.get(s), BlogVo.class));
				}else if(MQService.DELETE.equals(s)){
					System.out.println(map.get(s));
					solrUtil.deleteDocumentById(gson.fromJson((map.get(s)), String.class));
				}else if(MQService.DELETEbYlIST.equals(s)){
					solrUtil.deleteDocumentByIdList((List<String>)(gson.fromJson(map.get(s), ArrayList.class)));
				}
			}	
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

}
