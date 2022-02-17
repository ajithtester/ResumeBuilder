Feature: To verify Template Section in resume builder app

  Scenario: View template in Recommend Tab and All Tab
    Given User is in recommend tab
    When Clicks all tab
    Then User should be redirected to All tabs
    And User views all templates
