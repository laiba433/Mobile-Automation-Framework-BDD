Feature: Locate
  @reg
  Scenario: Locate
    And Wait for 5000
    And Click Button "LocateTab"
    And Wait for 5000
   #Given Fill TextBox "SymbolField" "IBM"
  # And Click Button "AddSymbolIcon"
  #And Log Message "Info" "Symbol Added Successfully"
  # Given Fill TextBox "QuantityField" "2,000"
 # And Click Button "RequestQuoteButton"
 Then Validate following values of Locate Watch at index 0
    | Key                   | Value |
    | LocateSymbol   | IBM   |
    | LocateQuantity | 2,000 |