package com.cbwit.interactivestoryapp.ui;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import com.cbwit.interactivestoryapp.R;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private EditText nameField;
    private Button startButton, choice1, choice2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         nameField = (EditText)findViewById(R.id.editFieldName);
         startButton= (Button)findViewById(R.id.button1);
        choice1=(Button) findViewById(R.id.choice1Button);
        choice2=(Button) findViewById(R.id.choice2Button);

        startButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    String name = nameField.getText().toString();
                    startStory(name);
               }
           }

        );



    }

    @Override
    protected void onResume() {
        super.onResume();
        nameField.setText("");
    }

    private void startStory(String name) {
        //intent objects are used to start another activity or handing something
        //off. It is for starting another task

        Intent intent = new Intent(this, StoryActivity.class);
        //use a resource for key name
        Resources resources = getResources();
        String key = resources.getString(R.string.key_name);
        intent.putExtra(key,name);
        startActivity(intent);
    }
}
