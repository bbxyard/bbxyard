package com.bbxyard;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.soap.Text;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class Html2Text {
	public static void main(String[] args) throws IOException {
		if (args.length < 1)
		{
			System.out.println("usage: Html2Text <file>");
			System.exit(1);
		}
		//String htmlfile = "/tmp/input.html";
		String htmlfile = args[0];
		File input = new File(htmlfile);
		Document doc = Jsoup.parse(input, "UTF-8");	
		System.out.println(doc.text().toString());
		
		
		//Element body = doc.getElementsByTag("body").first();
		//visit(body);
	}
}
