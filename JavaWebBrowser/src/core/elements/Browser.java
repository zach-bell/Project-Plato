package core.elements;

import core.JavaBrowserLauncher;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import tools.CP;
import tools.Scraper;

public class Browser extends Region {

	public final WebView browser = new WebView();
	public BrowserUI browserUI;
	public String outerHTML;
	public Scraper scrape = new Scraper();
	
	private final WebEngine webEngine = browser.getEngine();

	public Browser(JavaBrowserLauncher launcher) {
		// apply the styles
		getStyleClass().add("browser");
		// load the web page
		webEngine.load(launcher.currentURL);
		// add the web view to the scene
		getChildren().add(browser);
		
		browserUI = new BrowserUI(launcher);
	}
	
	public void setWebPage(String url) {
		webEngine.load(url);
	}
	
	public void updateOuterHtml() {
		outerHTML = getHtml();
	}
	
	public String getHtml() {
		try
		{
			return (String) webEngine.executeScript("document.documentElement.outerHTML");
		}
		catch(Exception ex)
		{
			CP.println("Error: " + ex.getMessage());
			return "lemme get uhhh, outer html pls?";
		}
	}

	public Node createSpacer() {
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		return spacer;
	}

	protected void layoutChildren() {
		double w = getWidth();
		double h = getHeight();
		layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
	}

	protected double computePrefWidth(double height) {
		return 1040;
	}

	protected double computePrefHeight(double width) {
		return 720;
	}
}
