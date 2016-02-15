package com.yurset.wiipm.logi;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.yurset.wiipm.base.XPokemon;

public class XPMHandler extends DefaultHandler {
	private XPokemon current;
	private String tagName = null;// 当前解析的元素标签
	private List<XPokemon> pokemons = null;

	public List<XPokemon> getPokemons() {
		return pokemons;
	}

	// 接收文档开始的通知。当遇到文档的开头的时候，调用这个方法，可以在其中做一些预处理的工作。
	@Override
	public void startDocument() throws SAXException {
		pokemons = new ArrayList<XPokemon>();
	}

	// 接收元素开始的通知。当读到一个开始标签的时候，会触发这个方法。其中namespaceURI表示元素的命名空间；
	// localName表示元素的本地名称（不带前缀）；qName表示元素的限定名（带前缀）；atts 表示元素的属性集合
	@Override
	public void startElement(String namespaceURI, String localName,
							 String qName, Attributes atts) throws SAXException {

		if (localName.equals("pokemon")) {
			current = new XPokemon();
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
			} else if (tagName.equals("level")) {
				this.current.setLevel(Short.parseShort(data));
			} else if (tagName.equals("item")) {
				this.current.setItem(data);
			} else if (tagName.equals("kidney")) {
				this.current.setKidney(Short.parseShort(data));
			} else if (tagName.equals("height")) {
				this.current.setHeight(Float.parseFloat(data));
			} else if (tagName.equals("weight")) {
				this.current.setWeight(Float.parseFloat(data));
			} else if (tagName.equals("type1")) {
				this.current.setType1(data);
			} else if (tagName.equals("type2")) {
				this.current.setType2(data);
			} else if (tagName.equals("eggcate1")) {
				this.current.setEggcate1(data);
			} else if (tagName.equals("eggcate2")) {
				this.current.setEggcate2(data);
			} else if (tagName.equals("ability1")) {
				this.current.setAbility1(data);
			} else if (tagName.equals("ability2")) {
				this.current.setAbility2(data);
			} else if (tagName.equals("ability3")) {
				this.current.setAbility3(data);
			} else if (tagName.equals("hp")) {
				this.current.setHP(Short.parseShort(data));
			} else if (tagName.equals("wg")) {
				this.current.setWG(Short.parseShort(data));
			} else if (tagName.equals("wf")) {
				this.current.setWF(Short.parseShort(data));
			} else if (tagName.equals("sd")) {
				this.current.setSD(Short.parseShort(data));
			} else if (tagName.equals("tg")) {
				this.current.setTG(Short.parseShort(data));
			} else if (tagName.equals("tf")) {
				this.current.setTF(Short.parseShort(data));
			} else if (tagName.equals("move1")) {
				this.current.setMove1(Short.parseShort(data));
			} else if (tagName.equals("move2")) {
				this.current.setMove2(Short.parseShort(data));
			} else if (tagName.equals("move3")) {
				this.current.setMove3(Short.parseShort(data));
			} else if (tagName.equals("move4")) {
				this.current.setMove4(Short.parseShort(data));
			}
		}
	}

	// 接收文档的结尾的通知。在遇到结束标签的时候，调用这个方法。其中，uri表示元素的命名空间；
	// localName表示元素的本地名称（不带前缀）；name表示元素的限定名（带前缀）
	@Override
	public void endElement(String uri, String localName, String name)
			throws SAXException {

		if (localName.equals("pokemon")) {
			pokemons.add(current);
			current = null;
		}
		this.tagName = null;
	}
}