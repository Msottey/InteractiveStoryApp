package com.cbwit.interactivestoryapp.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cbwit.interactivestoryapp.R;
import com.cbwit.interactivestoryapp.model.Page;
import com.cbwit.interactivestoryapp.model.Story;

import java.util.Stack;


public class StoryActivity extends AppCompatActivity {
 public static final String  TAG = StoryActivity.class.getSimpleName();

    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button, choice2Button;
    private String name;
    private Stack<Integer> pageStack =  new Stack<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView = (ImageView) findViewById(R.id.storyImageView);
        storyTextView = (TextView) findViewById((R.id.storyTextView));
        choice2Button = (Button)findViewById(R.id.choice2Button);
        choice1Button = (Button)findViewById(R.id.choice1Button);
        Intent intent = getIntent();
        //returns based on key name
         name = intent.getStringExtra(getString(R.string.key_name));
        //check if nothing is passed for various reasons
        if(name== null || name.isEmpty()){
            name= "Friend";
        }
        Log.d(TAG,name);

        story = new Story();
        loadPage(0);

    }

    private void loadPage(int pageNumber) {



        final Page page = story.getPage(pageNumber);
//set image
        Drawable image = ContextCompat.getDrawable(this,page.getImageID());
        storyImageView.setImageDrawable(image);

        String pageText = getString(page.getTextID());
        //add the name if the placeholder is included
        pageText = String.format(pageText,name);
        //set text view with modified text

        storyTextView.setText(pageText);
        if(page.isFinalPage()){
            choice1Button.setVisibility(View.INVISIBLE);
            choice2Button.setText(R.string.play_again_button_text);
            choice2Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {loadPage(0);
                }
            });

        }
        else{
            loadbuttons(page);

        }



    }

    private void loadbuttons(final Page page) {
        choice1Button.setVisibility(View.VISIBLE);
        choice2Button.setVisibility(View.VISIBLE);
        choice1Button.setText(page.getChoice1().getTextID());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });
        choice2Button.setText(page.getChoice2().getTextID());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }

    @Override
    public void onBackPressed() {
        pageStack.pop();
        if(pageStack.isEmpty()){
            super.onBackPressed();
        }
        else{
            //load the previous page

            loadPage(pageStack.pop());
        }



    }
}
