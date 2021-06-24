package com.aditya.scientificcalculator;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LengthActivity extends AppCompatActivity {

    EditText editText;
    Spinner spinnerinput;
    Spinner spinneroutput;
    String Tag = "LengthConverter";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);
        Log.i(Tag, "--on create--");
        toolbar = findViewById(R.id.toolbarLengthConverter);
        setSupportActionBar(toolbar);
        //calling get support action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.Input12);
        editText.setShowSoftInputOnFocus(false); //this will disable the keyboard pop up of the device
        spinnerinput = findViewById(R.id.inputspinner14);
        spinneroutput = findViewById(R.id.outputspinner13);

        //setting up spinner item array that will be displayed inside the spinner
        String[] from = {"Mile", "Kilometer", "Inch", "Millimeter", "Metre", "Yard", "Foot", "Nautical Mile"};
        ArrayAdapter ad = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, from);
        spinnerinput.setAdapter(ad);

        String[] to = {"Mile", "Kilometer", "Inch", "Millimeter", "Metre", "Yard", "Foot", "Nautical Mile"};
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
    //this function handles android os back button
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

    public void equalsButton(View view) {
        try {
           //logic for length conversion
            Double result;
            String toasttext;
            Double amount = Double.parseDouble(editText.getText().toString());
            //applying conditions from = mile
            if (spinnerinput.getSelectedItem() == "Mile" && spinneroutput.getSelectedItem() == "Kilometer") {
                result = 1.60934 * amount;
                toasttext = "Mile to Km " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Mile" && spinneroutput.getSelectedItem() == "Inch") {
                result = 63360 * amount;
                toasttext = "Mile to Inch " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Mile" && spinneroutput.getSelectedItem() == "Millimeter") {
                result = 1.609e+6 * amount;
                toasttext = "Mile to Millimeter " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Mile" && spinneroutput.getSelectedItem() == "Metre") {
                result = 1609.34 * amount;
                toasttext = "Mile to Metre " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Mile" && spinneroutput.getSelectedItem() == "Yard") {
                result = 1760 * amount;
                toasttext = "Mile to Yard " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Mile" && spinneroutput.getSelectedItem() == "Foot") {
                result = 5280 * amount;
                toasttext = "Mile to Foot " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Mile" && spinneroutput.getSelectedItem() == "Nautical Mile") {
                result = 0.868976 * amount;
                toasttext = "Mile to Nautical Mile " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Mile" && spinneroutput.getSelectedItem() == "Mile") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }

            //applying conditions from = Kilometer
            if (spinnerinput.getSelectedItem() == "Kilometer" && spinneroutput.getSelectedItem() == "Mile") {
                result = 0.621371 * amount;
                toasttext = "Kilometer to Mile " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilometer" && spinneroutput.getSelectedItem() == "Inch") {
                result = 39370.1 * amount;
                toasttext = "Kilometer to Inch " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilometer" && spinneroutput.getSelectedItem() == "Millimeter") {
                result = 1e+6 * amount;
                toasttext = "Kilometer to Millimeter " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilometer" && spinneroutput.getSelectedItem() == "Metre") {
                result = 1000 * amount;
                toasttext = "Kilometer to Metre " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilometer" && spinneroutput.getSelectedItem() == "Yard") {
                result = 1093.61 * amount;
                toasttext = "Kilometer to Yard " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilometer" && spinneroutput.getSelectedItem() == "Foot") {
                result = 3280.84 * amount;
                toasttext = "Kilometer to Foot " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilometer" && spinneroutput.getSelectedItem() == "Nautical Mile") {
                result = 0.539957 * amount;
                toasttext = "Kilometer to Nautical Mile " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilometer" && spinneroutput.getSelectedItem() == "Kilometer") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }

            //applying conditions from = Inch
            if (spinnerinput.getSelectedItem() == "Inch" && spinneroutput.getSelectedItem() == "Mile") {
                result = 1.5783e-5 * amount;
                toasttext = "Inch to Mile " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Inch" && spinneroutput.getSelectedItem() == "Kilometer") {
                result = 2.54e-5 * amount;
                toasttext = "Inch to Kilometer " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Inch" && spinneroutput.getSelectedItem() == "Inch") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Inch" && spinneroutput.getSelectedItem() == "Millimeter") {
                result = 25.4 * amount;
                toasttext = "Inch to Millimeter " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Inch" && spinneroutput.getSelectedItem() == "Metre") {
                result = 0.0254 * amount;
                toasttext = "Inch to Metre " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Inch" && spinneroutput.getSelectedItem() == "Yard") {
                result = 0.0277778 * amount;
                toasttext = "Inch to Yard " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Inch" && spinneroutput.getSelectedItem() == "Foot") {
                result = 0.0833333 * amount;
                toasttext = "Inch to Foot " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Inch" && spinneroutput.getSelectedItem() == "Nautical Mile") {
                result = 1.3715e-5 * amount;
                toasttext = "Inch to Nautical Mile" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Millimeter
            if (spinnerinput.getSelectedItem() == "Millimeter" && spinneroutput.getSelectedItem() == "Mile") {
                result = 6.2137e-7 * amount;
                toasttext = "Millimeter to Mile " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Millimeter" && spinneroutput.getSelectedItem() == "Kilometer") {
                result = 1e-6 * amount;
                toasttext = "Millimeter to Kilometer " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Millimeter" && spinneroutput.getSelectedItem() == "Inch") {
                result = 0.0393701 * amount;
                toasttext = "Millimeter to Inch " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Millimeter" && spinneroutput.getSelectedItem() == "Millimeter") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Millimeter" && spinneroutput.getSelectedItem() == "Metre") {
                result = 0.001 * amount;
                toasttext = "Millimeter to Metre " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Millimeter" && spinneroutput.getSelectedItem() == "Yard") {
                result = 0.00109361 * amount;
                toasttext = "Millimeter to Yard " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Millimeter" && spinneroutput.getSelectedItem() == "Foot") {
                result = 0.00328084 * amount;
                toasttext = "Millimeter to Foot " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Millimeter" && spinneroutput.getSelectedItem() == "Nautical Mile") {
                result = 5.3996e-7 * amount;
                toasttext = "Millimeter to Nautical Mile" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Meter
            if (spinnerinput.getSelectedItem() == "Meter" && spinneroutput.getSelectedItem() == "Mile") {
                result = 0.000621371 * amount;
                toasttext = "Meter to Mile " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Metre" && spinneroutput.getSelectedItem() == "Kilometer") {
                result = 0.001 * amount;
                toasttext = "Metre to Kilometer " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Metre" && spinneroutput.getSelectedItem() == "Inch") {
                result = 39.3701 * amount;
                toasttext = "Metre to Inch " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Metre" && spinneroutput.getSelectedItem() == "Millimeter") {
                result = 1000 * amount;
                toasttext = "Metre to Millimeter " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Metre" && spinneroutput.getSelectedItem() == "Metre") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Metre" && spinneroutput.getSelectedItem() == "Yard") {
                result = 1.09361 * amount;
                toasttext = "Metre to Yard " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Metre" && spinneroutput.getSelectedItem() == "Foot") {
                result = 3.28084 * amount;
                toasttext = "Metre to Foot " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Metre" && spinneroutput.getSelectedItem() == "Nautical Mile") {
                result = 0.000539957 * amount;
                toasttext = "Metre to Nautical Mile" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Yard
            if (spinnerinput.getSelectedItem() == "Yard" && spinneroutput.getSelectedItem() == "Mile") {
                result = 0.000568182 * amount;
                toasttext = "Yard to Mile " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Yard" && spinneroutput.getSelectedItem() == "Kilometer") {
                result = 0.0009144 * amount;
                toasttext = "Yard to Kilometer " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Yard" && spinneroutput.getSelectedItem() == "Inch") {
                result = 36 * amount;
                toasttext = "Yard to Inch " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Yard" && spinneroutput.getSelectedItem() == "Millimeter") {
                result = 914.4 * amount;
                toasttext = "Yard to Millimeter " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Yard" && spinneroutput.getSelectedItem() == "Metre") {
                result = 0.9144 * amount;
                toasttext = "Yard to Metre " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Yard" && spinneroutput.getSelectedItem() == "Yard") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Yard" && spinneroutput.getSelectedItem() == "Foot") {
                result = 3 * amount;
                toasttext = "Yard to Foot " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Yard" && spinneroutput.getSelectedItem() == "Nautical Mile") {
                result = 0.000493737 * amount;
                toasttext = "Yard to Nautical Mile" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Foot
            if (spinnerinput.getSelectedItem() == "Foot" && spinneroutput.getSelectedItem() == "Mile") {
                result = 0.000189394 * amount;
                toasttext = "Foot to Mile " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Foot" && spinneroutput.getSelectedItem() == "Kilometer") {
                result = 0.0003048 * amount;
                toasttext = "Foot to Kilometer " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Foot" && spinneroutput.getSelectedItem() == "Inch") {
                result = 12 * amount;
                toasttext = "Foot to Inch " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Foot" && spinneroutput.getSelectedItem() == "Millimeter") {
                result = 304.8 * amount;
                toasttext = "Foot to Millimeter " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Foot" && spinneroutput.getSelectedItem() == "Metre") {
                result = 0.3048 * amount;
                toasttext = "Foot to Metre " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Foot" && spinneroutput.getSelectedItem() == "Yard") {
                result = 0.333333 * amount;
                toasttext = "Foot to Yard " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Foot" && spinneroutput.getSelectedItem() == "Foot") {
                toasttext = "Error 2X00f_00#You! ";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Foot" && spinneroutput.getSelectedItem() == "Nautical Mile") {
                result = 0.000164579 * amount;
                toasttext = "Foot to Nautical Mile" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Nautical Mile
            if (spinnerinput.getSelectedItem() == "Nautical Mile" && spinneroutput.getSelectedItem() == "Mile") {
                result = 1.15078 * amount;
                toasttext = "Nautical Mile to Mile " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Nautical Mile" && spinneroutput.getSelectedItem() == "Kilometer") {
                result = 1.852 * amount;
                toasttext = "Nautical Mile to Kilometer " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Nautical Mile" && spinneroutput.getSelectedItem() == "Inch") {
                result = 72913.4 * amount;
                toasttext = "Nautical Mile to Inch " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Nautical Mile" && spinneroutput.getSelectedItem() == "Millimeter") {
                result = 1.852e+6 * amount;
                toasttext = "Nautical Mile to Millimeter " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Nautical Mile" && spinneroutput.getSelectedItem() == "Metre") {
                result = 1852 * amount;
                toasttext = "Nautical Mile to Metre " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Nautical Mile" && spinneroutput.getSelectedItem() == "Yard") {
                result = 2025.37 * amount;
                toasttext = "Nautical Mile to Yard " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Nautical Mile" && spinneroutput.getSelectedItem() == "Foot") {
                result = 6076.12 * amount;
                toasttext = "Nautical Mile to Foot " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Nautical Mile" && spinneroutput.getSelectedItem() == "Nautical Mile") {
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

    public void ClearButton(View view) {
        editText.setText("");
    }
}