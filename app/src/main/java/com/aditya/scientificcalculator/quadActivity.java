package com.aditya.scientificcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class quadActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    double XSquare;
    double X;
    double Constant;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quad);
        toolbar = findViewById(R.id.toolbarQuadraticCalculator);
        setSupportActionBar(toolbar);
        //calling get support action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText = findViewById(R.id.editText2);
        editText.setShowSoftInputOnFocus(false); //this will disable the keyboard pop up of the device
        textView = findViewById(R.id.textView);
        //here we will write the code to make textView scrollable after putting
        //android:scrollbars="vertical" in the xml file of the application activity
        textView.setMovementMethod(new ScrollingMovementMethod());
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
        if (editText.getText().toString() == "Input") {
            editText.setText(""); //clear the input field is the hint input is there when the user taps on it
        } else {
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
        updateEditText("7");
    }

    public void eightButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("8");
    }

    public void nineButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("9");
    }

    public void xSquare(View view) {
        try {
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

        /*
        no we will check if the default hint is there in the editText or not
        if yes then set it to string to add
        if no then set it to editText.setText(String.format("%s%s%s",leftOldString,strToAdd,rightOldString));
         */
            if (editText.getText().toString() == "Input") {
                editText.setText(""); //clear the input field is the hint input is there when the user taps on it
            } else {
                //now we are going to update the editText with this new String
        /*
        editText.setText(String.format("%s%s%s",leftOldString,strToAdd,rightOldString)); will update the String in our edit Text
        according to our cursor's position
        String.format("%s%s%s",leftOldString,strToAdd,rightOldString) = tells the editText in which format it has to show the Strings
        first we have to print the leftString then we will print the String that we want to add at the cursor's position and then we will
        print the right string
         */
                textView.setText(String.format("%s%s\n", "X^2 = ", leftOldString));
                //setting editText to null
                editText.setText("");
            }

            //here add the logic for value to store in x square
            if (leftOldString == null) {
                XSquare = 0;
            } else {
                //here add the logic for value to store in x square
                String X_Square = leftOldString;
                XSquare = Double.parseDouble(X_Square);
                Log.i("xSquare", Double.toString(XSquare));
            }
        }
        catch (Exception e)
        {
            String messege="X^2 Field is NULL";
            textView.setText(messege);
        }
    }

    public void fourButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("4");
    }

    public void fiveButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("5");
    }

    public void sixButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("6");
    }

    public void X_Button(View view) {
        try {
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

        /*
        no we will check if the default hint is there in the editText or not
        if yes then set it to string to add
        if no then set it to editText.setText(String.format("%s%s%s",leftOldString,strToAdd,rightOldString));
         */
            if (editText.getText().toString() == "Input") {
                editText.setText(""); //clear the input field is the hint input is there when the user taps on it
            } else {
                //now we are going to update the editText with this new String
        /*
        editText.setText(String.format("%s%s%s",leftOldString,strToAdd,rightOldString)); will update the String in our edit Text
        according to our cursor's position
        String.format("%s%s%s",leftOldString,strToAdd,rightOldString) = tells the editText in which format it has to show the Strings
        first we have to print the leftString then we will print the String that we want to add at the cursor's position and then we will
        print the right string
         */
                textView.setText(String.format("%s%s\n", "X = ", leftOldString));
                //setting the editText to null
                editText.setText("");
            }

            //here add the logic for value to store in x square
            if (leftOldString == null) {
                X = 0;
            } else {
                String X_ = leftOldString;
                X = Double.parseDouble(X_);
                Log.i("x", Double.toString(X));
            }
        }
        catch (Exception e)
        {
            String messege="X Field is NULL";
            textView.setText(messege);
        }
    }

    public void oneButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("1");
    }

    public void twoButton(View view) {//now here we will use the update text function that we just created to update the text in our editText
        updateEditText("2");
    }

    public void threeButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("3");
    }

    public void ButtonConstant(View view) {
        try {
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

        /*
        no we will check if the default hint is there in the editText or not
        if yes then set it to string to add
        if no then set it to editText.setText(String.format("%s%s%s",leftOldString,strToAdd,rightOldString));
         */
            if (editText.getText().toString() == "Input") {
                editText.setText(""); //clear the input field is the hint input is there when the user taps on it
            } else {
                //now we are going to update the editText with this new String
        /*
        editText.setText(String.format("%s%s%s",leftOldString,strToAdd,rightOldString)); will update the String in our edit Text
        according to our cursor's position
        String.format("%s%s%s",leftOldString,strToAdd,rightOldString) = tells the editText in which format it has to show the Strings
        first we have to print the leftString then we will print the String that we want to add at the cursor's position and then we will
        print the right string
         */
                textView.setText(String.format("%s%s\n", "X = ", leftOldString));
                //setting the editText to null
                editText.setText("");
            }

            if (leftOldString == null) {
                Constant = 0;
            } else {
                //here add the logic for value to store in x square
                String C = leftOldString;
                Constant = Double.parseDouble(C);
                Log.i("Constant", Double.toString(Constant));
            }
        }
        catch (Exception e)
        {
            String messege="Constant Field is NULL";
            textView.setText(messege);
        }

    }

    public void dotButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText(".");
    }

    public void zeroButton(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("0");
    }

    public void equalsButton(View view) {
        try {
            Double doublea = XSquare;
            Double doubleb = X;
            Double doublec = Constant;
            Double doubleDiscriminant = (doubleb * doubleb) - (4 * doublea * doublec);
            if (doubleDiscriminant > 0) {
                Double doubleroot1 = (-doubleb + Math.sqrt(doubleDiscriminant)) / (2 * doublea);
                Double doubleroot2 = (-doubleb - Math.sqrt(doubleDiscriminant)) / (2 * doublea);
                String toasttext = "Root1 = " + doubleroot1.toString() + " , " + "Root2 = " + doubleroot2.toString();
                textView.setText(toasttext);
                editText.setText("");
            } else if (doubleDiscriminant == 0) {
                Double doubleroot1 = (-doubleb) / (2 * doublea);
                String toasttext = "Root1 = " + doubleroot1.toString() + " , " + "Root2 = " + doubleroot1.toString();
                textView.setText(toasttext);
                editText.setText("");
            } else {
                Double real = -doubleb / (2 * doublea);
                Double imagPart = Math.sqrt(-doubleDiscriminant) / (2 * doublea);
                String doubleroot1 = "root1 = " + real + " + " + " i " + imagPart + " , " + " root 2 = " + real + " - " + " i " + imagPart;
                textView.setText(doubleroot1);
                editText.setText("");
            }
        }
        catch (Exception e)
        {
            String messege="Input Field is NULL";
            textView.setText(messege);
        }
    }

    public void clearButton(View view) {
        editText.setText("");
    }

    public void buttonMinus(View view) {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("-");
    }
}