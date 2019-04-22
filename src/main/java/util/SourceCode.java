package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

//数据抓取核心类
public class SourceCode {

	public static final String ENCODING = "utf8";

	// 获取网页数据
	/*
	 * @param url:目标网址
	 * 
	 * @param encoding：编码
	 */
	public static String[] getHtmlResourceByUrlAnjuke(String url) {

		StringBuffer buffer = new StringBuffer();
		InputStreamReader isr = null;
		String[] arr = new String[2];
		try {
			// 建立网络连接
			URL urlObj = new URL(url);
			// 打开网络连接
			URLConnection uc = urlObj.openConnection();
			/*
			 * io 流 从服务器下载源码到本地
			 */
			isr = new InputStreamReader(uc.getInputStream(), ENCODING);// 建立文件的输入流
			BufferedReader reader = new BufferedReader(isr);// 缓冲
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			/*
			 * lat: "39.995714", lng: "116.386038"
			 */
			int indexStart = buffer.indexOf("lat: \"");
			int indexEnd = buffer.indexOf("\",", indexStart);
			arr[0] = buffer.substring(indexStart + 6, indexEnd);
			indexStart = buffer.indexOf("lng: \"");
			for (int i = indexStart + 6; i < indexStart + 100; i++) {
				if (buffer.charAt(i) < '0' || buffer.charAt(i) > '9') {
					if (buffer.charAt(i) != '.') {
						indexEnd = i;
						break;
					}
				}
			}
			arr[1] = buffer.substring(indexStart + 6, indexEnd);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != isr)
					isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}
	
	
	
	public static String[] getHtmlResourceByUrlLianjia(String url) {

		StringBuffer buffer = new StringBuffer();
		InputStreamReader isr = null;
		String[] arr = new String[3];
		try {
			// 建立网络连接
			URL urlObj = new URL(url);
			// 打开网络连接
			URLConnection uc = urlObj.openConnection();
			/*
			 * io 流 从服务器下载源码到本地
			 */
			isr = new InputStreamReader(uc.getInputStream(), ENCODING);// 建立文件的输入流
			BufferedReader reader = new BufferedReader(isr);// 缓冲
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			/*
			 * lat: "39.995714", lng: "116.386038"
			 */
			int indexStart = buffer.indexOf("longitude: '");
			int indexEnd = buffer.indexOf("',", indexStart);
			arr[0] = buffer.substring(indexStart + 12, indexEnd);
			indexStart = buffer.indexOf("latitude: '");
			for (int i = indexStart + 12; i < indexStart + 100; i++) {
				if (buffer.charAt(i) < '0' || buffer.charAt(i) > '9') {
					if (buffer.charAt(i) != '.') {
						indexEnd = i;
						break;
					}
				}
			}
			arr[1] = buffer.substring(indexStart +11, indexEnd);
			indexStart = buffer.indexOf("g_conf.name = '");
			indexEnd = buffer.indexOf("';", indexStart);
			arr[2] = buffer.substring(indexStart + 15, indexEnd);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != isr)
					isr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	// 主函数
	public static void main(String[] args) {

		String[] htmlString = getHtmlResourceByUrlLianjia("https://hf.lianjia.com/zufang/HF2225175850217906176.html");
		System.out.println(htmlString[0] + "___" + htmlString[1] + "___" + htmlString[2]);

	}

}
