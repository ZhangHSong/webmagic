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
			if (page.getUrl().regex(
					"https://" + PingYinUtil.getFirstSpell(city[i]) + "\\.zu\\.anjuke\\.com/fangyuan/[0-9]{10}.*")
					.match()) {
				flag = true;
				break;
			}
		}

		if (!flag) {
			// �����������ҳ
			page.addTargetRequests(page.getHtml().xpath("//div[@class='list-content']").links().all());// �޶������б��ȡ����
		} else {
			// ��ץȡ�������ݣ�����������ݿ�
			MainInformation mf = new MainInformation();
			String[] strings = SourceCode.getHtmlResourceByUrlAnjuke(page.getUrl().toString());
			if (strings[0] != null && strings[1] != null) {

				
				mf.setSite("���ӿ�");
				
				// ��������
				mf.setName(page.getHtml().xpath("/html/body/div[3]/h3/text()").get());
				// ���ó���
				mf.setCity(getTureCity(page.getHtml().xpath("/html/body/div[2]/a[1]/text()").get()));

				// �������
				double area = Double.valueOf(page.getHtml().xpath("/html/body/div[3]/div[1]/span[3]/em/text()").get());
				mf.setArea(area);
				// ���÷���
				String houseType = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[2]/span[2]/text()")
						.get();
				mf.setHouseType(houseType);
				// ���ü۸�
				double price = Double.valueOf(page.getHtml().xpath("/html/body/div[3]/div[1]/span[1]/em/text()").get());
				mf.setPrice(price);
				// ���ø��ʽ
				String payment = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[1]/span[2]/text()")
						.get();
				mf.setPaymentMethod(payment);
				// ���ó��ⷽʽ
				String rentWay = page.getHtml().xpath("/html/body/div[3]/div[1]/ul/li[1]/text()").get();
				mf.setRentWay(rentWay);
				// ����url��ַ
				mf.setUrl(page.getUrl().toString());
				// ��������
				mf.setRow_col(strings[1] + "_" + strings[0]);
				// ���õ�ַ��Ϣ
				String add1 = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[8]/a[2]/text()").get();
				String add3 = page.getHtml().xpath("/html/body/div[3]/div[2]/div[1]/ul[1]/li[8]/a[1]/text()").get();
				String address = add1 + "��" + add3;
				mf.setAddress(address);
				// �Ѷ���������ݿ�
				new MainInformationDaoImpl().addMainInformation(mf);

			}
		}
	}

	private String getTureCity(String string) {
		int index = string.indexOf("��");
		return string.substring(0, index);
	}

	public static void main(String[] args) {
		System.out.println("�����濪ʼ�������ĵȴ�һ�����ݵ���������...");
		// ���û�������ҳ��ʼץ������5���̣߳���������
		Spider spider = Spider.create(new AnjukePageProcessor());
		for (int i = 0; i < city.length; i++) {
			spider.addUrl("https://" + PingYinUtil.getFirstSpell(city[i]) + ".zu.anjuke.com/");
		}
		spider.thread(5).run();
	}

}
