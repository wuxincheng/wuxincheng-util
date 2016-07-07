package com.wuxincheng.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 图片URL地址获取工具类
 * 
 * @author wuxincheng(wxcking)
 *
 */
public class CatchImageURLUtil {

	// 地址
	private static final String URL = "http://ip138.com/ips1388.asp?ip=65.55.24.218&action=2";
	// 编码
	private static final String ECODING = "GBK";
	// 获取img标签正则
	private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
	// 获取src路径的正则
	private static final String IMGSRC_REG = "http:\"?(.*?)(\"|>|\\s+)";

	/**
	 * 测试
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		CatchImageURLUtil cm = new CatchImageURLUtil();
		// 获得html文本内容
		String html = cm.getHTML(URL);
		
		int i = html.indexOf("<td align=\"center\"><ul class=\"ul1\"><li>本站主数据：");
		
		System.out.println(i);
		
		// String sst = html.substring(i, 50);
		
		// System.out.println(sst);
		
		System.out.println(html);
	}

	/***
	 * 获取HTML内容
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private String getHTML(String url) throws Exception {
		URL uri = new URL(url);
		URLConnection connection = uri.openConnection();
		InputStream in = connection.getInputStream();
		byte[] buf = new byte[1024];
		int length = 0;
		System.out.println(length);
		StringBuffer sb = new StringBuffer();
		while ((length = in.read(buf, 0, buf.length)) > 0) {
			sb.append(new String(buf, ECODING));
		}
		in.close();
		return sb.toString();
	}

	/***
	 * 获取ImageUrl地址
	 * 
	 * @param HTML
	 * @return
	 */
	private List<String> getImageUrl(String HTML) {
		Matcher matcher = Pattern.compile(IMGURL_REG).matcher(HTML);
		List<String> listImgUrl = new ArrayList<String>();
		while (matcher.find()) {
			listImgUrl.add(matcher.group());
		}
		return listImgUrl;
	}

	/***
	 * 获取ImageSrc地址
	 * 
	 * @param listImageUrl
	 * @return
	 */
	private List<String> getImageSrc(List<String> listImageUrl) {
		List<String> listImgSrc = new ArrayList<String>();
		for (String image : listImageUrl) {
			Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
			while (matcher.find()) {
				listImgSrc.add(matcher.group().substring(0,
						matcher.group().length() - 1));
			}
		}
		return listImgSrc;
	}

	/***
	 * 下载图片
	 * 
	 * @param listImgSrc
	 */
	public void Download(List<String> listImgSrc) {
		try {
			for (String url : listImgSrc) {
				String imageName = url.substring(url.lastIndexOf("/") + 1,
						url.length());
				URL uri = new URL(url);
				InputStream in = uri.openStream();
				FileOutputStream fo = new FileOutputStream(new File(imageName));
				byte[] buf = new byte[1024];
				int length = 0;
				System.out.println("开始下载:" + url);
				while ((length = in.read(buf, 0, buf.length)) != -1) {
					fo.write(buf, 0, length);
				}
				in.close();
				fo.close();
				System.out.println(imageName + "下载完成");
			}
		} catch (Exception e) {
			System.out.println("下载失败");
		}
	}

	/**
	 * 获取HTML等文本中第一张图片的URL地址
	 * 
	 * @param content
	 * @return
	 */
	public static String getFirstImgURLFromContent(String content) {
		if (content == null || "".equals(content)) {
			return "";
		}
		
		CatchImageURLUtil cm = new CatchImageURLUtil();
		
		// 获取图片标签
		List<String> imgUrl = cm.getImageUrl(content);
		
		if (imgUrl == null || imgUrl.size() < 1) {
			return "";
		}
		// 获取图片src地址
		List<String> imgSrc = cm.getImageSrc(imgUrl);
		
		if (null == imgSrc || imgSrc.size() < 1) {
			return "";
		}
		
		String url = imgSrc.get(0);
		
		return url;
	}
	
}
