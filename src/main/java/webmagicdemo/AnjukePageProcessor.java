package webmagicdemo;

import java.util.List;

import dao.impl.AnjukeDaoImpl;
import entity.AnjukeMainInformation;
import entity.AnjukePhoto;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class AnjukePageProcessor implements PageProcessor {

	private static int size = 0;// ��ץȡ������Ϣ����
	private static String location = "hf";

	// ץȡ��վ��������ã����������롢ץȡ��������Դ�����
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {

		if (!page.getUrl().regex("https://" + location + "\\.zu\\.anjuke\\.com/fangyuan/[0-9]{10}.*").match()) {
			
			// �����������ҳ
			page.addTargetRequests(page.getHtml().xpath("//div[@class='list-content']").links().all());// �޶������б��ȡ����
			
		} else {
			
			size++;// ������1

			// ��ץȡ�������ݣ�����������ݿ�
			AnjukeMainInformation amf = new AnjukeMainInformation();
			AnjukePhoto ap = new AnjukePhoto();

			// ���ñ��
			amf.setNumber(getLongNumber(page.getHtml().xpath("//*[@id=\"houseCode\"]/text()").get()));
			// ��������
			amf.setName(page.getHtml().xpath("/html/body/div[3]/h3/text()").get());
			// ���õ�ַ��Ϣ
			String add1 = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[8]/a[2]/text()").get();
			String add2 = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[8]/a[3]/text()").get();
			String add3 = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[8]/a[1]/text()").get();
			amf.setAddress(add1 + add2 + add3);
			// �������
			amf.setArea(Double.valueOf(page.getHtml().xpath("/html/body/div[3]/div[1]/span[3]/em/text()").get()));
			// ���÷���
			amf.setHouseType(page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[2]/span[2]/text()").get());
			// ���ü۸�
			amf.setPrice(Double.valueOf(page.getHtml().xpath("/html/body/div[3]/div[1]/span[1]/em/text()").get()));
			// ���ø��ʽ
			amf.setPaymentMethod(
					page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[1]/span[2]/text()").get());
			// ���ó��ⷽʽ
			amf.setRentWay(page.getHtml().xpath("/html/body/div[3]/div[1]/ul/li[1]/text()").get());
			// ������ϵ��
			String contact1 = page.getHtml().xpath("/html/body/div[3]/div[2]/div[2]/div[1]/div[1]/h2/text()").get();
			String contact2 = page.getHtml().xpath("/html/body/div[3]/div[2]/div[2]/div[1]/div[2]/span/text()").get();
			amf.setContacts(contact1 + contact2);
			// ���÷���ʱ��
			amf.setReleaseTime(
					getDateTime(page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div/text()").get()));
			// ��������
			amf.setDescription(page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/div[6]/allText()").get());
			// �Ѷ���������ݿ�
			new AnjukeDaoImpl().addMainInformation(amf);

			// ���ñ��
			ap.setNumber(getLongNumber(page.getHtml().xpath("//*[@id=\"houseCode\"]/text()").get()));
			// ��������ͼ
			ap.setIndoorPhoto(listToString(page.getHtml().xpath("//*[@id=\"room_pic_wrap\"]/div/img/@src").all()));
			// ���û���ͼ
			ap.setFloorPhoto(listToString(page.getHtml().xpath("//*[@id=\"hx_pic_wrap\"]/div/img/@src").all()));
			// ���û���ͼ
			ap.setEnvironmentPhoto(listToString(page.getHtml().xpath("//*[@id=\"surround_pic_wrap\"]/div/img/@src").all()));
			// �Ѷ���������ݿ�
			new AnjukeDaoImpl().addPhotoInformation(ap);
			
		}
	}

	// ��listת��Ϊstring����,�ָ�
	private String listToString(List<String> stringList) {

		if (stringList == null) {
			return null;
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append(",");
			} else {
				flag = true;
			}
			result.append(string);
		}
		return result.toString();
	}

	private String getDateTime(String string) {
		int i = 0;
		int a = 0, b = 0;
		for (; i < string.length(); i++) {
			if (string.charAt(i) >= '0' && string.charAt(i) <= '9') {
				a = i;
				break;
			}
		}
		return string.substring(a);
	}

	/**
	 * ��ȡ���ݱ��������ֱ��
	 * 
	 * @param string
	 * @return
	 */
	private long getLongNumber(String string) {
		char[] c = string.toCharArray();
		int i = 0;
		int a = 0, b = 0;
		for (; i < c.length; i++) {
			if (c[i] >= '0' && c[i] <= '9') {
				a = i;
				break;
			}
		}
		for (; i < c.length; i++) {
			if (c[i] < '0' || c[i] > '9') {
				b = i;
				break;
			}
		}
		return Long.valueOf(string.substring(a, b));
	}

	public static void main(String[] args) {
		long startTime, endTime;
		System.out.println("�����濪ʼ�������ĵȴ�һ�����ݵ���������...");
		startTime = System.currentTimeMillis();
		// ���û�������ҳ��ʼץ������5���̣߳���������
		Spider.create(new AnjukePageProcessor()).addUrl("https://" + location + ".zu.anjuke.com/").thread(5).run();
		endTime = System.currentTimeMillis();
		System.out.println("�������������ץȡ" + size + "ƪ���£���ʱԼ" + ((endTime - startTime) / 1000) + "�룬�ѱ��浽���ݿ⣬����գ�");
	}

}
