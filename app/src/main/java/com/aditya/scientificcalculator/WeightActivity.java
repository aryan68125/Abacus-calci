package com.aditya.scientificcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class WeightActivity extends AppCompatActivity {

    EditText editText;
    Spinner spinnerinput;
    Spinner spinneroutput;
    String Tag = "WeightConverter";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        toolbar = findViewById(R.id.toolbarWeightConverter);
        setSupportActionBar(toolbar);
        //calling get support action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.i(Tag, "--on create--");
        editText = findViewById(R.id.Input20);
        editText.setShowSoftInputOnFocus(false); //this will disable the keyboard pop up of the device
        spinnerinput = findViewById(R.id.inputspinner20);
        spinneroutput = findViewById(R.id.outputspinner20);

        //setting up spinner item array that will be displayed inside the spinner
        String[] from = {"Tonne", "Kilogram", "Gram", "Milligram", "Microgram", "Stone", "Pound", "Ounce"};
        ArrayAdapter ad = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, from);
        spinnerinput.setAdapter(ad);

        String[] to = {"Tonne", "Kilogram", "Gram", "Milligram", "Microgram", "Stone", "Pound", "Ounce"};
        ArrayAdapter ad1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, to);
        spinneroutput.setAdapter(ad1);
    }

    // this event will enable the back activity action bar back button
    // function to the button on press activity action bar back button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.nothing, R.anim.slide_out_right);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //exit animation of currently active activity
    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }

    /*
   create a method updateEditText to update the text in the editText whenever we press a button in our application
           strToAdd = is a new string or text entered by the user that has to be added in the editText
               */
    private void updateEditText(String strToAdd) {
        String oldString = editText.getText().toString(); //this will grab any text that is previously entered by the user
        /*
        we need the position if the cursor because we want to enter the digit where the cursor is and not at the end of the String
        example = jkka|scbs -> jkka|0scbs zero is inserted in the place where cursor is and not at the end of the string
        so we had to split the string into two inorder to do that
        so we are gonna grab the cursor position and split the oldString into two different String
        editText.getSelectionStart(); will give you the cursor position in the editText field
        */
        int cursorPosition = editText.getSelectionStart();
        String leftOldString = oldString.substring(0, cursorPosition);
        String rightOldString = oldString.substring(cursorPosition); //this will create a subtring from cursor position to all the way to the end

        /*
        no we will check if the default hint is there in the editText or not
        if yes then set it to string to add
        if no then set it to editText.setText(String.format("%s%s%s",leftOldString,strToAdd,rightOldString));
         */
        if (editText.getText().toString() == "Input"|| editText.getText().toString() =="Input Field is Empty"|| editText.getText().toString() == "Error 2X00f_00#You!") {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        else{
            //now we are going to update the editText with this new String
        /*
        editText.setText(String.format("%s%s%s",leftOldString,strToAdd,rightOldString)); will update the String in our edit Text
        according to our cursor's position
        String.format("%s%s%s",leftOldString,strToAdd,rightOldString) = tells the editText in which format it has to show the Strings
        first we have to print the leftString then we will print the String that we want to add at the cursor's position and then we will
        print the right string
         */
            editText.setText(String.format("%s%s%s", leftOldString, strToAdd, rightOldString));

        /*
        right now whenever we are entering string to our edit Text our cursor jumps to the left most position in EditText field
        so we need to fix that by fixing the position of our cursor in the EditText field
        editText.setSelection(cursorPosition+1); will fix the issue of cursor position in our editText field
         */
            editText.setSelection(cursorPosition + 1);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(Tag, "--on Restart--");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(Tag, "--on Start--");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(Tag, "--on Resume--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(Tag, "--on Pause--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(Tag, "--on Stop--");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(Tag, "--on Destroy--");
    }

    public void backspaceButton(View view) {
        //this will delete a digit from the entered number
        /*
        in order to that we need to get the cursor's position and grab the length of the string in the editText field
        we need to get the length of the String so that we can prevent the user from trying to backspace when there is nothing in the
        editText field
        editText.getText().length(); = will give us the length of the text that is being entered in the editText field
         */
        int cursorPosition = editText.getSelectionStart();
        int textLength = editText.getText().length();
        if(textLength!=0 && cursorPosition!=0){
            /*
            spannableString will allow us to replace different characters within our string
             */
            SpannableStringBuilder selection = (SpannableStringBuilder) editText.getText();
            selection.replace(cursorPosition-1,cursorPosition,""); //replacing the character with an empty string
            editText.setText(selection); //updating the text in the editText field
            editText.setSelection(cursorPosition-1); //update the cursor's position
        }
    }

    public void sevenButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("7");
    }

    public void eightButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("8");
    }

    public void nineButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("9");
    }

    public void fourButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("4");
    }

    public void fiveButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("5");
    }

    public void sixButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("6");
    }

    public void oneButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("1");
    }

    public void twoButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("2");
    }

    public void threeButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("3");
    }

    public void dotButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText(".");
    }

    public void zeroButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        if (editText.getText().toString().equals("Input Field is Empty")|| editText.getText().toString().equals("Error 2X00f_00#You!")) {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        }
        updateEditText("0");
    }
    public void ClearButton(View view)
    {
        editText.setText("");
    }

    public void equalsButton(View view) {
        try {
             //weight conversion logic will come here
            Double result;
            String toasttext;
            Double amount = Double.parseDouble(editText.getText().toString());

            //applying conditions for = Tonne
            if (spinnerinput.getSelectedItem() == "Tonne" && spinneroutput.getSelectedItem() == "Tonne") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Tonne" && spinneroutput.getSelectedItem() == "Kilogram") {
                result = 1000 * amount;
                toasttext = "Tonne to Kilogram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Tonne" && spinneroutput.getSelectedItem() == "Gram") {
                result = 1e+6 * amount;
                toasttext = "Tonne to Gram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Tonne" && spinneroutput.getSelectedItem() == "Milligram") {
                result = 1e+9 * amount;
                toasttext = "Tonne to Milligram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Tonne" && spinneroutput.getSelectedItem() == "Microgram") {
                result = 1e+12 * amount;
                toasttext = "Tonne to Microgram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Tonne" && spinneroutput.getSelectedItem() == "Stone") {
                result = 157.473 * amount;
                toasttext = "Tonne to Stone " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Tonne" && spinneroutput.getSelectedItem() == "Pound") {
                result = 2204.62 * amount;
                toasttext = "Tonne to Pound " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Tonne" && spinneroutput.getSelectedItem() == "Ounce") {
                result = 35274 * amount;
                toasttext = "Tonne to Ounce " + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Kilogram
            if (spinnerinput.getSelectedItem() == "Kilogram" && spinneroutput.getSelectedItem() == "Tonne") {
                result = 0.001 * amount;
                toasttext = "Kilogram to Tonne " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilogram" && spinneroutput.getSelectedItem() == "Kilogram") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilogram" && spinneroutput.getSelectedItem() == "Gram") {
                result = 1000 * amount;
                toasttext = "Kilogram to Gram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilogram" && spinneroutput.getSelectedItem() == "Milligram") {
                result = 1e+6 * amount;
                toasttext = "Kilogram to Milligram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilogram" && spinneroutput.getSelectedItem() == "Microgram") {
                result = 1e+9 * amount;
                toasttext = "Kilogram to Microgram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilogram" && spinneroutput.getSelectedItem() == "Stone") {
                result = 0.157473 * amount;
                toasttext = "Kilogram to Stone " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilogram" && spinneroutput.getSelectedItem() == "Pound") {
                result = 2.20462 * amount;
                toasttext = "Kilogram to Pound " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilogram" && spinneroutput.getSelectedItem() == "Ounce") {
                result = 35.274 * amount;
                toasttext = "Kilogram to Ounce " + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Gram
            if (spinnerinput.getSelectedItem() == "Gram" && spinneroutput.getSelectedItem() == "Tonne") {
                result = 1e-6 * amount;
                toasttext = "Gram to Tonne " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gram" && spinneroutput.getSelectedItem() == "Kilogram") {
                result = 0.001 * amount;
                toasttext = "Gram to Kilogram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gram" && spinneroutput.getSelectedItem() == "Gram") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gram" && spinneroutput.getSelectedItem() == "Milligram") {
                result = 1000 * amount;
                toasttext = "Gram to Milligram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gram" && spinneroutput.getSelectedItem() == "Microgram") {
                result = 1e+6 * amount;
                toasttext = "Gram to Microgram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gram" && spinneroutput.getSelectedItem() == "Stone") {
                result = 0.000157473 * amount;
                toasttext = "Gram to Stone " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gram" && spinneroutput.getSelectedItem() == "Pound") {
                result = 0.00220462 * amount;
                toasttext = "Gram to Pound " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gram" && spinneroutput.getSelectedItem() == "Ounce") {
                result = 0.035274 * amount;
                toasttext = "Gram to Ounce" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Milligram
            if (spinnerinput.getSelectedItem() == "Milligram" && spinneroutput.getSelectedItem() == "Tonne") {
                result = 1e-9 * amount;
                toasttext = "Milligram to Tonne " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Milligram" && spinneroutput.getSelectedItem() == "Kilogram") {
                result = 1e-6 * amount;
                toasttext = "Milligram to Kilogram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Milligram" && spinneroutput.getSelectedItem() == "Gram") {
                result = 0.001 * amount;
                toasttext = "Milligram to Gram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Milligram" && spinneroutput.getSelectedItem() == "Milligram") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Milligram" && spinneroutput.getSelectedItem() == "Microgram") {
                result = 1000 * amount;
                toasttext = "Milligram to Microgram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Milligram" && spinneroutput.getSelectedItem() == "Stone") {
                result = 1.5747e-7 * amount;
                toasttext = "Milligram to Stone " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Milligram" && spinneroutput.getSelectedItem() == "Pound") {
                result = 2.2046e-6 * amount;
                toasttext = "Milligram to Pound " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Milligram" && spinneroutput.getSelectedItem() == "Ounce") {
                result = 3.5274e-5 * amount;
                toasttext = "Milligram to Ounce" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Microgram
            if (spinnerinput.getSelectedItem() == "Microgram" && spinneroutput.getSelectedItem() == "Tonne") {
                result = 1e-12 * amount;
                toasttext = "Microgram to Tonne " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Microgram" && spinneroutput.getSelectedItem() == "Kilogram") {
                result = 1e-9 * amount;
                toasttext = "Microgram to Kilogram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Microgram" && spinneroutput.getSelectedItem() == "Gram") {
                result = 1e-6 * amount;
                toasttext = "Microgram to Gram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Microgram" && spinneroutput.getSelectedItem() == "Milligram") {
                result = 0.001 * amount;
                toasttext = "Microgram to Milligram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Microgram" && spinneroutput.getSelectedItem() == "Microgram") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Microgram" && spinneroutput.getSelectedItem() == "Stone") {
                result = 1.5747e-10 * amount;
                toasttext = "Microgram to Stone " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Microgram" && spinneroutput.getSelectedItem() == "Pound") {
                result = 2.2046e-9 * amount;
                toasttext = "Microgram to Pound " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Microgram" && spinneroutput.getSelectedItem() == "Ounce") {
                result = 3.5274e-8 * amount;
                toasttext = "Microgram to Ounce" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Stone
            if (spinnerinput.getSelectedItem() == "Stone" && spinneroutput.getSelectedItem() == "Tonne") {
                result = 0.00635029 * amount;
                toasttext = "Stone to Tonne " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Stone" && spinneroutput.getSelectedItem() == "Kilogram") {
                result = 6.35029 * amount;
                toasttext = "Stone to Kilogram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Stone" && spinneroutput.getSelectedItem() == "Gram") {
                result = 6350.29 * amount;
                toasttext = "Stone to Gram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Stone" && spinneroutput.getSelectedItem() == "Milligram") {
                result = 6.35e+6 * amount;
                toasttext = "Stone to Milligram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Stone" && spinneroutput.getSelectedItem() == "Microgram") {
                result = 6.35e+9 * amount;
                toasttext = "Stone to Microgram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Stone" && spinneroutput.getSelectedItem() == "Stone") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Stone" && spinneroutput.getSelectedItem() == "Pound") {
                result = 14 * amount;
                toasttext = "Stone to Pound " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Stone" && spinneroutput.getSelectedItem() == "Ounce") {
                result = 224 * amount;
                toasttext = "Stone to Ounce" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Pound
            if (spinnerinput.getSelectedItem() == "Pound" && spinneroutput.getSelectedItem() == "Tonne") {
                result = 0.000453592 * amount;
                toasttext = "Pound to Tonne " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Pound" && spinneroutput.getSelectedItem() == "Kilogram") {
                result = 0.453592 * amount;
                toasttext = "Pound to Kilogram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Pound" && spinneroutput.getSelectedItem() == "Gram") {
                result = 453.592 * amount;
                toasttext = "Pound to Gram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Pound" && spinneroutput.getSelectedItem() == "Milligram") {
                result = 453592 * amount;
                toasttext = "Pound to Milligram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Pound" && spinneroutput.getSelectedItem() == "Microgram") {
                result = 4.536e+8 * amount;
                toasttext = "Pound to Microgram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Pound" && spinneroutput.getSelectedItem() == "Stone") {
                result = 0.0714286 * amount;
                toasttext = "Pound to Stone " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Pound" && spinneroutput.getSelectedItem() == "Pound") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Pound" && spinneroutput.getSelectedItem() == "Ounce") {
                result = 16 * amount;
                toasttext = "Pound to Ounce" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Ounce
            if (spinnerinput.getSelectedItem() == "Ounce" && spinneroutput.getSelectedItem() == "Tonne") {
                result = 2.835e-5 * amount;
                toasttext = "Ounce to Tonne " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Ounce" && spinneroutput.getSelectedItem() == "Kilogram") {
                result = 0.0283495 * amount;
                toasttext = "Ounce to Kilogram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Ounce" && spinneroutput.getSelectedItem() == "Gram") {
                result = 28.3495 * amount;
                toasttext = "Ounce to Gram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Ounce" && spinneroutput.getSelectedItem() == "Milligram") {
                result = 28349.5 * amount;
                toasttext = "Ounce to Milligram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Ounce" && spinneroutput.getSelectedItem() == "Microgram") {
                result = 2.83495e+7 * amount;
                toasttext = "Ounce to Microgram " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Ounce" && spinneroutput.getSelectedItem() == "Stone") {
                result = 0.00446429 * amount;
                toasttext = "Ounce to Stone " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Ounce" && spinneroutput.getSelectedItem() == "Pound") {
                result = 0.0625 * amount;
                toasttext = "Ounce to Pound " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Ounce" && spinneroutput.getSelectedItem() == "Ounce") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
        }
        catch (Exception e)
        {
            String messege="Input Field is Empty";
            editText.setText(messege);
        }
                /*
                   we will use try and catch to handel any unexpected errors in our application so that if something gos wrong our application does not crash
                   instead it can still function as normal with an error message like hey this is what went wrong to notify the user
                   try{
                     //the code here which you know might cause problem under a certain scenario
                     }
                     catch (Exception e)
                     {
                     //error message that you want to display to the user
                     }
                 */
    }

}