package com.appium.Main.Interface;

import io.cucumber.datatable.DataTable;

public interface IList {
    public int GetGridIndexValueOfPosition(String Symbol,String Position, String AccountValue);
    public void ValidatePositionValues(String ListKey, String Symbol, String AccountValue, DataTable table);
    public void ValidateListValues (String orderList,int index, DataTable table);
    public void ClickOnPositionMenuIcon(String Symbol,String Position, String AccountValue);
    public void SelectPositionMenuOption(String Option);
}
