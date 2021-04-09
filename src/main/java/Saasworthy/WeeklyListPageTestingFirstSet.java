package Saasworthy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WeeklyListPageTestingFirstSet {

	public static void main(String[] args)
			throws IOException, ParserConfigurationException, SAXException, SQLException, InterruptedException {
		String url = "jdbc:mysql://localhost:3306/saasworthy";
		String username = "root";
		String password = "dineshdd";
		String listpageurl = "null";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db
				.parse(new URL("https://www.saasworthy.com/sitemaps/category_sitemap_google.xml").openStream());
		NodeList nodeList = doc.getElementsByTagName("url");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			Element eElement = (Element) node;
			Connection conn = DriverManager.getConnection(url, username, password);
			String sql;
			// query for inserting data
			sql = "INSERT INTO weeklylistpagefirst (listpageurl,Description,productCount,updatedate,Lefttopics,Subscribe,pagination)"
					+ " VALUES (?,?,?,?,?,?,?) ";
			// Create a statement
			PreparedStatement statement = conn.prepareStatement(sql);

			try {
				listpageurl = eElement.getElementsByTagName("loc").item(0).getTextContent();

				org.jsoup.nodes.Document docm = Jsoup.connect(listpageurl).get();
				statement.setString(1, listpageurl);

				// checking Integrations is persent/not from productUrl
				try {
					Boolean Description = docm.select(".list_head_desc > p:nth-child(1)").hasText();
					statement.setBoolean(2, Description);

				} catch (Exception e) {
					statement.setBoolean(2, false);

				}
				try {
					Boolean productCount = docm.select(".prdct_nmbr").hasText();

					statement.setBoolean(3, productCount);
				} catch (Exception e) {
					statement.setBoolean(3, false);

				}
				try {
					Boolean updatedate = docm.select(".updtd_date").hasText();
					statement.setBoolean(4, updatedate);

				} catch (Exception e) {
					statement.setBoolean(4, false);

				}
				try {
					Boolean Lefttopics = docm.select(".glnce-list").hasText();
					statement.setBoolean(5, Lefttopics);

				} catch (Exception e) {
					statement.setBoolean(5, false);

				}
				try {
					Boolean Subscribe = docm.getElementById("subscribeUpdateDiv").hasText();

					statement.setBoolean(6, Subscribe);
				} catch (Exception e) {
					statement.setBoolean(6, false);

				}

				try {
					Boolean pagination = docm.select(".pagination").hasText();
					statement.setBoolean(7, pagination);

				} catch (Exception e) {
					statement.setBoolean(7, false);

				}

				Thread.sleep(1000);

				// establish connection with the Mysql database

				statement.executeUpdate();

				// execute statement
				conn.close();
			} catch (Exception e) {
				System.out.println(listpageurl);
			}
		}
	}
}
