// Alexsandria Ryan
// Assignment #1
// MOBI3002
// February 2, 2023
// Portfolio version

package com.example.assignment1_calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    doMath math = new doMath();
    public static boolean autoClear = false;

    private TextView txtPreview, txtDisplay;
    public Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnAdd, btnSub, btnMul, btnDiv, btnEqu, btnC, btnNegPos, btnDec, btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // text views
        txtPreview = findViewById(R.id.txtPreview);
        txtDisplay = findViewById(R.id.txtDisplay);

        // buttons
        btn0    = findViewById(R.id.btn0);
        btn1    = findViewById(R.id.btn1);
        btn2    = findViewById(R.id.btn2);
        btn3    = findViewById(R.id.btn3);
        btn4    = findViewById(R.id.btn4);
        btn5    = findViewById(R.id.btn5);
        btn6    = findViewById(R.id.btn6);
        btn7    = findViewById(R.id.btn7);
        btn8    = findViewById(R.id.btn8);
        btn9    = findViewById(R.id.btn9);
        btnAdd  = findViewById(R.id.btnAdd);
        btnSub  = findViewById(R.id.btnSub);
        btnMul  = findViewById(R.id.btnMul);
        btnDiv  = findViewById(R.id.btnDiv);
        btnEqu  = findViewById(R.id.btnEqu);
        btnC    = findViewById(R.id.btnC);
        btnNegPos  = findViewById(R.id.btnNegPos);
        btnDec  = findViewById(R.id.btnDec);
        btnDel  = findViewById(R.id.btnDel);

        // listeners
        btn0.setOnClickListener(onClick);
        btn1.setOnClickListener(onClick);
        btn2.setOnClickListener(onClick);
        btn3.setOnClickListener(onClick);
        btn4.setOnClickListener(onClick);
        btn5.setOnClickListener(onClick);
        btn6.setOnClickListener(onClick);
        btn7.setOnClickListener(onClick);
        btn8.setOnClickListener(onClick);
        btn9.setOnClickListener(onClick);
        btnAdd.setOnClickListener(onClick);
        btnSub.setOnClickListener(onClick);
        btnMul.setOnClickListener(onClick);
        btnDiv.setOnClickListener(onClick);
        btnEqu.setOnClickListener(onClick);
        btnC.setOnClickListener(onClick);
        btnNegPos.setOnClickListener(onClick);
        btnDec.setOnClickListener(onClick);
        btnDel.setOnClickListener(onClick);
    }

    private final OnClickListener onClick = new OnClickListener() {

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {

            String newPreview;
            String newDisplay;
            String currentPreview = (String)txtPreview.getText();
            String currentDisplay = (String)txtDisplay.getText();

            // toasts
            Toast max = Toast.makeText(getApplicationContext(), "Max digits reached.", Toast.LENGTH_SHORT);
            Toast maxResult = Toast.makeText(getApplicationContext(), "Result has been formatted to fit screen.", Toast.LENGTH_SHORT);
            Toast failed = Toast.makeText(getApplicationContext(), "Whoops! Something went wrong.", Toast.LENGTH_SHORT);

            // if autoclear is required, clear the preview and display and reset autoclear boolean
            if (MainActivity.autoClear) {
                txtPreview.setText("");
                txtDisplay.setText("");
                MainActivity.autoClear = false;
            }

            // switch case for button listeners
            switch(v.getId()) {

                case R.id.btn0:
                    newDisplay = math.updateDisplay(currentDisplay, currentPreview, "0", max);
                    txtDisplay.setText(newDisplay);
                    break;

                case R.id.btn1:
                    newDisplay = math.updateDisplay(currentDisplay, currentPreview, "1", max);
                    txtDisplay.setText(newDisplay);
                    break;

                case R.id.btn2:
                    newDisplay = math.updateDisplay(currentDisplay, currentPreview, "2", max);
                    txtDisplay.setText(newDisplay);
                    break;

                case R.id.btn3:
                    newDisplay = math.updateDisplay(currentDisplay, currentPreview, "3", max);
                    txtDisplay.setText(newDisplay);
                    break;

                case R.id.btn4:
                    newDisplay = math.updateDisplay(currentDisplay, currentPreview, "4", max);
                    txtDisplay.setText(newDisplay);
                    break;

                case R.id.btn5:
                    newDisplay = math.updateDisplay(currentDisplay, currentPreview, "5", max);
                    txtDisplay.setText(newDisplay);
                    break;

                case R.id.btn6:
                    newDisplay = math.updateDisplay(currentDisplay, currentPreview, "6", max);
                    txtDisplay.setText(newDisplay);
                    break;

                case R.id.btn7:
                    newDisplay = math.updateDisplay(currentDisplay, currentPreview, "7", max);
                    txtDisplay.setText(newDisplay);
                    break;

                case R.id.btn8:
                    newDisplay = math.updateDisplay(currentDisplay, currentPreview, "8", max);
                    txtDisplay.setText(newDisplay);
                    break;

                case R.id.btn9:
                    newDisplay = math.updateDisplay(currentDisplay, currentPreview, "9", max);
                    txtDisplay.setText(newDisplay);
                    break;

                case R.id.btnEqu:
                    // if the display is only a -, a . or -. , update display to "0"
                    if (currentDisplay.matches("-|\\.|-\\.")) {
                        currentDisplay = "0";

                    } else if (currentDisplay.startsWith(".")) {
                        currentDisplay = "0" + currentDisplay;
                    }

                    if (currentPreview.endsWith("=")) {
                        newPreview = currentDisplay + " =";
                        txtPreview.setText(newPreview);
                        txtDisplay.setText(currentDisplay);
                        MainActivity.autoClear = true;

                    } else if (currentDisplay.isBlank()) {
                        newPreview = math.checkOperator(currentPreview, currentDisplay, failed, maxResult, "=");
                        txtPreview.setText(newPreview);
                        String[] split = newPreview.trim().split("\s+");
                        txtDisplay.setText(split[0]);

                    } else {
                        newPreview = math.updatePreview(currentPreview, currentDisplay, "=");
                        newDisplay = math.calculate(newPreview, failed, maxResult);
                        txtPreview.setText(newPreview);
                        txtDisplay.setText(newDisplay);
                        MainActivity.autoClear = true;
                    }

                    break;

                case R.id.btnAdd:
                    newPreview = math.checkOperator(currentPreview, currentDisplay, failed, maxResult, "+");
                    txtPreview.setText(newPreview);
                    txtDisplay.setText("");
                    break;

                case R.id.btnSub:
                    newPreview = math.checkOperator(currentPreview, currentDisplay, failed, maxResult, "−");
                    txtPreview.setText(newPreview);
                    txtDisplay.setText("");
                    break;

                case R.id.btnMul:
                    newPreview = math.checkOperator(currentPreview, currentDisplay, failed, maxResult, "×");
                    txtPreview.setText(newPreview);
                    txtDisplay.setText("");
                    break;

                case R.id.btnDiv:
                    newPreview = math.checkOperator(currentPreview, currentDisplay, failed, maxResult, "÷");
                    if (newPreview.equals("NaN")) {
                        MainActivity.autoClear = true;

                    } else {
                        txtPreview.setText(newPreview);
                        txtDisplay.setText("");
                    }

                    break;

                case R.id.btnC:
                    txtPreview.setText("");
                    txtDisplay.setText("");
                    break;

                case R.id.btnNegPos:
                    // add negative value to display
                    if (!currentDisplay.startsWith("-")) {
                        newDisplay = "-" + currentDisplay;
                        txtDisplay.setText(newDisplay);

                    // remove negative value from display
                    } else {
                        newDisplay = currentDisplay.replaceAll("-", "");
                        txtDisplay.setText(newDisplay);
                    }

                    break;

                case R.id.btnDec:
                    // if the display does not already contain a decimal
                    if (!currentDisplay.contains(".")) {
                        newDisplay = math.updateDisplay(currentDisplay, currentPreview, ".", max);
                        txtDisplay.setText(newDisplay);
                    }

                    break;

                case R.id.btnDel:
                    newDisplay = math.delete(currentDisplay);
                    txtDisplay.setText(newDisplay);
                    break;

                default:
                    failed.show();
            }
        }
    };
}