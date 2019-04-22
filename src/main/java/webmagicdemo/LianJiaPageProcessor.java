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

	private static String[] city = new String[] { "�Ϸ�", "����", "�Ϻ�", "����", "����", "�ɶ�", "�Ͼ�", "���", "����", "�人" };

	// ץȡ��վ��������ã����������롢ץȡ��������Դ�����
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
			// �����������ҳ
			page.addTargetRequests(page.getHtml().xpath("//div[@class='content__list']").links().all());// �޶������б��ȡ����
		} else {

			// ��ץȡ�������ݣ�����������ݿ�
			MainInformation mf = new MainInformation();
			String[] strings = SourceCode.getHtmlResourceByUrlLianjia(page.getUrl().toString());
			if (strings[0] != null && strings[1] != null) {

				mf.setSite("����");
				// ��������
				mf.setName(page.getHtml().xpath("/html/body/div[3]/div[1]/div[3]/p/text()").get());
				// ���ó���
				mf.setCity(
						page.getHtml().xpath("/html/body/div[3]/div[1]/div[7]/p[1]/a[1]/text()").get().substring(0, 2));
				// ���õ�ַ��Ϣ
				mf.setAddress(page.getHtml().xpath("//*[@id=\"map\"]/div[1]/div[2]/div[4]/label[1]/div/text()").get());
				// �������
				mf.setArea(getTrueArea(page.getHtml().xpath("//*[@id=\"aside\"]/ul[1]/p/span[3]/text()").get()));
				// ���÷���
				mf.setHouseType(page.getHtml().xpath("//*[@id=\"aside\"]/ul[1]/p/span[2]/text()").get());
				// ���ü۸�
				mf.setPrice(Double.valueOf(page.getHtml().xpath("//*[@id=\"aside\"]/p[1]/span/text()").get()));
				// ���ø��ʽ
				mf.setPaymentMethod(page.getHtml().xpath("//*[@id=\"aside\"]/p[1]/text()").get().substring(5, 8));
				// ���ó��ⷽʽ
				mf.setRentWay(page.getHtml().xpath("//*[@id=\"aside\"]/ul[1]/p/span[1]/text()").get());
				// ����url��ַ
				mf.setUrl(page.getUrl().toString());
				// ��������
				mf.setRow_col(strings[0] + "_" + strings[1]);
				// ���õ�ַ��Ϣ
				mf.setAddress(strings[2]);
				// �Ѷ���������ݿ�
				new MainInformationDaoImpl().addMainInformation(mf);
			}
		}
	}

	private Double getTrueArea(String string) {
		return Double.valueOf(string.substring(0, string.length() - 1));
	}

	public static void main(String[] args) {
		System.out.println("�����濪ʼ�������ĵȴ�һ�����ݵ���������...");
		// ���û�������ҳ��ʼץ������5���̣߳���������
		Spider spider = Spider.create(new LianJiaPageProcessor());
		for (int i = 0; i < city.length; i++) {
			spider.addUrl("https://" + PingYinUtil.getFirstSpell(city[i]) + ".lianjia.com/zufang/");
		}
		spider.thread(5).run();
	}

}
