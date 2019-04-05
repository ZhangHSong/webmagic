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

	private static int size = 0;// 共抓取到的信息数量
	private static String location = "hf";

	// 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {

		if (!page.getUrl().regex("https://" + location + "\\.zu\\.anjuke\\.com/fangyuan/[0-9]{10}.*").match()) {
			
			// 添加所有文章页
			page.addTargetRequests(page.getHtml().xpath("//div[@class='list-content']").links().all());// 限定文章列表获取区域
			
		} else {
			
			size++;// 数量加1

			// 存抓取到的数据，方便存入数据库
			AnjukeMainInformation amf = new AnjukeMainInformation();
			AnjukePhoto ap = new AnjukePhoto();

			// 设置编号
			amf.setNumber(getLongNumber(page.getHtml().xpath("//*[@id=\"houseCode\"]/text()").get()));
			// 设置名称
			amf.setName(page.getHtml().xpath("/html/body/div[3]/h3/text()").get());
			// 设置地址信息
			String add1 = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[8]/a[2]/text()").get();
			String add2 = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[8]/a[3]/text()").get();
			String add3 = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[8]/a[1]/text()").get();
			amf.setAddress(add1 + add2 + add3);
			// 设置面积
			amf.setArea(Double.valueOf(page.getHtml().xpath("/html/body/div[3]/div[1]/span[3]/em/text()").get()));
			// 设置房型
			amf.setHouseType(page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[2]/span[2]/text()").get());
			// 设置价格
			amf.setPrice(Double.valueOf(page.getHtml().xpath("/html/body/div[3]/div[1]/span[1]/em/text()").get()));
			// 设置付款方式
			amf.setPaymentMethod(
					page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[1]/span[2]/text()").get());
			// 设置出租方式
			amf.setRentWay(page.getHtml().xpath("/html/body/div[3]/div[1]/ul/li[1]/text()").get());
			// 设置联系人
			String contact1 = page.getHtml().xpath("/html/body/div[3]/div[2]/div[2]/div[1]/div[1]/h2/text()").get();
			String contact2 = page.getHtml().xpath("/html/body/div[3]/div[2]/div[2]/div[1]/div[2]/span/text()").get();
			amf.setContacts(contact1 + contact2);
			// 设置发布时间
			amf.setReleaseTime(
					getDateTime(page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div/text()").get()));
			// 设置描述
			amf.setDescription(page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/div[6]/allText()").get());
			// 把对象存入数据库
			new AnjukeDaoImpl().addMainInformation(amf);

			// 设置编号
			ap.setNumber(getLongNumber(page.getHtml().xpath("//*[@id=\"houseCode\"]/text()").get()));
			// 设置室内图
			ap.setIndoorPhoto(listToString(page.getHtml().xpath("//*[@id=\"room_pic_wrap\"]/div/img/@src").all()));
			// 设置户型图
			ap.setFloorPhoto(listToString(page.getHtml().xpath("//*[@id=\"hx_pic_wrap\"]/div/img/@src").all()));
			// 设置环境图
			ap.setEnvironmentPhoto(listToString(page.getHtml().xpath("//*[@id=\"surround_pic_wrap\"]/div/img/@src").all()));
			// 把对象存入数据库
			new AnjukeDaoImpl().addPhotoInformation(ap);
			
		}
	}

	// 把list转换为string，用,分割
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
	 * 获取房屋编号里的数字编号
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
		System.out.println("【爬虫开始】请耐心等待一大波数据到你碗里来...");
		startTime = System.currentTimeMillis();
		// 从用户博客首页开始抓，开启5个线程，启动爬虫
		Spider.create(new AnjukePageProcessor()).addUrl("https://" + location + ".zu.anjuke.com/").thread(5).run();
		endTime = System.currentTimeMillis();
		System.out.println("【爬虫结束】共抓取" + size + "篇文章，耗时约" + ((endTime - startTime) / 1000) + "秒，已保存到数据库，请查收！");
	}

}
