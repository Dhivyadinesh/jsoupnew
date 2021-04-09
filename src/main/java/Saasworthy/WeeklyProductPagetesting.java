package Saasworthy;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class WeeklyProductPagetesting {
	public static void main(String args[]) throws IOException {
		File file = new File("C:\\Users\\dines\\Documents\\firsthalf.txt");
		Scanner scan;
		String productsite = "null";
		scan = new Scanner(file);

		while (scan.hasNextLine()) {
			// url stored in productUrl
			// connecting to productUrl by jsoup
			String url = "jdbc:mysql://localhost:3306/saasworthy";
			String username = "root";
			String password = "dineshdd";

			try {
				Connection conn = DriverManager.getConnection(url, username, password);
				String sql;
				productsite ="https://www.saasworthy.com"+scan.nextLine();
				// query for inserting data
				sql = "INSERT INTO weeklyproductpagetesting (productsite,Producttitlename,productName,logo,"
						+ "VistWebSite,Followers,SWScore,"
						+ "Description,Awards,Features,TechnicalDetails,categoryName,"
						+ "Pricing,PricingPlan,PricingVendorScreenshot,FAQ,"
						+ "Community,Related,Alternatives,Reviews,Videos,Screenshots,"
						+ "Articles,Downloads,Customers,Integrations,Slides)"
						+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
				PreparedStatement statement = conn.prepareStatement(sql);

				org.jsoup.nodes.Document docm = Jsoup.connect(productsite).get();
				Thread.sleep(1000);

				statement.setString(1, productsite);

				try {
					String Producttitlename = docm.getElementsByClass("h-title").text();
					boolean productName = docm.getElementsByClass("h-title").hasText();
					statement.setString(2, Producttitlename);
					statement.setBoolean(3, productName);
				} catch (Exception e) {
					statement.setString(2, null);
					statement.setBoolean(3, false);

					e.printStackTrace();
				}

				try {
					Elements productlogo = docm.getElementsByClass("pro-image-panel").select("img");
					boolean logo = productlogo.hasAttr("src");

					statement.setBoolean(4, logo);
				} catch (Exception e) {
					statement.setBoolean(4, false);
				}

				try {
					boolean VistWebSite = docm.getElementById("addfvrt").hasText();
 

					statement.setBoolean(5, VistWebSite);
				} catch (Exception e) {
					statement.setBoolean(5, false);
				}

				try {
					boolean Followers = docm.getElementsByClass("right_followers").hasText();
  

					statement.setBoolean(6, Followers);
				} catch (Exception e) {
					statement.setBoolean(6, false);
				}

				try {
					boolean SWScore = docm.getElementsByClass("pop_score_d").hasText();
  

					statement.setBoolean(7, SWScore);
				} catch (Exception e) {
					statement.setBoolean(7, false);
				}

				// checking Description is persent/not from productUrl
				try {
					boolean Description = docm.getElementById("sass-desc").hasText();
  

					statement.setBoolean(8, Description);
				} catch (Exception e) {
					statement.setBoolean(8, false);
				}

				// checking Awards is persent/not from productUrl
				try {
					boolean Awards = docm.getElementById("awards").hasText();
  

					statement.setBoolean(9, Awards);
				} catch (Exception e) {
					statement.setBoolean(9, false);
				}

				// checking Features is persent/not from productUrl
				try {
					boolean Features = docm.getElementById("features").hasText();
  

					statement.setBoolean(10, Features);
				} catch (Exception e) {
					statement.setBoolean(10, false);
				}

				// checking TechnicalDetails is persent/not from productUrl
				try {
					boolean TechnicalDetails = docm.getElementById("technical-details").hasText();
  

					statement.setBoolean(11, TechnicalDetails);
				} catch (Exception e) {
					statement.setBoolean(11, false);
				}

				try {
					boolean categoryName = docm.select(
							".tech-det-table > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(4) > span:nth-child(1) > a:nth-child(1)")
							.hasText();
  

					statement.setBoolean(12, categoryName);
				} catch (Exception e) {
					statement.setBoolean(12, false);
				}
				// checking Pricing is persent/not from productUrl
				try {
					boolean Pricing = docm.getElementById("pricing").hasText();
  

					statement.setBoolean(13, Pricing);
				} catch (Exception e) {
					statement.setBoolean(13, false);
				}

				try {
					boolean PricingPlan = docm.select(".pricing-slider > div:nth-child(1) > div").hasText();
  

					statement.setBoolean(14, PricingPlan);
				} catch (Exception e) {
					statement.setBoolean(14, false);
				}

				try {
					Elements pricingvendor = docm.getElementsByClass("scrn-shot-div").select("img");
					boolean PricingVendorScreenshot = pricingvendor.hasAttr("src");
  

					statement.setBoolean(15, PricingVendorScreenshot);
				} catch (Exception e) {
					statement.setBoolean(15, false);
				}
				// checking FAQ is persent/not from productUrl
				try {
					boolean FAQ = docm.getElementById("faq").hasText();
  

					statement.setBoolean(16, FAQ);
				} catch (Exception e) {
					statement.setBoolean(16, false);
				}

				try {
					boolean Community = docm.getElementById("community").hasText();
  

					statement.setBoolean(17, Community);
				} catch (Exception e) {
					statement.setBoolean(17, false);
				}

				try {
					boolean Related = docm.getElementById("related").hasText();
  

					statement.setBoolean(18, Related);
				} catch (Exception e) {
					statement.setBoolean(18, false);
				}

				// checking Alternatives is persent/not from productUrl
				try {
					boolean Alternatives = docm.getElementById("alternatives").hasText();
  

					statement.setBoolean(19, Alternatives);
				} catch (Exception e) {
					statement.setBoolean(19, false);
				}

				// checking Reviews is persent/not from productUrl
				try {
					boolean Reviews = docm.getElementById("reviews").hasText();
  

					statement.setBoolean(20, Reviews);
				} catch (Exception e) {
					statement.setBoolean(20, false);
				}

				// checking Videos is persent/not from productUrl
				try {
					boolean Videos = docm.getElementById("videos").hasText();
  

					statement.setBoolean(21, Videos);
				} catch (Exception e) {
					statement.setBoolean(21, false);
				}

				// checking Screenshots is persent/not from productUrl
				try {
					boolean Screenshots = docm.getElementById("screenshots").hasText();
					statement.setBoolean(22, Screenshots);
  

				} catch (Exception e) {
					statement.setBoolean(22, false);
				}

				// checking Articles is persent/not from productUrl
				try {
					boolean Articles = docm.getElementById("articles").hasText();
  

					statement.setBoolean(23, Articles);
				} catch (Exception e) {
					statement.setBoolean(23, false);
				}

				// checking Awards is persent/not from productUrl
				try {
					boolean Downloads = docm.getElementById("downloads").hasText();
  

					statement.setBoolean(24, Downloads);
				} catch (Exception e) {

					statement.setBoolean(24, false);
				}

				try {
					boolean Customers = docm.getElementById("customers").hasText();
  

					statement.setBoolean(25, Customers);
				} catch (Exception e) {
					statement.setBoolean(25, false);

				}

				try {
					boolean Integrations = docm.getElementById("integration").hasText();
  

					statement.setBoolean(26, Integrations);

				} catch (Exception e) {
					statement.setBoolean(26, false);

				}
				try {
					boolean Slides = docm.getElementById("slides").hasText();
  

					statement.setBoolean(27, Slides);

				} catch (Exception e) {
					statement.setBoolean(27, false);

				}
				statement.executeUpdate();
			} catch (Exception e) {
				System.out.println(productsite);
			}
		}

	}
}
