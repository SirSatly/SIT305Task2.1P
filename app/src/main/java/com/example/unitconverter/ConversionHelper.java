package com.example.unitconverter;

public class ConversionHelper
{
    public static double Convert(double value, String input, String output)
    {
        if (input.equals("Fahrenheit")) value = (value - 32) / 1.8;
        else if (input.equals("Kelvin")) value -= 273.15;
        else value *= GetRatioToSI(input);

        if (output.equals("Fahrenheit")) value = (value * 1.8) + 32;
        else if (output.equals("Kelvin")) value += 273.15;
        else value /= GetRatioToSI(output);

        return value;
    }

    public static double GetRatioToSI(String unit)
    {
        switch(unit)
        {
            case "Grams": case "Millimeters":
                return 0.001;
            case "Centimeters":
                return 0.01;
            case "Kilometers": case "Tonnes":
                return 1000;
            case "Inches":
                return 0.0254;
            case "Feet":
                return 0.3048;
            case "Yards":
                return 0.9144;
            case "Miles":
                return 1609.34;
            case "Ounces":
                return 0.0283495;
            case "Pounds":
                return 0.453592;
            case "Tons":
                return 907.185;
            default:
                return 1;
        }
    }
}
