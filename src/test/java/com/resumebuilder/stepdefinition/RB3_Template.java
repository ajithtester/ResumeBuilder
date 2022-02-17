package com.resumebuilder.stepdefinition;

import com.resumebuilder.baseclass.Baseclass;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RB3_Template extends Baseclass {
	
	@Given("User is in recommend tab")
	public void user_is_in_recommend_tab() {
	   
		try {
			String ProgressBar = GetText("ProgressBar", Template);
			
			if(ProgressBar.equalsIgnoreCase("2/3")) {

				System.out.println("Progress Bar Shows " +"'"+ProgressBar+ "'" + "User is in Recommended Template Page");

			}else {
				System.err.println("Not Recommended Template Page");


			}
		
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
	

	@When("Clicks all tab")
	public void clicks_all_tab() {
	   

		try {
			
			ClickElement("AllTab", Template);
			
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
	}

	@Then("User should be redirected to All tabs")
	public void user_should_be_redirected_to_All_tabs() {
	   
		try {
				waitForElement("Sort", Template);
				Thread.sleep(5000);
				if( getElement("Sort", Template).isDisplayed()) {
					
					System.out.println("In All Tab");
				} else {
					System.err.println("In recommended Tab");
				}
				
			} 
			catch (Throwable e) {
				
			}
		
	   
	}

	@And("User views all templates")
	public void user_views_all_templates() {
	   
		try {
			Thread.sleep(6000);
			ScrollDown();
			Thread.sleep(5000);
			ViewElement("AllTemplates", Template);
			
		} catch (Throwable e) {
			
		}
	}

}