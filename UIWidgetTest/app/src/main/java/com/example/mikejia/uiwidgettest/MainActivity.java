package com.example.mikejia.uiwidgettest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final EditText editText;
        final ImageView imageView1;
        final ProgressBar progressBar;
//        final ImageView imageView2;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.edit_text);
        imageView1 = (ImageView) findViewById(R.id.image_view1);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
//        imageView2 = (ImageView) findViewById(R.id.image_view2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button1:
//                        String inputText = editText.getText().toString();
//                        Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();

//                        imageView1.setImageResource(R.drawable.img_1);


//                        if(progressBar.getVisibility() == View.GONE){
//                            progressBar.setVisibility(View.VISIBLE);
//                        }else {
//                            progressBar.setVisibility(View.GONE);
//                        }

                        int progress = progressBar.getProgress();
                        progress = progress + 10;
                        progressBar.setProgress(progress);
                        break;
                    default:
                        break;
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button2:
//                        String inputText = editText.getText().toString();
//                        Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
                        imageView1.setImageResource(R.drawable.img_2);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
