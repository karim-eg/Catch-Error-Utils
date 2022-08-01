package com.EnceptCode.CatchErrorUtils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DisplayErrorActivity extends AppCompatActivity {

    private final String[] exceptionTypes = {
            "StringIndexOutOfBoundsException",
            "IndexOutOfBoundsException",
            "ArithmeticException",
            "NumberFormatException",
            "ActivityNotFoundException"
    };

    private final String[] exceptionMessages = {
            "Invalid string operation\n",
            "Invalid list operation\n",
            "Invalid arithmetical operation\n",
            "Invalid toNumber block operation\n",
            "Invalid intent operation"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_error);


        Intent intent = getIntent();
        String err;
        String error_msg = "";

        if (intent != null) {

            err = intent.getStringExtra("Error_message");

            String[] split = err.split("\n");
            try {

                for (int j = 0; j < exceptionTypes.length; j++) {

                    if (split[0].contains(exceptionTypes[j])) {
                        error_msg = exceptionMessages[j];

                        int addIndex = split[0].indexOf(exceptionTypes[j]) + exceptionTypes[j].length();

                        error_msg += split[0].substring(addIndex);

                        error_msg += "\n\nError message:\n" + err;
                        break;
                    }
                }

                if (error_msg.isEmpty()) {
                    error_msg = err;
                }

            } catch (Exception e) {
                error_msg = error_msg + "\n\nError: " + Log.getStackTraceString(e);
            }

            TextView textView1 = findViewById(R.id.textView1);
            textView1.setText(error_msg);
        }

    }
}