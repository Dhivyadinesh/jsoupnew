package Producthunt;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.html.HTMLAnchorElement;

public class IndividualSoftwaredatacrawl {
	public static void main(String args[]) throws IOException, SQLException {
		File file = new File("C:\\Users\\dines\\Documents\\Producthunt Today URL.txt");
		Scanner scan;
		String ProductName = "null",productUrl="null",VoteCount="null",tag1="null",tag2="null";
		scan = new Scanner(file);
		while (scan.hasNextLine()) {
			try {
				productUrl = scan.nextLine();
				Document doc = Jsoup.connect(productUrl).get();
				Thread.sleep(1000);
				ProductName = doc.select("h1.styles_font__2Nqit > a:nth-child(1)").text();
			    VoteCount=doc.select(".styles_bigButtonCount__1DS7y").text();
			    tag1=doc.select("div.styles_item__13V32:nth-child(1) > a:nth-child(1)").text();
				tag2=doc.select("div.styles_item__13V32:nth-child(2) > a:nth-child(1)").text();
				java.util.Date Date=new java.util.Date();
				java.sql.Date date=new java.sql.Date(Date.getTime());
				Elements link = doc.select(".styles_asideGetItButton__1hGKL");
				String vist = link.attr("href");
				System.out.println("https://www.producthunt.com"+vist);
                
				String url = "jdbc:mysql://localhost:3306/producthunt";
				String username = "root";
				String password = "dineshdd";
				Connection conn = DriverManager.getConnection(url, username, password);
				String sql;
				sql = "INSERT INTO IndividualDetails(productUrl,ProductName,date,VoteCount,tag1,tag2) VALUES (?,?,?,?,?,?) ";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, productUrl);
				statement.setString(2, ProductName);
				statement.setDate(3, date);
				statement.setString(4, VoteCount);
				statement.setString(5, tag1);
				statement.setString(6, tag2);
				statement.execute();

				}catch (Exception e) {
System.out.println(productUrl);			
}
	}
  }
}