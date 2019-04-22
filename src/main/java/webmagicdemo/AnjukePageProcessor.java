package webmagicdemo;


import dao.impl.MainInformationDaoImpl;
import entity.MainInformation;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import util.PingYinUtil;
import util.SourceCode;

public class AnjukePageProcessor implements PageProcessor {

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
			if (page.getUrl().regex(
					"https://" + PingYinUtil.getFirstSpell(city[i]) + "\\.zu\\.anjuke\\.com/fangyuan/[0-9]{10}.*")
					.match()) {
				flag = true;
				break;
			}
		}

		if (!flag) {
			// 添加所有文章页
			page.addTargetRequests(page.getHtml().xpath("//div[@class='list-content']").links().all());// 限定文章列表获取区域
		} else {
			// 存抓取到的数据，方便存入数据库
			MainInformation mf = new MainInformation();
			String[] strings = SourceCode.getHtmlResourceByUrlAnjuke(page.getUrl().toString());
			if (strings[0] != null && strings[1] != null) {

				
				mf.setSite("安居客");
				
				// 设置名称
				mf.setName(page.getHtml().xpath("/html/body/div[3]/h3/text()").get());
				// 设置城市
				mf.setCity(getTureCity(page.getHtml().xpath("/html/body/div[2]/a[1]/text()").get()));

				// 设置面积
				double area = Double.valueOf(page.getHtml().xpath("/html/body/div[3]/div[1]/span[3]/em/text()").get());
				mf.setArea(area);
				// 设置房型
				String houseType = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[2]/span[2]/text()")
						.get();
				mf.setHouseType(houseType);
				// 设置价格
				double price = Double.valueOf(page.getHtml().xpath("/html/body/div[3]/div[1]/span[1]/em/text()").get());
				mf.setPrice(price);
				// 设置付款方式
				String payment = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[1]/span[2]/text()")
						.get();
				mf.setPaymentMethod(payment);
				// 设置出租方式
				String rentWay = page.getHtml().xpath("/html/body/div[3]/div[1]/ul/li[1]/text()").get();
				mf.setRentWay(rentWay);
				// 设置url地址
				mf.setUrl(page.getUrl().toString());
				// 设置坐标
				mf.setRow_col(strings[1] + "_" + strings[0]);
				// 设置地址信息
				String add1 = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[8]/a[2]/text()").get();
				String add3 = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[8]/a[1]/text()").get();
				String address = add1 + "区" + add3;
				mf.setAddress(address);
				// 把对象存入数据库
				new MainInformationDaoImpl().addMainInformation(mf);

			}
		}
	}

	private String getTureCity(String string) {
		int index = string.indexOf("安");
		return string.substring(0, index);
	}

	public static void main(String[] args) {
		System.out.println("【爬虫开始】请耐心等待一大波数据到你碗里来...");
		// 从用户博客首页开始抓，开启5个线程，启动爬虫
		Spider spider = Spider.create(new AnjukePageProcessor());
		for (int i = 0; i < city.length; i++) {
			spider.addUrl("https://" + PingYinUtil.getFirstSpell(city[i]) + ".zu.anjuke.com/");
		}
		spider.thread(5).run();
	}

}
