package com.yurset.wiipm.logi;

import java.util.List;
import java.io.InputStream;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.util.Log;

import com.yurset.wiipm.base.XPokemon;

public class XPMReader {
	public static List<XPokemon> readXML(InputStream inStream) {
		try {
			// 创建解析器
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser saxParser = spf.newSAXParser();
			// // 设置解析器的相关特性，true表示开启命名空间特性
			// saxParser.setProperty("http://xml.org/sax/features/namespaces",
			// true);
			XPMHandler handler = new XPMHandler();
			saxParser.parse(inStream, handler);
			inStream.close();
			return handler.getPokemons();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}