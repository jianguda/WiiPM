package com.yurset.wiipm.logi;

import java.util.List;
import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.yurset.wiipm.base.XMove;

public class XMoveReader {
	public static List<XMove> readXML(InputStream inStream) {
		try {
			// 创建解析器
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxParser = spf.newSAXParser();
			// // 设置解析器的相关特性，true表示开启命名空间特性
			// saxParser.setProperty("http://xml.org/sax/features/namespaces",
			// true);
			XMoveHandler handler = new XMoveHandler();
			saxParser.parse(inStream, handler);
			return handler.getMoves();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}