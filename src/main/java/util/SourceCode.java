package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

//����ץȡ������
public class SourceCode {

	public static final String ENCODING = "utf8";

	// ��ȡ��ҳ����
	/*
	 * @param url:Ŀ����ַ
	 * 
	 * @param encoding������
	 */
	public static String[] getHtmlResourceByUrlAnjuke(String url) {

		StringBuffer buffer = new StringBuffer();
		InputStreamReader isr = null;
		String[] arr = new String[2];
		try {
			// ������������
			URL urlObj = new URL(url);
			// ����������
			URLConnection uc = urlObj.openConnection();
			/*
			 * io �� �ӷ���������Դ�뵽����
			 */
			isr = new InputStreamReader(uc.getInputStream(), ENCODING);// �����ļ���������
			BufferedReader reader = new BufferedReader(isr);// ����
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
			// ������������
			URL urlObj = new URL(url);
			// ����������
			URLConnection uc = urlObj.openConnection();
			/*
			 * io �� �ӷ���������Դ�뵽����
			 */
			isr = new InputStreamReader(uc.getInputStream(), ENCODING);// �����ļ���������
			BufferedReader reader = new BufferedReader(isr);// ����
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

	// ������
	public static void main(String[] args) {

		String[] htmlString = getHtmlResourceByUrlLianjia("https://hf.lianjia.com/zufang/HF2225175850217906176.html");
		System.out.println(htmlString[0] + "___" + htmlString[1] + "___" + htmlString[2]);

	}

}
