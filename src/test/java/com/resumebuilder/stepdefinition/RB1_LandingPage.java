package com.resumebuilder.stepdefinition;

import com.resumebuilder.baseclass.Baseclass;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RB1_LandingPage extends Baseclass {


	@Given("User launch the valid URL")
	public void user_launch_the_valid_url() throws Throwable {

		try {

			LaunchURLinChromeDriver("URL", App);

			waitForElement("ExploreNow", LandingPage);
			Thread.sleep(5000);
			if( getElement("ExploreNow", LandingPage).isDisplayed()) {

				System.out.println("Application Launched Successfully");
			} else {

				System.err.println("Application Launching Failed");
			}
		} 
		catch (Throwable e) {
			System.err.println(e.getMessage());
		}
	}



	@When("User clicks explore button")
	public void user_clicks_explore_button() throws Throwable {

		try {

			ClickElement("ExploreNow", LandingPage);

		} catch (Exception e) {

			System.err.println(e.getMessage());
		}


	}


	@Then("User clicks build now")
	public void user_clicks_build_now() throws Throwable {

		try {
			
			ClickElement("BuildNow", LandingPage);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}


	@And("User clicks build resume")
	public void user_clicks_build_resume() throws Throwable {

		Thread.sleep(5000);
		try {
			
			ClickElement("BuildResume", LandingPage);

		} catch (Exception e) {
			
			System.err.println(e.getMessage());
		}

	}


	@Then("Lands on the Pre-requisite questions page")
	public void lands_on_the_prerequisite_questions_page() throws Throwable {

		try {
			
			String ProgressBar = GetText("ProgressBar", LandingPage);

			if(ProgressBar.equalsIgnoreCase("1/3")) {

				System.out.println("Progress Bar Shows " +"'"+ProgressBar+ "'" + "User is in Pre-requisite Questions Page");

			}else {
				
				System.err.println("Not Pre-requisite Questions Page");


			}

		} catch (Throwable e) {
			
			System.err.println(e.getMessage());
		}
	}





}