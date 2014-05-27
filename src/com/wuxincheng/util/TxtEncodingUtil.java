package com.wuxincheng.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 编码工具类
 * 
 * @author wuxincheng
 *
 */
public class TxtEncodingUtil {

	private static BufferedInputStream bin;

	public static String getCharset(String fileName) throws IOException{
        
        bin = new BufferedInputStream(new FileInputStream(fileName)); 
        
        int p = (bin.read() << 8) + bin.read();  
        
        String code = null;  
        
        switch (p) {  
            case 0xefbb:  
                code = "UTF-8";  
                break;  
            case 0xfffe:  
                code = "Unicode";  
                break;  
            case 0xfeff:  
                code = "UTF-16BE";  
                break;  
            default:  
                code = "GBK";  
        }  
        bin.close();
        return code;
	}
	
}
