package com.teraninja.guard.Model;

public class DataPriceCompany {
    public String tax_rate;
    public String count;
    public double price_for_cv;
    public double price_without_tax;
    public double tax_value;
    public double price_with_tax;

    public String getTax_rate() {
        return tax_rate;
    }

    public void setTax_rate(String tax_rate) {
        this.tax_rate = tax_rate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public double getPrice_for_cv() {
        return price_for_cv;
    }

    public void setPrice_for_cv(double price_for_cv) {
        this.price_for_cv = price_for_cv;
    }

    public double getPrice_without_tax() {
        return price_without_tax;
    }

    public void setPrice_without_tax(double price_without_tax) {
        this.price_without_tax = price_without_tax;
    }

    public double getTax_value() {
        return tax_value;
    }

    public void setTax_value(double tax_value) {
        this.tax_value = tax_value;
    }

    public double getPrice_with_tax() {
        return price_with_tax;
    }

    public void setPrice_with_tax(double price_with_tax) {
        this.price_with_tax = price_with_tax;
    }
}