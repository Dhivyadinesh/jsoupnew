package Capterra;

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

public class ProductAdditionCapterra {
	public static void main(String[] args) throws FileNotFoundException, SQLException {
		File file = new File("C:\\Users\\dines\\Documents\\CapterraUrl.txt");
		Scanner scan;
		String productsite = "null";

		scan = new Scanner(file);

		while (scan.hasNextLine()) {
			String url = "jdbc:mysql://localhost:3306/capterra";
			String username = "root";
			String password = "dineshdd";

			try {
				Connection conn = DriverManager.getConnection(url, username, password);
				String sql;
				// query for inserting data
				sql = "INSERT INTO 	productaddcapterra (productsite,ProductName,ProductLogoUrl,"
						+ "MainCategory,PricingModels,deploymentdata,supportdata)" + " VALUES (?,?,?,?,?,"
						+ "?,?) ";
				PreparedStatement statement = conn.prepareStatement(sql);
				productsite = scan.nextLine();
				Document doc = Jsoup.connect(productsite).get();
				Thread.sleep(1000);

				statement.setString(1, productsite);

				try {
					String ProductName = doc.getElementsByClass(
							"ProductHeader__ProductHeading-sc-10fs9um-3 dbAnwA Heading-p3hmo4-0 cftCto").text();
					statement.setString(2, ProductName);
				} catch (Exception e) {
					statement.setString(2, null);

				}

				try {
					Elements logolink = doc.select(
							"#root > div > div.gtm-page-content > div > div.gtm-desktop-product-header > div > div > div > div > div > div.sc-bwzfXH.gsrdfM > span > div > div > img");
					String ProductLogoUrl = logolink.attr("src");
					statement.setString(3, ProductLogoUrl);
				} catch (Exception e) {
					statement.setString(3, null);

				}
				try {
					String MainCategory = doc.select(
							"#root > div > div.gtm-main-navigation > div.BreadCrumbs__Container-sc-93wirt-0.kvPUzW > div > div > div > div:nth-child(2) > a > div")
							.text();
					statement.setString(4, MainCategory);
				} catch (Exception e) {
					statement.setString(4, null);

				}
				try {
					Elements Pricingmain = doc.select(
							"#LoadableProductPricingSection > div > div > div > div > div.StyledComponents__BlockLeft-wzvnxk-10.dJytYn > div.StyledComponents__BlockChecklist-wzvnxk-12.beXFFr > ul > li:nth-child(n) > div > span");
					for (Element Pricingsub : Pricingmain) {
						Elements Pricingdata = Pricingsub.getElementsByClass("ListItem__Text-sc-1ejeqo-1 fAHCTA");
						String Pricing = Pricingdata.text();
						String Removeword = "Yes, has ";
						String PricingModels = Pricing.replaceAll(Removeword, "");
						statement.setString(5, PricingModels);
					}
				} catch (Exception e) {
					statement.setString(5, null);

				}

				try {
					Elements deploymentmain = doc.select(
							"#LoadableProductSummary > div > div.DeploymentSupportSection__Root-z7uxeo-0.dolXgi > div > div:nth-child(1) > ul > li:nth-child(n) > div>span");
					for (Element deploymentsub : deploymentmain) {
						Elements deployment = deploymentsub.getElementsByClass("ListItem__Text-sc-1ejeqo-1 fAHCTA");
						String deploymentdata = deployment.text();
						statement.setString(6, deploymentdata);
					}
				} catch (Exception e) {
					statement.setString(6, null);

				}
				try {
				
					Elements supportmain = doc.select(
							"#LoadableProductSummary > div > div.DeploymentSupportSection__Root-z7uxeo-0.dolXgi > div > div:nth-child(2) > ul > li:nth-child(n) > div > span");
					for (Element supportsub : supportmain) {
						Elements support = supportsub.getElementsByClass("ListItem__Text-sc-1ejeqo-1 fAHCTA");
						String supportdata = support.text();
						statement.setString(7, supportdata);
					}
				} catch (Exception e) {
					statement.setString(7, null);
				}
				statement.executeUpdate();
			} catch (Exception e) {
				System.out.println(productsite);
			}
		}
	}

}
