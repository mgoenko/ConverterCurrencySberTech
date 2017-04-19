// MainActivity.java
// Calculates a bill total based on a tip percentage
package com.deitel.tipcalculator;

import android.os.Bundle; // for saving state information
import android.support.v7.app.AppCompatActivity; // base class
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.widget.EditText; // for bill amount input
import android.widget.TextView; // for displaying text

import java.text.NumberFormat; // for currency formatting

// MainActivity class for the Tip Calculator app
public class MainActivity extends AppCompatActivity {

   // currency and percent formatter objects
   private static final NumberFormat currencyFormat =
      NumberFormat.getCurrencyInstance();

   private double billAmount = 0.0; // bill amount entered by the user, позднее можно заменить на BigDecimal из пакета java.math для точности расчетов
   private TextView amountTextView; // shows formatted bill amount
   private TextView tipTextView; // shows calculated tip amount
   private TextView totalTextView; // shows calculated total bill amount
   private TextView ResultKeshtextView; //для вывода конечной суммы
   private double kursValut=65;

   // called when the activity is first created
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState); // call superclass onCreate
      setContentView(R.layout.activity_main); // inflate the GUI

      // get references to programmatically manipulated TextViews
      amountTextView = (TextView) findViewById(R.id.amountTextView);
      tipTextView = (TextView) findViewById(R.id.tipTextView);
      totalTextView = (TextView) findViewById(R.id.totalTextView);
      tipTextView.setText(currencyFormat.format(0));
      totalTextView.setText(currencyFormat.format(0));

      // set amountEditText's TextWatcher
      EditText amountEditText =
         (EditText) findViewById(R.id.amountEditText);
      amountEditText.addTextChangedListener(amountEditTextWatcher);

      //регистрация слушателя кнопки

   }

   // calculate and display tip and total amounts
   private void calculate() {
      // format percent and display in percentTextView
      //percentTextView.setText(percentFormat.format(percent));
      //вычитать курс

      // calculate the total
      double resultkesh = billAmount * kursValut;

      // display tip and total formatted as currency
      ResultKeshtextView.setText(currencyFormat.format(resultkesh));
   }


   // listener object for the EditText's text-changed events
   private final TextWatcher amountEditTextWatcher = new TextWatcher() {
      // called when the user modifies the bill amount
      @Override
      public void onTextChanged(CharSequence s, int start,
         int before, int count) {

         try { // get bill amount and display currency formatted value
            billAmount = Double.parseDouble(s.toString()) / 100.0;
            amountTextView.setText(currencyFormat.format(billAmount));
         }
         catch (NumberFormatException e) { // if s is empty or non-numeric
            amountTextView.setText("");
            billAmount = 0.0;
         }

         calculate(); // update the tip and total TextViews
      }

      @Override
      public void afterTextChanged(Editable s) { }

      @Override
      public void beforeTextChanged(
         CharSequence s, int start, int count, int after) { }
   };
}