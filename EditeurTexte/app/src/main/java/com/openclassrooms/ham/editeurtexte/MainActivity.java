package com.openclassrooms.ham.editeurtexte;

import android.content.res.Resources;
import android.graphics.YuvImage;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Scroller;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView previewBox = null;
    EditText editionBox = null;

    class SmileyLoader implements Html.ImageGetter{
        @Override
        public Drawable getDrawable(String smileyName){
            Resources resource = getResources();
            Drawable result =  null;
            switch (smileyName){
                case "smile":
                    result = resource.getDrawable(R.drawable.smile);
                    break;
                case "big_smile":
                    result = resource.getDrawable(R.drawable.big_smile);
                    break;
                case "wink":
                    result = resource.getDrawable(R.drawable.wink);
                    break;
                default:
                    break;
            }
            result.setBounds(0, 0, result.getIntrinsicWidth(), result.getIntrinsicHeight());
            return result;
        }
    }


    public void updatePreview(int currentColor){
        try {
            Resources res = getResources();
            String text =  this.editionBox.getText().toString();
            text = "<font color=\"" + res.getColor(currentColor) + "\">" + text + "</font>";
            Spanned result = Html.fromHtml(text, new SmileyLoader(), null);
            previewBox.setText(result);
        }catch (Exception e){
            previewBox.setText("HTML error !!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int[] currentColor = {R.color.black};

        final Slider slider = (Slider) findViewById(R.id.slider);
        Button hideShowButton = (Button) findViewById(R.id.hide_show_button);
        this.editionBox = (EditText) findViewById(R.id.edition_box);
        this.previewBox = (TextView) findViewById(R.id.prev_box);
        previewBox.setMovementMethod(new ScrollingMovementMethod());

        Button bold = (Button) findViewById(R.id.button_bold);
        Button italic = (Button) findViewById(R.id.button_italic);
        Button underline = (Button) findViewById(R.id.button_underlined);

        RadioGroup colorGroup = (RadioGroup) findViewById(R.id.color_group);

        hideShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                boolean open = slider.toggle();
                if (open == Slider.OPEN){
                    b.setText(R.string.hide);
                }else{
                    b.setText(R.string.show);
                }
            }
        });


        editionBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
              updatePreview(currentColor[0]);
            }
        });
        editionBox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() != 0){
                    return false;
                }
                if(66 == keyCode){
                    int currentPos = ((EditText) v).getSelectionStart();
                    String oldText = ((EditText) v).getText().toString();
                    ((EditText) v).setText(((EditText) v).getText().insert(currentPos, "<br />"));
                    ((EditText) v).setSelection(currentPos + 6);
                    updatePreview(currentColor[0]);
                    return true;
                }
                return false;
            }
        });

        View.OnClickListener ocl_style = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int startSelection = editionBox.getSelectionStart();
                int endSelection = editionBox.getSelectionEnd();
                String text = editionBox.getText().toString();
                String tag = "";
                switch (v.getId()){
                    case R.id.button_bold:
                        tag = "b";
                        break;
                    case R.id.button_italic:
                        tag = "i";
                        break;
                    case R.id.button_underlined:
                        tag = "u";
                        break;
                }
                text = text.substring(0, startSelection) + "<" + tag + ">" +
                        text.substring(startSelection, endSelection) + "</" + tag + ">" +
                        text.substring(endSelection);
                editionBox.setText(text);
                editionBox.setSelection(endSelection);
                updatePreview(currentColor[0]);
            }
        };
        bold.setOnClickListener(ocl_style);
        italic.setOnClickListener(ocl_style);
        underline.setOnClickListener(ocl_style);

        colorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.color_black:
                        currentColor[0] = R.color.black;
                        break;
                    case R.id.color_blue:
                        currentColor[0] = R.color.blue;
                        break;
                    case R.id.color_red:
                        currentColor[0] = R.color.red;
                        break;
                }
                updatePreview(currentColor[0]);
            }
        });

        View.OnClickListener ocl_smiley = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int endSelection = editionBox.getSelectionEnd();
                String text = editionBox.getText().toString();
                String tag = "";
                switch (v.getId()){
                    case R.id.smiley_smile:
                        tag = "smile";
                        break;
                    case R.id.smiley_big_smile:
                        tag = "big_smile";
                        break;
                    case R.id.smiley_wink:
                        tag = "wink";
                        break;
                }
                text = text.substring(0, endSelection) + "<img src=\"" + tag + "\">" +
                        text.substring(endSelection);
                editionBox.setText(text);
                editionBox.setSelection(endSelection);
                updatePreview(currentColor[0]);
            }
        };
        View smile = (View) findViewById(R.id.smiley_smile);
        View big_smile = findViewById(R.id.smiley_big_smile);
        View wink = findViewById(R.id.smiley_wink);
        smile.setOnClickListener(ocl_smiley);
        big_smile.setOnClickListener(ocl_smiley);
        wink.setOnClickListener(ocl_smiley);
    }
}
