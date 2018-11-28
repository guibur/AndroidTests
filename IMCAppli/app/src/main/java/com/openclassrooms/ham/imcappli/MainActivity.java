package com.openclassrooms.ham.imcappli;

import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button validateButton = null;
    private Button resetButton = null;
    private EditText weightView = null;
    private EditText sizeView = null;
    private CheckBox masterBox = null;
    private RadioGroup unitRadioGroup = null;
    private TextView resultField = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validateButton = (Button) findViewById(R.id.validate);
        resetButton = (Button) findViewById(R.id.reset);
        weightView = (EditText) findViewById(R.id.text_weight);
        sizeView = (EditText) findViewById(R.id.text_size);
        masterBox = (CheckBox) findViewById(R.id.master);
        unitRadioGroup = (RadioGroup) findViewById(R.id.unit_group);
        resultField = (TextView) findViewById(R.id.result);

        validateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                float weight = Float.parseFloat(weightView.getText().toString());
                float size = Float.parseFloat(sizeView.getText().toString());
                if (unitRadioGroup.getCheckedRadioButtonId() == R.id.unit_cm){
                    size /= 100;
                }

                if (weight > 0 && size > 0) {
                    float IMC = weight / (size * size);
                    if (masterBox.isChecked()){
                        String masterText = getResources().getString(R.string.master_msg);
                        resultField.setText(masterText + Float.toString(IMC));
                    }else {
                        resultField.setText(Float.toString(IMC));
                    }
                }else{
                    Toast.makeText(MainActivity.this, R.string.wrong_values, Toast.LENGTH_SHORT).show();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightView.getText().clear();
                sizeView.getText().clear();
                masterBox.setChecked(false);
                unitRadioGroup.check(R.id.unit_m);
                resultField.setText(R.string.when_no_results);
            }
        });

        weightView.addTextChangedListener(resetResult);
        sizeView.addTextChangedListener(resetResult);

        unitRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                resultField.setText(R.string.when_no_results);
            }
        });

        masterBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                resultField.setText(R.string.when_no_results);
            }
        });
    }

    private TextWatcher resetResult = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable editable){
            resultField.setText(R.string.when_no_results);
        }

        @Override
        public void beforeTextChanged(CharSequence charSeq, int arg1, int arg2, int arg3) {}

        @Override
        public void onTextChanged(CharSequence charSeq, int arg1, int arg2, int arg3) {}
    };

}
