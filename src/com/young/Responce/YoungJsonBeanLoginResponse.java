
package com.young.Responce;

import com.young.json.YoungJsonBeanBase;

/***
 * 登录出参实体类
 * 
 * @author Gaojun
 * 
 *         2013-4-2
 */
public class YoungJsonBeanLoginResponse extends YoungJsonBeanBase
{
	public int result = 1; // 成功失败标示
	public String errorMsg = null; // 失败原因
	public String userName = null; // 用户名
	public String passWord = null; // 登录密码
}
