package com.wuxincheng.util.pdf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * pdf工具类
 * 
 * @author wuxincheng(wxcking)
 * @date 2016年7月7日 上午9:53:57
 * 
 */
public class PdfBoxUtil {

	private static final Logger logger = LoggerFactory.getLogger(PdfBoxUtil.class);

	/**
	 * PDF文件转换图片
	 * 
	 * 说明: 支持pdf扫描件的转换, 但需要引入JAI的四个jar包, 否则会转成空白的图片
	 * 
	 * @param pdfFile
	 *            完整PDF文件
	 * @param imagePath
	 *            输出图片的位置
	 * @throws IOException
	 */
	public static List<File> pdf2Images(File pdfFile) {
		logger.info("开始PDF转换成图片");

		List<File> imageTempFile = new ArrayList<File>();

		long t1 = System.currentTimeMillis();

		PDDocument document = null;
		FileOutputStream out = null;
		ImageOutputStream outImage = null;
		int pageCount = 0;
		try {
			// 加载指定pdf文件
			logger.debug("加载指定pdf文件");
			document = PDDocument.load(pdfFile);

			// 获取pdf文件页码
			logger.debug("获取pdf文件页码");
			pageCount = document.getNumberOfPages();
			logger.info("PDF总共有{}页", pageCount);

			logger.info("开始转换");
			PDFRenderer pdfRenderer = new PDFRenderer(document);
			for (int page = 0; page < document.getNumberOfPages(); ++page) {
				BufferedImage image = pdfRenderer.renderImageWithDPI(page, 150, ImageType.RGB);

				Iterator<ImageWriter> iter = ImageIO.getImageWritersBySuffix("jpg");
				ImageWriter writer = (ImageWriter) iter.next();

				File outFile = File.createTempFile("templatePdfImg", ".jpg");
				out = new FileOutputStream(outFile);
				outImage = ImageIO.createImageOutputStream(out);
				writer.setOutput(outImage);
				writer.write(new IIOImage(image, null, null));

				logger.info("第{}页图片转换成功", page + 1);

				imageTempFile.add(outFile);

				try {
					if (outImage != null) {
						outImage.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (IOException e) {
				}
			}

			long t2 = System.currentTimeMillis();

			logger.info("转换完成 耗时{}ms", (t2 - t1));
		} catch (Exception e) {
			logger.error("PDF转换图片异常{}", e.getMessage());
			return null;
		} finally {
			try {
				if (document != null) {
					document.close();
				}
			} catch (IOException e) {
			}
		}

		return imageTempFile;
	}

	/**
	 * 读取PDF文件
	 * 
	 * @param pdfFile
	 * @return PDF总页数
	 */
	public static int isPdfFile(String pdfFile) {
		PDDocument doc = null;
		int pageSize = 0;
		try {
			doc = PDDocument.load(new File(pdfFile));
			pageSize = doc.getNumberOfPages();
		} catch (IOException e) {
			return 0;
		} finally {
			try {
				if (doc != null) {
					doc.close();
				}
			} catch (IOException e) {
			}
		}
		return pageSize;
	}

}
