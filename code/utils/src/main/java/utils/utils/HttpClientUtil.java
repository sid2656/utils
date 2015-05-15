package utils.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;


public class HttpClientUtil {

	private static Logger logger = LogUtil.getInstance().getLogger(HttpClientUtil.class);
	
	@SuppressWarnings({ "rawtypes" })
	public String get(Map map, String url){
		if (logger.isDebugEnabled()) {
			logger.debug("获取访问的URL(id, date,type,fee) - start"+url); //$NON-NLS-1$
		}
		String body=null;
		String path=url;
		// 处理对象
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpget=new HttpGet(path);
		List<NameValuePair> list=new ArrayList<NameValuePair>() ;
			Set keys=map.keySet();
			Iterator iter=keys.iterator();
			while(iter.hasNext()){
				String s=iter.next().toString();
				list.add(new BasicNameValuePair(s, map.get(s)!=null?map.get(s).toString():""));
			}
		try {
			String str=EntityUtils.toString(new UrlEncodedFormEntity(list));
		
			httpget.setURI(new URI(httpget.getURI().toString()+"?"+str));
			HttpResponse response=client.execute(httpget);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode==401||statusCode==400) {
				return null;
			}
			HttpEntity entity=response.getEntity();
			body=EntityUtils.toString(entity);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally{
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
		
	}
	
	@SuppressWarnings({ "rawtypes" })
	public String post(String url,Map map){
		
		String body=null;
		String path=url;
		// 处理对象
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httppost=new HttpPost(path);
		List<NameValuePair> list=new ArrayList<NameValuePair>() ;
		Set keys=map.keySet();
		Iterator iter=keys.iterator();
		while(iter.hasNext()){
			
			String s=iter.next().toString();
			
			list.add(new BasicNameValuePair(s, map.get(s).toString()));
		}
		try {
			httppost.setEntity(new UrlEncodedFormEntity(list));
			
			HttpResponse response=client.execute(httppost);
			HttpEntity entity=response.getEntity();
			body=EntityUtils.toString(entity);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return body;
	}
}
