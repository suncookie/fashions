
package com.young.network;

import com.young.json.YoungJsonBeanBase;

/**
 * 网络请求回调
 * 
 * @author Gaojun
 * 
 *         2013-4-2
 */
public interface YoungJsonParserAPI
{
	public YoungJsonBeanBase parseJson(final String strResult);
}
