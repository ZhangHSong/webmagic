package webmagicdemo;

import dao.impl.MainInformationDaoImpl;
import entity.MainInformation;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import util.PingYinUtil;
import util.SourceCode;

public class LianJiaPageProcessor implements PageProcessor {

	private static String[] city = new String[] { "合肥", "北京", "上海", "广州", "深圳", "成都", "南京", "天津", "杭州", "武汉" };

	// 抓取网站的相关配置，包括：编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setUserAgent(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {

		boolean flag = false;
		for (int i = 0; i < city.length; i++) {
			if (page.getUrl().regex("https://" + PingYinUtil.getFirstSpell(city[i]) + "\\.lianjia\\.com/zufang/"
					+ PingYinUtil.getFirstSpell(city[i]).toUpperCase() + "[0-9]{19}.*").match()) {
				flag = true;
				break;
			}
		}

		if (!flag) {
			// 添加所有文章页
			page.addTargetRequests(page.getHtml().xpath("//div[@class='content__list']").links().all());// 限定文章列表获取区域
		} else {

			// 存抓取到的数据，方便存入数据库
			MainInformation mf = new MainInformation();
			String[] strings = SourceCode.getHtmlResourceByUrlLianjia(page.getUrl().toString());
			if (strings[0] != null && strings[1] != null) {

				mf.setSite("链家");
				// 设置名称
				mf.setName(page.getHtml().xpath("/html/body/div[3]/div[1]/div[3]/p/text()").get());
				// 设置城市
				mf.setCity(
						page.getHtml().xpath("/html/body/div[3]/div[1]/div[7]/p[1]/a[1]/text()").get().substring(0, 2));
				// 设置地址信息
				mf.setAddress(page.getHtml().xpath("//*[@id=\"map\"]/div[1]/div[2]/div[4]/label[1]/div/text()").get());
				// 设置面积
				mf.setArea(getTrueArea(page.getHtml().xpath("//*[@id=\"aside\"]/ul[1]/p/span[3]/text()").get()));
				// 设置房型
				mf.setHouseType(page.getHtml().xpath("//*[@id=\"aside\"]/ul[1]/p/span[2]/text()").get());
				// 设置价格
				mf.setPrice(Double.valueOf(page.getHtml().xpath("//*[@id=\"aside\"]/p[1]/span/text()").get()));
				// 设置付款方式
				mf.setPaymentMethod(page.getHtml().xpath("//*[@id=\"aside\"]/p[1]/text()").get().substring(5, 8));
				// 设置出租方式
				mf.setRentWay(page.getHtml().xpath("//*[@id=\"aside\"]/ul[1]/p/span[1]/text()").get());
				// 设置url地址
				mf.setUrl(page.getUrl().toString());
				// 设置坐标
				mf.setRow_col(strings[0] + "_" + strings[1]);
				// 设置地址信息
				mf.setAddress(strings[2]);
				// 把对象存入数据库
				new MainInformationDaoImpl().addMainInformation(mf);
			}
		}
	}

	private Double getTrueArea(String string) {
		return Double.valueOf(string.substring(0, string.length() - 1));
	}

	public static void main(String[] args) {
		System.out.println("【爬虫开始】请耐心等待一大波数据到你碗里来...");
		// 从用户博客首页开始抓，开启5个线程，启动爬虫
		Spider spider = Spider.create(new LianJiaPageProcessor());
		for (int i = 0; i < city.length; i++) {
			spider.addUrl("https://" + PingYinUtil.getFirstSpell(city[i]) + ".lianjia.com/zufang/");
		}
		spider.thread(5).run();
	}

}
