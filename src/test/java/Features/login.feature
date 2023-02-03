Feature: Login
@reg
  Scenario Outline: Login vBate
    And Click Button "LoginBtnSplash"
    And Wait for 5000
  Given Fill Values in Multiple Text Boxes as following table
    | Key      | Value                   |
    | Username | <UsernameValue>|
    | Password | <PasswordValue>|
    And Click Button "LoginButton"
    And Wait for 5000
    When I add Verification code"<VerificationCode>"
    And Click Button "VerifyButton"

    Examples:
      | UsernameValue                | PasswordValue  | VerificationCode |
      | usama165@mailinator.com | usama165 | 123456           |

