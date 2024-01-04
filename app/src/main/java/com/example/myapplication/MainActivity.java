 package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.util.Scanner;

 public class MainActivity   extends AppCompatActivity  {
    private ActivityMainBinding binding;
    private String set = "";
    private String s1 = "";
    private Scanner scanner1;
    private Scanner scanner2;
    private String s2 = "";
    private String sacin = "";
    private int i;
    private String sbcin = "";
    private final static String Namefile1 = "A.txt";
     private final static String Namefile2 = "B.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.button.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                s1 = binding.textView.getText().toString();
                FileInputStream fin1 = null;
                FileInputStream fin2 = null;
                FileOutputStream fos1 = null;
                FileOutputStream fos2 = null;
                try {
                    sacin = binding.edittext1.getText().toString();
                    sbcin = binding.edittext2.getText().toString();
                    fos1 = openFileOutput(Namefile1, MODE_PRIVATE);
                    fos2 = openFileOutput(Namefile2, MODE_PRIVATE);
                    fos1.write(sacin.getBytes());
                    fos2.write(sbcin.getBytes());
                }
                catch(IOException ex) {


                }



                try {
                    fin1 = openFileInput(Namefile1);
                    byte[] bytes1 = new byte[fin1.available()];
                    fin1.read(bytes1);
                    String text1 = new String (bytes1);
                    fin2 = openFileInput(Namefile2);
                    byte[] bytes2 = new byte[fin2.available()];
                    fin2.read(bytes2);
                    String text2 = new String (bytes2);
                    int extremum = Math.min(text1.length(), text2.length());

                    for (int i = 0; i < extremum; i++) {
                        if (text1.charAt(i) != text2.charAt(i)) {
                            set = set + text1.charAt(i) + "!=" + text2.charAt(i) + "  ";
                        }
                        else {
                            set = set + text1.charAt(i) + "==" + text2.charAt(i) + "  ";
                        }
                    }

                    if (text1.length() < text2.length()) {
                        for (int i = extremum; i < text2.length(); i++) {
                            set = set + text2.charAt(i) + "  ";
                        }
                    }
                    else {
                        for (int i = extremum; i < text1.length(); i++) {
                            set = set + text1.charAt(i) + "  ";
                        }
                    }
                    binding.textView.setText(set);




                }
                catch (IOException e) {
                    binding.textView.setText(e.getMessage());
                }








            }
        });
    }
}