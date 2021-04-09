package Producthunt;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class OfficalWebsiteCrawl {
	public static void main(String[] args) throws FileNotFoundException, SQLException {
		File file = new File("C:\\Users\\dines\\Documents\\RedirectUrlFromProducthuntsite.txt");
		Scanner scan;
		String productsite="null",visitwebsite;
		
			scan = new Scanner(file);

			while (scan.hasNextLine()) {
				try {
					productsite = scan.nextLine();
					Document doc = Jsoup.connect(productsite).get();
					visitwebsite = doc.location();
					System.out.println(visitwebsite);
				    String url = "jdbc:mysql://localhost:3306/producthunt";
					String username = "root";
					String password = "dineshdd";
					Connection conn = DriverManager.getConnection(url, username, password);
					String sql;
					sql = "INSERT INTO visit (visitwebsite,productsite) VALUES (?,?) ";
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.setString(1, visitwebsite);
					statement.setString(2,productsite);
				}catch (Exception e) {
					System.out.println(productsite);
				    }
			}	}

	}
