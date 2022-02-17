Feature: To verify Landing Page in resume builder app

  @run
  Scenario: Redirecting to Build new resume
    Given User launch the valid URL
    When User clicks explore button
    Then User clicks build now
    And User clicks build resume
    Then Lands on the Pre-requisite questions page
