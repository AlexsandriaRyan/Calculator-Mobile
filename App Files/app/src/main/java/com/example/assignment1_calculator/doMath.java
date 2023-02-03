// Alexsandria Ryan
// Assignment #1
// MOBI3002
// February 2, 2023
// Portfolio version

package com.example.assignment1_calculator;

import android.widget.Toast;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class doMath {

    private final int maxString = 10;

    public String updatePreview(String currentPreview, String currentDisplay, String input) {
        // if display is blank, return current preview, else return current preview + display value + operator
        return (currentDisplay.equals("")) ? currentPreview : (currentPreview + " " + currentDisplay + " " + input);
    }

    public String updateDisplay(String currentDisplay, String currentPreview, String input, Toast max) {
        // if display should be reset from previous equation
        if (currentPreview.endsWith("=")) {
            return input;

        // if display has not already maxed out its permitted length (not including decimals)
        } else if (currentDisplay.replaceAll("\\.", "").length() < maxString) {
            return (currentDisplay + input);

        // if display has maxed out its permitted length
        } else {
            max.show();
            return (currentDisplay);
        }
    }

    public String checkOperator(String currentPreview, String currentDisplay, Toast failed, Toast maxResult, String operator) {
        String retString;
        // if preview and display are both blank, return nothing
        if (currentPreview.endsWith("=")) {
            retString = currentDisplay + " " + operator;

        // if the current display had everything deleted before the E (when using scientific notation), remove the E
        } else if (currentDisplay.endsWith("E")) {
            retString = currentDisplay.replaceAll("E", "") + " " + operator;

        // if the display is only a -, a . or -.  , add 0 before it
        } else if (currentDisplay.matches("-|\\.|-\\.")) {
            retString = "0 " + operator;

        // if the display starts with a decimal, add 0 before it
        } else if (currentDisplay.startsWith(".")) {
            retString = "0" + currentDisplay + " " + operator;

        // if both the preview and display are blank, return blank
        } else if (currentPreview.isBlank() && currentDisplay.isBlank()) {
            retString = "";

        // if display is blank, update operator in preview
        } else if (!currentPreview.isBlank() && currentDisplay.isBlank()) {
            retString = currentPreview.substring(0, currentPreview.length() -1) + operator;

        // if preview is blank, add operand to preview
        } else if (currentPreview.isBlank() && !currentDisplay.isBlank()) {
            retString = currentDisplay + " " + operator;

        // proceed with calculation
        } else {
            retString = calculate(currentPreview + " " + currentDisplay, failed, maxResult);
            retString = retString + " " + operator;
        }

        return retString;
    }

    public String calculate(String equation, Toast failed, Toast maxResult) {
        String[] split = equation.trim().split("\s+");
        double answer = 0;
        double num1;
        double num2;
        String operator;

        try {
            // if only two results in the split string, answer will be the first operand
            if (split.length == 2 ) {
                // if the operand ends with "E" (scientific notation), then it will be removed
                if (split[0].endsWith("E")) {
                    split[0] = split[0].replaceAll("E", "");
                }
                answer = Double.parseDouble(split[0]);

            // split the equation into two operands and one operator
            } else {
                num1 = Double.parseDouble(split[0]);
                num2 = Double.parseDouble(split[2]);
                operator = split[1];

                // if division by 0; only illegal if num2 == 0
                if (operator.equals("÷") && num2 == 0) {
                    return "NaN";
                }

                switch (operator) {
                    case "+" -> answer = num1 + num2;
                    case "−" -> answer = num1 - num2;
                    case "×" -> answer = num1 * num2;
                    case "÷" -> answer = num1 / num2;
                    default -> System.out.println("Failed calculation.");
                }
            }

        } catch (Exception e) {
            System.out.println("Failed splitting & parsing.");
            failed.show();
        }

        // if result is longer than max string and requires formatting, show warning message
        if (Double.toString(answer).length() > maxString && Double.toString(answer).contains(".")) {
            maxResult.show();
        }

        // double-check answer is an acceptable length to be printed to the calculator as-is, or as scientific notation
        BigDecimal bd = new BigDecimal(Double.toString(answer));
        String[] noDecimals;
        long check = 99999999999L;
        if (!bd.toEngineeringString().contains("E")) {
            noDecimals = bd.toEngineeringString().split("\\.");
            check = Long.parseLong(noDecimals[0]);
        }

        DecimalFormat df1 = new DecimalFormat("#0.###");
        DecimalFormat df2 = new DecimalFormat("0.###E0");
        // if the long (without decimals) is less than the max string...
        if (Long.toString(check).length() <= maxString) {
            // if the double, when formatted to the 3rd decimal place, is less than the max string...
            if (df1.format(Double.parseDouble(bd.toPlainString())).length() <= maxString) {
                return df1.format(Double.parseDouble(bd.toPlainString()));
            }
        }
        // otherwise, format as scientific notation
        return df2.format(answer);
    }

    public String delete(String currentDisplay) {
        return (currentDisplay == null) ? null : currentDisplay.replaceAll(".$", "");
    }
}