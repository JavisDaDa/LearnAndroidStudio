package com.example.mikejia.uiwidgettest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private ImageView imageView1;
    private ProgressBar progressBar;
    //        private ImageView imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        editText = (EditText) findViewById(R.id.edit_text);
        imageView1 = (ImageView) findViewById(R.id.image_view1);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
//        imageView2 = (ImageView) findViewById(R.id.image_view2);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:

                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
                default:
                    break;
//                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
//                dialog.setTitle("This is Dialog");
//                dialog.setMessage("Something important");
//                dialog.setCancelable(false);
//                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//                dialog.show();
//                break;
//            default:
//                break;


//                        String inputText = editText.getText().toString();
//                        Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();

//                        imageView1.setImageResource(R.drawable.img_1);


//                        if(progressBar.getVisibility() == View.GONE){
//                            progressBar.setVisibility(View.VISIBLE);
//                        }else {
//                            progressBar.setVisibility(View.GONE);
//                        }

//                int progress = progressBar.getProgress();
//                progress = progress + 10;
//                progressBar.setProgress(progress);


//            case R.id.button2:
////                        String inputText = editText.getText().toString();
////                        Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
//                imageView1.setImageResource(R.drawable.img_2);
//                break;
//                default:
//                    break;



        }
    }
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()){
//                    case R.id.button1:
////                        String inputText = editText.getText().toString();
////                        Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
//
////                        imageView1.setImageResource(R.drawable.img_1);
//
//
////                        if(progressBar.getVisibility() == View.GONE){
////                            progressBar.setVisibility(View.VISIBLE);
////                        }else {
////                            progressBar.setVisibility(View.GONE);
////                        }
//
//                        int progress = progressBar.getProgress();
//                        progress = progress + 10;
//                        progressBar.setProgress(progress);
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.button2:
////                        String inputText = editText.getText().toString();
////                        Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
//                        imageView1.setImageResource(R.drawable.img_2);
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
}
