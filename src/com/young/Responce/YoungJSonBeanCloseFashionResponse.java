package com.young.Responce;

import java.util.List;

import com.young.json.YoungJsonBeanBase;

public class YoungJSonBeanCloseFashionResponse extends YoungJsonBeanBase {
	public int result = 1; // 成功失败标示
	public String errorMsg = null; // 失败原因
	public List<YCImageInfo> ImageList = null;
}
