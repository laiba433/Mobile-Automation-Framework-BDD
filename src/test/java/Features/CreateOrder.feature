Scenario: Test Flat Position Scenario
And Wait for 4000
And Click Button "tradeScreenButton"
Given Create "Simple" "Buy" side Order of Symbol "MA"
|Key              | Value             | ControlType|
|QuantityField    | 100               | TextField  |
|Accounts          | usama165 account 1| ComboBox   |