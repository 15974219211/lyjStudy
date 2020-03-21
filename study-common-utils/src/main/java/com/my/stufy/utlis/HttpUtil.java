package com.my.stufy.utlis;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;


public class HttpUtil {


	public static void main(String[] args) {
		download("http://www.appv6.com/json/gn.json", "dxy.txt", "F:/dxy");

	}
	
	/**
	 * 下载文件
	 * 
	 * @param urlString 文件网络地址
	 * @param filename 文件名称
	 * @param savePath 传输的地址
	 * @throws Exception
	 */
	public static void download(String urlString, String filename,
			String savePath)  {
		// 输入流
		InputStream is = null;
		OutputStream os = null;
		try {
			// 构造URL
			URL url = new URL(urlString);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			is = con.getInputStream();
			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf = new File(savePath);
			if (!sf.exists()) {

				sf.mkdirs();
			}
			os = new FileOutputStream(sf.getPath() + "/" + filename);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 完毕，关闭所有链接
			try {
				if(is != null){
					is.close();
				}
				if(os != null){
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
