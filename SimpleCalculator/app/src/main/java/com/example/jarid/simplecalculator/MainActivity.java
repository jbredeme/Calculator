package com.example.jarid.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Programmatically add event listeners to button objects
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        // Get the number of children of the grid layout
        int count = gridLayout.getChildCount();

        ButtonClickHandler buttonClickHandler = new ButtonClickHandler();

        // Loop through grid elements
        for(int i = 0; i < count; i++) {
            View v = gridLayout.getChildAt(i);
            // If a child is a button, attach a listener
            if(v instanceof Button){
                v.setOnClickListener(buttonClickHandler);

            }

        }
    }


    private class ButtonClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            double output;
            TextView textOutput = (TextView)findViewById(R.id.textOutput);
            Button buttonClicked = (Button)v;


            if(v instanceof Button) {

                // Sets the output view to 0 on clear button click
                if(v.getId() == (R.id.buttonKeyClear)) {
                    textOutput.setText("0");

                } else if(v.getId() == R.id.buttonKeyEquals){
                    try {
                        output = CalcHelper.evaluate(textOutput.getText().toString());
                        textOutput.setText(Double.toString(output));

                    } catch(Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        textOutput.setText("0");

                    }

                } else if(textOutput.getText().length() > 0 &&
                        CalcHelper.isOperator(textOutput.getText().charAt(textOutput.getText().length() - 1)) &&
                        CalcHelper.isOperator(buttonClicked.getText().charAt(0))) {

                        Toast.makeText(getApplicationContext(), "Cannot preform math operations on math operators", Toast.LENGTH_SHORT).show();

                } else {
                    if(textOutput.getText().equals("0") && !(CalcHelper.isOperator(buttonClicked.getText().charAt(0)))){
                        textOutput.setText("");

                    }

                    textOutput.setText(textOutput.getText().toString() + buttonClicked.getText());

                }

            }

        }

    }

}
