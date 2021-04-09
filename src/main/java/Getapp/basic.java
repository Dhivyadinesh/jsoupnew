package Getapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class basic {
	public static void main(String[] args) throws IOException, InterruptedException {

				Document doc = Jsoup.connect("https://www.getapp.com/it-management-software/a/windows-azure-platform/")
						 .userAgent("Chrome")
                         .header("Accept", "text/html")
                         .header("Accept-Encoding", "gzip,deflate")
                         .header("Accept-Language", "it-IT,en;q=0.8,en-US;q=0.6,de;q=0.4,it;q=0.2,es;q=0.2")
                         .header("Connection", "keep-alive")
                         .ignoreContentType(true)
                         .get();				Thread.sleep(1000);			
				try {
					String ProductName = doc.select("#apps > div > div > div:nth-child(2) > div.listing-box.cd-scope > div > div.col-lg-8.col-md-8.col-sm-8.col-xs-12 > div.col-lg-10.col-md-10.col-sm-10.col-xs-9.listing-overview > h2").text();
					System.out.println(ProductName);
				} catch (Exception e) {
				}
				
				try {
					Elements logolink = doc.select("#apps > div > div > div:nth-child(2) > div.listing-box.cd-scope > div > div.col-lg-8.col-md-8.col-sm-8.col-xs-12 > div.col-lg-2.col-md-2.col-sm-2.col-xs-3.listing-logo > img");
					String ProductLogoUrl = logolink.attr("data-src");
					System.out.println(ProductLogoUrl);
				} catch (Exception e) {
				}
				
				try {
					String mainCategory = doc.select("#secondary-header > div > div.navigation-row.hscroll > ul > li:nth-child(2) > a > span").text();
					System.out.println(mainCategory);
				} catch (Exception e) {
				}
				
				try {
					String IncludedInSwScore = doc.select("#apps > div > div > div:nth-child(2) > div.listing-box.cd-scope > div > div.col-lg-8.col-md-8.col-sm-8.col-xs-12 > div.col-lg-10.col-md-10.col-sm-10.col-xs-9.listing-overview > span.text-muted > span > span:nth-child(1)").text();
					System.out.println(IncludedInSwScore);
				} catch (Exception e) {
				}
			
				try {
					String Removeword = doc.select("#apps > div > div > div:nth-child(2) > div.body-container.listing-overview > div > div.col-lg-8.col-md-8 > div.row > div:nth-child(1) > div:nth-child(2)").text();
					Elements Pricing = doc.select("#apps > div > div > div:nth-child(2) > div:nth-child(21) > div > div.col-lg-5 > div:nth-child(n)");
					String Pricingplan = Pricing.text();
					String PricingModels = Pricingplan.replaceAll(Removeword, "");
					System.out.println(PricingModels);
				} catch (Exception e) {
				}
				try {
					Elements Pricinglink = doc.select("#apps > div > div > div:nth-child(2) > div:nth-child(21) > div > div.col-lg-5 > a");
					String pricingPageLink = Pricinglink.attr("href");
					System.out.println(pricingPageLink);
				} catch (Exception e) {
				}
				try {
					Elements device = doc.select("#apps > div > div > div:nth-child(2) > div.body-container.listing-overview > div > div > div.row > div:nth-child(2) > div:nth-child(1) > div > div:nth-child(2) > div > div.device.active");
					String pricingPageLink = device.attr("title");
					System.out.println(pricingPageLink);
				} catch (Exception e) {
				}
				try {
					String CustomerType = doc.getElementsByClass("business-size active").text();
					System.out.println(CustomerType);
				} catch (Exception e) {
				}
				try {
					String Removeword = doc.select("#apps > div > div > div:nth-child(2) > div.body-container.listing-overview > div > div.col-lg-8.col-md-8 > div.row > div:nth-child(2) > div:nth-child(4) > span.collapsed_list > a").text();
					String market = doc.select("#apps > div > div > div:nth-child(2) > div.body-container.listing-overview > div > div.col-lg-8.col-md-8 > div.row > div:nth-child(2) > div:nth-child(4)").text();
					String markets = market.replaceAll(Removeword, "");
					System.out.println(markets);
				} catch (Exception e) {
				}
				try {
					String Removeword = doc.select("#apps > div > div > div:nth-child(2) > div.body-container.listing-overview > div > div.col-lg-8.col-md-8 > div.row > div:nth-child(2) > div:nth-child(6) > span.collapsed_list > a").text();
					String Languages = doc.select("#apps > div > div > div:nth-child(2) > div.body-container.listing-overview > div > div.col-lg-8.col-md-8 > div.row > div:nth-child(2) > div:nth-child(6)").text();
					String SupportedLanguages = Languages.replaceAll(Removeword, "");
					System.out.println(SupportedLanguages);
				} catch (Exception e) {
				}
			
		}

	}


