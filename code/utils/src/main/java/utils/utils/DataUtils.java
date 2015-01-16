/**
 * Project Name:mongo
 * File Name:DataUtils.java
 * Package Name:com.hdsx.taxi.mongo.utils
 * Date:2014年10月31日下午3:41:55
 * Copyright (c) 2014, sid Jenkins All Rights Reserved.
 * 
 *
*/

package utils.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:DataUtils
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2014年10月31日 下午3:41:55 
 * @author   sid
 * @see 	 
 */
@SuppressWarnings("rawtypes")
public class DataUtils {


	/**
	 * 判断当前参数列表是否均不为空
	 * @param objects
	 * @return
	 */
	public static boolean isNotEmpty(Object... objects) {
		for (Object obj : objects) {
			if (obj == null) {
				return false;
			}
			if (obj instanceof String) {
				if ("null".equals(((String) obj).trim())||"".equals(((String) obj).trim())) {
					return false;
				}
			}
			if (obj instanceof List){
				if (((List) obj).size()==0) {
					return false;
				}
			}
			if (obj instanceof Iterable){
				if (!((Iterable) obj).iterator().hasNext()) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		Iterable objs = new ArrayList<Object>();
		System.out.println(DataUtils.isNotEmpty(objs));
	}
}

