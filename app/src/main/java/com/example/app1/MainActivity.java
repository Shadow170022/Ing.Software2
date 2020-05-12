package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    private ImageView loginTap;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = findViewById(R.id.logo);

        loginTap = findViewById(R.id.tapScreen);

        loginTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.RubberBand).duration(700).repeat(1).playOn(mImageView);
                openLogin_Sign();
            }
        });
    }

    public void openLogin_Sign(){
        Intent intent = new Intent(this, Login_Sign.class);
        startActivity(intent);
    }
}
