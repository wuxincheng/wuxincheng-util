package com.wuxincheng.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * zip解压缩文件
 * 
 * @author Administrator
 * 
 */
public class ZipUtil {

	public static void main(String[] args) {
		File zipFile = new File("G:/fund_test/jsfund_allocation_20131201.txt.zip");
		File srcFiles = new File("G:/fund_test/jsfund_allocation_20131201.txt");
		try {
			zipFiles(zipFile, null, srcFiles);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 压缩成zip文件
	 * 
	 * @param zipFile
	 *            压缩成的zip文件的路径和文件名称
	 * @param path
	 *            压缩后在哪个文件夹里, 如果为空则直接压缩(一般情况下为空就可以)
	 * @param srcFiles
	 *            源文件的路径和文件名称
	 * @throws IOException
	 */
	public static void zipFiles(File zipFile, String path, File... srcFiles)
			throws IOException {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
		zipFiles(out, path, srcFiles);
	}

	/**
	 * 解压zip文件
	 * 
	 * @param zipFile
	 *            压缩文件的路径和文件名称
	 * @param descDir
	 *            解压后存放的路径
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static void unZipFiles(File zipFile, String descDir)
			throws IOException {
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		Enumeration entries = zip.entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + zipEntryName).replace("\\*", "/");
			// 判断路径是否存在，不存在就创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}

			// 判断文件全路径是否是文件夹，如果是上面已经上传，不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}

			OutputStream out = new FileOutputStream(outPath);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		}
	}

	public static void unZipFiles(String zipFile, String descDir)
			throws IOException {
		unZipFiles(new File(zipFile), descDir);
	}

	public static void zipFiles(ZipOutputStream out, String path,
			File... srcFiles) throws IOException {
		path = "";
		if (path != null && !"".equals(path)) {
			path = completeDirectory(path);
		}
		byte[] buf = new byte[1024];
		for (int i = 0; i < srcFiles.length; i++) {
			if (srcFiles[i].isDirectory()) {
				File[] files = srcFiles[i].listFiles();
				String srcPath = srcFiles[i].getName();
				srcPath = completeDirectory(srcPath);
				out.putNextEntry(new ZipEntry(path + srcPath));

				if (path != null && !"".equals(path)) {
					srcPath = path + srcPath;
				}
				zipFiles(out, srcPath, files);
			} else {
				FileInputStream in = new FileInputStream(srcFiles[i]);
				out.putNextEntry(new ZipEntry(path + srcFiles[i].getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
		}
		out.close();
	}

	/**
	 * 
	 * 自动为文件目录添加最后的/
	 * 
	 * @param srcPath
	 *            原目录名
	 * @return
	 */
	private static String completeDirectory(String srcPath) {
		srcPath = srcPath.replaceAll("\\*", "/");
		if (!srcPath.endsWith("/")) {
			srcPath += "/";
		}
		return srcPath;
	}
}
