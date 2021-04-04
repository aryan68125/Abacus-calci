package com.aditya.scientificcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class PowerActivity extends AppCompatActivity {

    EditText editText;
    Spinner spinnerinput;
    Spinner spinneroutput;
    String Tag = "PowerConverter";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);
        toolbar = findViewById(R.id.toolbarPowerConverter);
        setSupportActionBar(toolbar);
        //calling get support action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.i(Tag, "--on create--");
        editText = findViewById(R.id.Input21);
        editText.setShowSoftInputOnFocus(false); //this will disable the keyboard pop up of the device
        spinnerinput = findViewById(R.id.inputspinner21);
        spinneroutput = findViewById(R.id.outputspinner21);

        //setting up spinner item array that will be displayed inside the spinner
        String[] from = {"Watt", "Kilowatt", "Megawatt", "Gigawatt", "Terawatt", "Petawatt", "Milliwatt", "HP"};
        ArrayAdapter ad = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, from);
        spinnerinput.setAdapter(ad);

        String[] to = {"Watt", "Kilowatt", "Megawatt", "Gigawatt", "Terawatt", "Petawatt", "Milliwatt", "HP"};
        ArrayAdapter ad1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, to);
        spinneroutput.setAdapter(ad1);
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
            Double result;
            String toasttext;
            Double amount = Double.parseDouble(editText.getText().toString());

            //applying conditions for = Watt
            if (spinnerinput.getSelectedItem() == "Watt" && spinneroutput.getSelectedItem() == "Watt") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Watt" && spinneroutput.getSelectedItem() == "Kilowatt") {
                result = 0.001 * amount;
                toasttext = "Watt to Kilowatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Watt" && spinneroutput.getSelectedItem() == "Megawatt") {
                result = 1e-6 * amount;
                toasttext = "Watt to Megawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Watt" && spinneroutput.getSelectedItem() == "Gigawatt") {
                result = 1e-9 * amount;
                toasttext = "Watt to Gigawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Watt" && spinneroutput.getSelectedItem() == "Terawatt") {
                result = 1e-12 * amount;
                toasttext = "Watt to Terawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Watt" && spinneroutput.getSelectedItem() == "Petawatt") {
                result = 1e-15 * amount;
                toasttext = "Watt to Petawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Watt" && spinneroutput.getSelectedItem() == "Milliwatt") {
                result = 1000 * amount;
                toasttext = "Watt to Milliwatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Watt" && spinneroutput.getSelectedItem() == "HP") {
                result = 0.0013596216 * amount;
                toasttext = "Watt to HP " + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Kilowatt
            if (spinnerinput.getSelectedItem() == "Kilowatt" && spinneroutput.getSelectedItem() == "Watt") {
                result = 1000 * amount;
                toasttext = "Kilowatt to Watt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilowatt" && spinneroutput.getSelectedItem() == "Kilowatt") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilowatt" && spinneroutput.getSelectedItem() == "Megawatt") {
                result = 0.001 * amount;
                toasttext = "Kilowatt to Megawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilowatt" && spinneroutput.getSelectedItem() == "Gigawatt") {
                result = 1e-6 * amount;
                toasttext = "Kilowatt to Gigawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilowatt" && spinneroutput.getSelectedItem() == "Terawatt") {
                result = 1e-9 * amount;
                toasttext = "Kilowatt to Terawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilowatt" && spinneroutput.getSelectedItem() == "Petawatt") {
                result = 1e-12 * amount;
                toasttext = "Kilowatt to Petawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilowatt" && spinneroutput.getSelectedItem() == "Milliwatt") {
                result = 1e+6 * amount;
                toasttext = "Kilowatt to Milliwatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Kilowatt" && spinneroutput.getSelectedItem() == "HP") {
                result = 1.3596216173 * amount;
                toasttext = "Kilowatt to HP " + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Megawatt
            if (spinnerinput.getSelectedItem() == "Megawatt" && spinneroutput.getSelectedItem() == "Watt") {
                result = 1e+6 * amount;
                toasttext = "Megawatt to Watt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Megawatt" && spinneroutput.getSelectedItem() == "Kilowatt") {
                result = 1000 * amount;
                toasttext = "Megawatt to Kilowatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Megawatt" && spinneroutput.getSelectedItem() == "Megawatt") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Megawatt" && spinneroutput.getSelectedItem() == "Gigawatt") {
                result = 0.001 * amount;
                toasttext = "Megawatt to Gigawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Megawatt" && spinneroutput.getSelectedItem() == "Terawatt") {
                result = 1e-6 * amount;
                toasttext = "Megawatt to Terawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Megawatt" && spinneroutput.getSelectedItem() == "Petawatt") {
                result = 1e-9 * amount;
                toasttext = "Megawatt to Petawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Megawatt" && spinneroutput.getSelectedItem() == "Milliwatt") {
                result = 1e+9 * amount;
                toasttext = "Megawatt to Milliwatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Megawatt" && spinneroutput.getSelectedItem() == "HP") {
                result = 1359.6216173 * amount;
                toasttext = "Megawatt to HP" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Gigawatt
            if (spinnerinput.getSelectedItem() == "Gigawatt" && spinneroutput.getSelectedItem() == "Watt") {
                result = 1e+9 * amount;
                toasttext = "Gigawatt to Watt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gigawatt" && spinneroutput.getSelectedItem() == "Kilowatt") {
                result = 1e+6 * amount;
                toasttext = "Gigawatt to Kilowatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gigawatt" && spinneroutput.getSelectedItem() == "Megawatt") {
                result = 1000 * amount;
                toasttext = "Gigawatt to Megawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gigawatt" && spinneroutput.getSelectedItem() == "Gigawatt") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gigawatt" && spinneroutput.getSelectedItem() == "Terawatt") {
                result = 0.001 * amount;
                toasttext = "Gigawatt to Terawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gigawatt" && spinneroutput.getSelectedItem() == "Petawatt") {
                result = 1e-5 * amount;
                toasttext = "Gigawatt to Petawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gigawatt" && spinneroutput.getSelectedItem() == "Milliwatt") {
                result = 1e+12 * amount;
                toasttext = "Gigawatt to Milliwatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Gigawatt" && spinneroutput.getSelectedItem() == "HP") {
                result = 1359621.6173 * amount;
                toasttext = "Gigawatt to HP" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Terawatt
            if (spinnerinput.getSelectedItem() == "Terawatt" && spinneroutput.getSelectedItem() == "Watt") {
                result = 1e+12 * amount;
                toasttext = "Terawatt to Watt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Terawatt" && spinneroutput.getSelectedItem() == "Kilowatt") {
                result = 1e+9 * amount;
                toasttext = "Terawatt to Kilowatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Terawatt" && spinneroutput.getSelectedItem() == "Megawatt") {
                result = 1e+6 * amount;
                toasttext = "Terawatt to Megawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Terawatt" && spinneroutput.getSelectedItem() == "Gigawatt") {
                result = 1000 * amount;
                toasttext = "Terawatt to Gigawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Terawatt" && spinneroutput.getSelectedItem() == "Terawatt") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Terawatt" && spinneroutput.getSelectedItem() == "Petawatt") {
                result = 0.001 * amount;
                toasttext = "Terawatt to Petawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Terawatt" && spinneroutput.getSelectedItem() == "Milliwatt") {
                result = 1e+15 * amount;
                toasttext = "Terawatt to Milliwatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Terawatt" && spinneroutput.getSelectedItem() == "HP") {
                result = 1359621617.3 * amount;
                toasttext = "Terawatt to HP" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Petawatt
            if (spinnerinput.getSelectedItem() == "Petawatt" && spinneroutput.getSelectedItem() == "Watt") {
                result = 1e+15 * amount;
                toasttext = "Petawatt to Watt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Petawatt" && spinneroutput.getSelectedItem() == "Kilowatt") {
                result = 1e+12 * amount;
                toasttext = "Petawatt to Kilowatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Petawatt" && spinneroutput.getSelectedItem() == "Megawatt") {
                result = 1e+9 * amount;
                toasttext = "Petawatt to Megawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Petawatt" && spinneroutput.getSelectedItem() == "Gigawatt") {
                result = 1e+6 * amount;
                toasttext = "Petawatt to Gigawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Petawatt" && spinneroutput.getSelectedItem() == "Terawatt") {
                result = 1000 * amount;
                toasttext = "Petawatt to Terawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Petawatt" && spinneroutput.getSelectedItem() == "Petawatt") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Petawatt" && spinneroutput.getSelectedItem() == "Milliwatt") {
                result = 1e+18 * amount;
                toasttext = "Petawatt to Milliwatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Petawatt" && spinneroutput.getSelectedItem() == "HP") {
                result = 135962161e+4 * amount;
                toasttext = "Petawatt to HP" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = Milliwatt
            if (spinnerinput.getSelectedItem() == "Milliwatt" && spinneroutput.getSelectedItem() == "Watt") {
                result = 0.001 * amount;
                toasttext = "Milliwatt to Watt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Milliwatt" && spinneroutput.getSelectedItem() == "Kilowatt") {
                result = 1e-5 * amount;
                toasttext = "Milliwatt to Kilowatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Milliwatt" && spinneroutput.getSelectedItem() == "Megawatt") {
                result = 1e-9 * amount;
                toasttext = "Milliwatt to Megawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Milliwatt" && spinneroutput.getSelectedItem() == "Gigawatt") {
                result = 1e-12 * amount;
                toasttext = "Milliwatt to Gigawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Milliwatt" && spinneroutput.getSelectedItem() == "Terawatt") {
                result = 1e-15 * amount;
                toasttext = "Milliwatt to Terawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Milliwatt" && spinneroutput.getSelectedItem() == "Petawatt") {
                result = 1e-18 * amount;
                toasttext = "Milliwatt to Petawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Milliwatt" && spinneroutput.getSelectedItem() == "Milliwatt") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "Milliwatt" && spinneroutput.getSelectedItem() == "HP") {
                result = 1.3596e-6 * amount;
                toasttext = "Milliwatt to HP" + result.toString();
                editText.setText(toasttext);
            }

            //applying conditions from = HP
            if (spinnerinput.getSelectedItem() == "HP" && spinneroutput.getSelectedItem() == "Watt") {
                result = 735.4987 * amount;
                toasttext = "HP to Watt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "HP" && spinneroutput.getSelectedItem() == "Kilowatt") {
                result = 0.7354987 * amount;
                toasttext = "HP to Kilowatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "HP" && spinneroutput.getSelectedItem() == "Megawatt") {
                result = 7.35498e-4 * amount;
                toasttext = "HP to Megawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "HP" && spinneroutput.getSelectedItem() == "Gigawatt") {
                result = 7.35498e-7 * amount;
                toasttext = "HP to Gigawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "HP" && spinneroutput.getSelectedItem() == "Terawatt") {
                result = 7.35498e-10 * amount;
                toasttext = "HP to Terawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "HP" && spinneroutput.getSelectedItem() == "Petawatt") {
                result = 7.35498e-13 * amount;
                toasttext = "HP to Petawatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "HP" && spinneroutput.getSelectedItem() == "Milliwatt") {
                result = 735498.75 * amount;
                toasttext = "HP to Milliwatt " + result.toString();
                editText.setText(toasttext);
            }
            if (spinnerinput.getSelectedItem() == "HP" && spinneroutput.getSelectedItem() == "HP") {
                toasttext = "Error 2X00f_00#You!";
                editText.setText(toasttext);
            }
        } catch (Exception e) {
            String messege = "Input Field is Empty";
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
