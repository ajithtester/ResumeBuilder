package com.resumebuilder.stepdefinition;

import com.resumebuilder.baseclass.Baseclass;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class RB2_Pre_requisiteQuestions extends Baseclass {



	@Given("User is in the Pre-requisite questions page")
	public void lands_on_the_Pre_requisite_questions_page() {


		try {

			waitForElement("ProgressBar", PreRequisite_Questions);

		} 
		catch (Throwable e) {

			System.err.println(e.getMessage());
		}

	}


	@And("User answer the first question")
	public void user_answer_the_first_question() {

		try {

			//	waitForElement("FirstQuestion", PreRequisite_Questions);
			Thread.sleep(3000);	
			Clickelement("FirstQuestion", PreRequisite_Questions, ReadDataFrom(1, 1, "PreRequisties_Answer"));
			Thread.sleep(3000);	
			String SelectedAnswer	= GetText("SelectedAnswer", PreRequisite_Questions);

			if(SelectedAnswer.equalsIgnoreCase(ReadDataFrom(1, 1, "PreRequisties_Answer"))) {

				System.out.println("Answer1 is correct");

			}else {
				System.out.println("Answer1 is not correct");


			}

		} catch (Throwable e) {

			System.err.println(e.getMessage());
		}
	}

	@And("User answer the second question")
	public void user_answer_the_second_question() {


		try {
			TypeDataInTheElement("SecondQuestion", PreRequisite_Questions, "india");
			Thread.sleep(5000);	

			Clickelement("List", PreRequisite_Questions, ReadDataFrom(2, 1, "PreRequisties_Answer") );

			String answer2	= GetText("SelectedAnswer", PreRequisite_Questions);
			if(answer2.equalsIgnoreCase(ReadDataFrom(2, 1, "PreRequisties_Answer"))) {

				System.out.println("Answer2 is correct");

			}else {
				System.out.println("Answer2 is not correct");


			}

		} catch (Throwable e) {
			System.err.println(e.getMessage());
		}

	}

	@And("User answer the third question")
	public void user_answer_the_third_question() {

		try {

			TypeDataInTheElement("ThirdQuestion", PreRequisite_Questions, "tester");
			Thread.sleep(5000);	

			Clickelement("List", PreRequisite_Questions, ReadDataFrom(3, 1, "PreRequisties_Answer") );

			String answer3	= GetText("SelectedAnswer", PreRequisite_Questions);
			if(answer3.equalsIgnoreCase(ReadDataFrom(3, 1, "PreRequisties_Answer"))) {

				System.out.println("Answer3 is correct");

			}else {
				System.out.println("Answer3 is not correct");


			}

		} catch (Throwable e) {
			System.err.println(e.getMessage());
		}

	}

	@And("User answer the fourth question")
	public void user_answer_the_fourth_question() {

		try {


			TypeDataInTheElement("FourthQuestion", PreRequisite_Questions, ReadDataFrom(4, 1, "PreRequisties_Answer"));
			Thread.sleep(5000);	

			ClickElement("SelectedAnswer", PreRequisite_Questions);

			String answer4	= GetText("FourthQuestion", PreRequisite_Questions);
			System.out.println(answer4);
			if(answer4.equalsIgnoreCase(ReadDataFrom(4, 1, "PreRequisties_Answer"))) {

				System.out.println("Answer4 is correct");

			}else {
				System.out.println("Answer4 is not correct");


			}

		} catch (Throwable e) {
			System.err.println(e.getMessage());
		}

	}


	@And("User clicks edit button")
	public void user_clicks_edit_button() {

		try {

			ClickElement("EditAnswer", PreRequisite_Questions);
			Thread.sleep(3000);
			Clear("ThirdQuestion", PreRequisite_Questions);

		} catch (Throwable e) {

		}


	}

	@Then("User changes the answer")
	public void user_changes_the_answer() {



		try {

			TypeDataInTheElement("ThirdQuestion", PreRequisite_Questions, ReadDataFrom(3, 2, "PreRequisties_Answer"));
			Thread.sleep(5000);	

			Clickelement("List", PreRequisite_Questions, ReadDataFrom(3, 2, "PreRequisties_Answer") );

			String answer3	= GetText("SelectedAnswer", PreRequisite_Questions);
			if(answer3.equalsIgnoreCase(ReadDataFrom(3, 2, "PreRequisties_Answer"))) {

				System.out.println("Edited answer updated");

			}else {
				System.out.println("Edited answer not updated");


			}

		} catch (Throwable e) {
			System.err.println(e.getMessage());
		}

	}


	@And("User clicks the see your template")
	public void user_clicks_the_see_your_template() {

		try {
			Thread.sleep(3000);
			ClickElement("SeeYourTemplate", PreRequisite_Questions);

		} catch (Throwable e) {

			System.err.println(e.getMessage());
		}
	}

	@Then("User should land on template page")
	public void user_should_lands_on_template_page() {

		try {
			waitForElement("Sort", PreRequisite_Questions);
			Thread.sleep(5000);
			if( getElement("Sort", PreRequisite_Questions).isDisplayed()) {

				System.out.println("failed landed in All tabs");
			} else {

				System.out.println("Landed in Recommended tabs");
			}

		} 
		catch (Throwable e) {
			//System.err.println(e.getMessage());
		}
	}


}
