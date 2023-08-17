package com.CreditCardManagement.CreditCardManagement.dto;

public class LowHigh {
    private String lowHigh;
    private double transTotal;

    public LowHigh() {
    }

    public String getLowHigh() {
        return lowHigh;
    }

    public void setLowHigh(String lowHigh) {
        this.lowHigh = lowHigh;
    }

    public double getTransTotal() {
        return transTotal;
    }

    public void setTransTotal(double transTotal) {
        this.transTotal = transTotal;
    }
}
