package com.aditya.scientificcalculator;
/*
we need a third party library so that we can evaluate the expression entered by the user because java cannot evauate the expression on its own as the expression is in the String format
the library that i used is mXparser I've downloaded the library from mathParser.org
step 1 - download the library from mathParser.org
step 2 - check which version of jdk you are using
step 3 - now copy the jdk version
step 4 - go to project menue and convert the project bar from android symbol to project symbol
step 5 - paste the copied file in the libs folder that is present under build folder
step 6 - now right click on the newly copied file and add the file as a library to your project


in order to use gif images in android projects you have to add 
step 1:- Add the lines below in build.gradel Project file
```buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral() **
    }
    dependencies {
        classpath "com.android.tools.build:gradle:4.1.1"

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral() **
    }
}
```

step 2:- Add the dependency in you build.gradle Application file
```` 

implementation 'pl.droidsonroids.gif:android-gif-drawable:1.2.20'

````

step 2:- to use gif images in your activity xml files just type the following code:-
```<pl.droidsonroids.gif.GifImageView
        android:id="@+id/gifImageView"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:src="@drawable/loading_file"
        app:layout_constraintBottom_toTopOf="@+id/SplashprogressBar"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
        and you are good to go
```
 */

// import androidx.appcompat.widget.Toolbar; make sure to import this and not import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    String answer;
    Toolbar toolbar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        toolbar= findViewById(R.id.toolbarCalculator);
        setSupportActionBar(toolbar);
        editText.setShowSoftInputOnFocus(false); //this will disable the keyboard pop up of the device
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString()=="Input"){
                    editText.setText(""); //clear the input field is the hint input is there when the user taps on it
                }
            }
        });

    }// on create closed


    //this function will allow us to set up menue item on the app title bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        //onCreateOptionsMenu(Menu menu object)
        //menuInflater.inflate(R.menu.menue xml file name, menu object);
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //this function is used to listen for click
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        //item.getItemId() will get the item id selected by the user
        switch (item.getItemId()){
            case R.id.quad_eque:
                Log.i("item","quad_eque selected");
                Intent intent = new Intent(MainActivity.this,quadActivity.class);
                startActivity(intent);
                //return true; works same as a break statement it prevents the fall through of the switch case
                return true;
            case R.id.unit_converter:
                Log.i("item","unit_converter selected");
                Intent intent2 = new Intent(MainActivity.this,UnitConverterActivity.class);
                startActivity(intent2);
                //return true; works same as a break statement it prevents the fall through of the switch case
                return true;
            case R.id.times_table:
                Log.i("item","times_table selected");
                Intent intent3 = new Intent(MainActivity.this,TimesTableActivity.class);
                startActivity(intent3);
                //return true; works same as a break statement it prevents the fall through of the switch case
                return true;
            case R.id.dev_info:
                Log.i("item","Developer info selected");
                Intent intent4 = new Intent(MainActivity.this,DevActivity.class);
                startActivity(intent4);
                return true;
            default:
                return false;
        }
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

     public void sevenButton(View view)
     {
         //now here we will use the update text function that we just created to update the text in our editText
         updateEditText("7");
     }
    public void eightButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("8");
    }
    public void nineButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("9");
    }
    public void divideButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("/");
    }
    public void fourButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("4");
    }
    public void fiveButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("5");
    }
    public void sixButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("6");
    }
    public void multiplyButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("X");
    }
    public void oneButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("1");
    }
    public void twoButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("2");
    }
    public void threeButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("3");
    }
    public void subtractButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("-");
    }
    public void dotButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText(".");
    }
    public void zeroButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("0");
    }
    public void equalsButton(View view)
    {

        //here the actual calculation will take place
        String userExpression = editText.getText().toString();
        userExpression = userExpression.replaceAll("X","*");

        // now we will use mxParser to calculate the expression for us
        /*
        in order to that we are going to use Expression class within mxParser library
        expression.calculate() is used to calculate the expression using mxParser library
         */
        Expression expression = new Expression(userExpression);
        answer = String.valueOf(expression.calculate());
        //now after calculation we need to update the text in editText
        editText.setText(answer);

        //now we have to make sure to reposition the cursor to all the way to the end of the answer
        editText.setSelection(answer.length());

    }
    public void additionButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("+");
    }
    public void parenthesisButton(View view)
    {
      /*
      since we have only one Parenthesis button and not two . we have to determine wheather the parenthesis is closed or not
      first determine the position of the cursor in the editText field
       */
        int cursorPosition = editText.getSelectionStart();
        //now we want to track the number of open and closed parenthesis to the left side of the cursor
        int openParenthesis = 0;
        int closedParenthesis = 0;
        //now what we need is to get the length of the text in the editText
        int textLength = editText.getText().length();
/*
         i<cursorPosition because we want to see how many open and closed parenthesis are there to the left of the cursor
 */
        for(int i=0;i<cursorPosition;i++)
        {
            //here we need an if statement to check weather we have an open parenthesis or a closed parenthesis
            if(editText.getText().toString().substring(i,i+1).equals("(")){
                openParenthesis++;
            }
            if(editText.getText().toString().substring(i,i+1).equals(")")){
                closedParenthesis++;
            }
        }
        //so we will use this openParenthesis and closed Parenthesis to determine weather we should print '(' or ')' when we press the button
        /*
        editText.getText().toString().substring(textLength-1,textLength).equals("(")
        here we are first extracting the text from the editText field and then taking a substring length-1 to lentgth so that will give us only
        ont character
         */
        if(openParenthesis==closedParenthesis|| editText.getText().toString().substring(textLength-1,textLength).equals("("))
        {
            updateEditText("(");
            // now we have to increase the cursor's position
            editText.setSelection(cursorPosition+1);
        }
        else if(openParenthesis>closedParenthesis && !editText.getText().toString().substring(textLength-1,textLength).equals("("))
        {
            updateEditText(")");
            editText.setSelection(cursorPosition+1);
        }
    }
    public void clearButton(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        editText.setText("");
    }
    public void answerButton(View view) {
        if (answer==null) {
            editText.setText("0");
        } else {
            editText.setText(answer);
            //now we have to make sure to reposition the cursor to all the way to the end of the answer
            editText.setSelection(answer.length());
        }
    }
    public void backspaceButton(View view)
    {
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
    public void buttonPower(View view)
    {
        //now here we will use the update text function that we just created to update the text in our editText
        updateEditText("^");
    }

    }
