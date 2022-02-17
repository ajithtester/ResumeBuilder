package com.resumebuilder.stepdefinition;

import com.resumebuilder.baseclass.Baseclass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RB4_SelectTemplate extends Baseclass {

	@Given("User is in All Tab")
	public void user_is_in_All_Tab() {
	   
		try {
			waitForElement("Sort", SelectTemplate);
			Thread.sleep(5000);
			if( getElement("Sort", SelectTemplate).isDisplayed()) {
				
				System.out.println("In All Tab");
			} else {
				System.err.println("In recommended Tab");
			}
			
		} 
		catch (Throwable e) {
			
		}
	}

	

	@When("User choose a tempate")
	public void user_choose_a_tempate() {
	
		try {
			Thread.sleep(5000);
			ChooseTemplate("AllTemplates", "UseTemplate", SelectTemplate, ReadDataFrom(1, 0, "ChooseTemplate"));
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	@Then("User should land on the Build resume - Options Page")
	public void user_should_land_on_the_Build_resume_Options_Page() {
		
		try {
			String ProgressBar = GetText("ProgressBar", SelectTemplate);
			
			if(ProgressBar.equalsIgnoreCase("3/3")) {

				System.out.println("Progress Bar Shows " +"'"+ProgressBar+ "'" + "User is in Build resume - Options Page");

			}else {
				System.err.println("Not Build resume - Options Page");


			}
		
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
