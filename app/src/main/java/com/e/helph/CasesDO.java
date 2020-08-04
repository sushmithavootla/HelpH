package com.e.helph;

import java.io.Serializable;

public class CasesDO implements Serializable {
    public String name = "";
    public String code = "";

    public  int newConfirmed = 0;
    public  int totalConfirmed = 0;
    public int newDeaths = 0;
    public int totalDeaths =0;
    public int newRecovered = 0;
    public int totalRecovered = 0;


}
