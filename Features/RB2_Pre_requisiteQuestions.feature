Feature: To verify Pre-requisite questions on resume builder app

  Scenario: Answering the Pre-requisite questions
    Given User is in the Pre-requisite questions page
    And User answer the first question
    And User answer the second question
    And User answer the third question
    And User answer the fourth question
    And User clicks edit button
    Then User changes the answer
    And User clicks the see your template
    Then User should land on template page
