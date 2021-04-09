package Producthunt;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CurrentVoteCountCrawl {
	public static void main(String args[]) throws IOException {

	File file = new File("C:\\Users\\dines\\Documents\\Vote Count Url.txt");
	Scanner scan;
	String productsite="null";
	
		scan = new Scanner(file);

		while (scan.hasNextLine()) {
			String url = "jdbc:mysql://localhost:3306/producthunt";
			String username = "root";
			String password = "dineshdd";
			
			try {
				productsite = scan.nextLine();
				Document doc = Jsoup.connect(productsite).get();
				java.util.Date Date=new java.util.Date();
				java.sql.Date date=new java.sql.Date(Date.getTime());
				Connection conn = DriverManager.getConnection(url, username, password);
				String sql;
				sql = "INSERT INTO vote (name,date,votecount) VALUES (?,?,?) ";
				PreparedStatement statement = conn.prepareStatement(sql);
				try {
			    String name = doc.select("h1.styles_font__2Nqit > a:nth-child(1)").text();
			    String 	votecount=doc.select(".styles_bigButtonCount__1DS7y").text();
				System.out.println(votecount);
             	statement.setString(1, name);
				statement.setDate(2, date);
				statement.setString(3, votecount);
				}catch (Exception e) {
				  statement.setString(3, null);
				}
				statement.executeUpdate();
			}catch (Exception e) {
				System.out.println(productsite);
			    }
		}	}

}
