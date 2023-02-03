Feature: Test
  @reg
  Scenario Outline: Test
  #  And Wait for 10000
   # Given Testing
   # And Add Symbol
   And Click Button "PositionTab"
   And Get index of position of Symbol "MA", Position "Long" and Account ""
   # And Wait for 5000
    And Click Button "TradeTab"
    And Click Button "AdvanceButton"
    Given Select ComboBoxValue "OrderType" "STOP LMT"
   # Given Add Multiple Symbols of this file "src/test/resources/Symbols.txt" and verify Company Info
    Given Fill TextBox "SymbolField" "MA"
    And Click Button "AddSymbolIcon"
    Then Validate Bid, Ask, and Last Price
   # And Log Message "Info" "Symbol Added Successfully"
    #Given Fill TextBox "QuantityField" "<Quantity>"
 #  And Click Button "SendOrderButton"
   # And Wait for 5000
    #And Click Button "ConfirmOrderYes"
   # Then Action on Order Successfully Created pop up "Ok"
   # And Click Button "AddWatchListButton"
  #  Given Fill TextBox "AddWatchListAlerttextfield" "Logiciel"
   # Then Accept Alert
  #And Click Button "watchlistPopCreateButton" Android Script
  #  Then Check the Presence of "WatchlistName"
    Examples:
      | Symbol | Quantity | Account | Confirmation | Action |
      | MA     | 1000     | 1LOC-LOCA-123456-M | Yes          | OK     |


  Scenario:TC0012 - Enter the correct Email and Password and tap on Login.
    Given Fill TextBox "Username" ""
    And Click Button "LoginButton"
    And Validate Toast Message: "The Email field is required."