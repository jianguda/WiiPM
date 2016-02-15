package com.yurset.wiipm.logi;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.yurset.wiipm.base.XMove;

public class XMoveHandler extends DefaultHandler {
	private XMove current;
	private String tagName = null;// 当前解析的元素标签
	private List<XMove> moves = null;

	public List<XMove> getMoves() {
		return moves;
	}

	// 接收文档开始的通知。当遇到文档的开头的时候，调用这个方法，可以在其中做一些预处理的工作。
	@Override
	public void startDocument() throws SAXException {
		moves = new ArrayList<XMove>();
	}

	// 接收元素开始的通知。当读到一个开始标签的时候，会触发这个方法。其中namespaceURI表示元素的命名空间；
	// localName表示元素的本地名称（不带前缀）；qName表示元素的限定名（带前缀）；atts 表示元素的属性集合
	@Override
	public void startElement(String namespaceURI, String localName,
							 String qName, Attributes atts) throws SAXException {

		if (localName.equals("move")) {
			current = new XMove();
			current.setId(Integer.parseInt(atts.getValue("id")));
		}
		this.tagName = localName;
	}

	// 接收字符数据的通知。该方法用来处理在XML文件中读到的内容，第一个参数用于存放文件的内容，
	// 后面两个参数是读到的字符串在这个数组中的起始位置和长度，使用new String(ch,start,length)就可以获取内容。
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {

		if (tagName != null) {
			String data = new String(ch, start, length);
			if (tagName.equals("name")) {
				this.current.setName(data);
			} else if (tagName.equals("index")) {
				this.current.setIndex(Short.parseShort(data));
			} else if (tagName.equals("type")) {
				this.current.setType(data);
			} else if (tagName.equals("cate")) {
				this.current.setCate(data);
			} else if (tagName.equals("power")) {
				this.current.setPower(Short.parseShort(data));
			} else if (tagName.equals("accur")) {
				this.current.setAccur(Short.parseShort(data));
			} else if (tagName.equals("pp")) {
				this.current.setPP(Short.parseShort(data));
			} else if (tagName.equals("pri")) {
				this.current.setPri(Short.parseShort(data));
			} else if (tagName.equals("info")) {
				this.current.setInfo(data);
			}
		}
	}

	// 接收文档的结尾的通知。在遇到结束标签的时候，调用这个方法。其中，uri表示元素的命名空间；
	// localName表示元素的本地名称（不带前缀）；name表示元素的限定名（带前缀）
	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {

		if (localName.equals("move")) {
			moves.add(current);
			current = null;
		}
		this.tagName = null;
	}
}