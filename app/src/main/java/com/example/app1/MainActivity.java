package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ImageView loginTap;
    private TextView mTextView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.logo);
        //loginTap = (ImageView) findViewById(R.id.blurScreen);

        button= (Button) findViewById(R.id.button);
        mTextView = findViewById(R.id.nombreEquipo);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin_Sign();
                YoYo.with(Techniques.RubberBand).duration(700).repeat(1).playOn(mTextView);
                YoYo.with(Techniques.RubberBand).duration(700).repeat(1).playOn(mImageView);
            }
        });

        //loginTap.setClickable(true);

        /*loginTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.RubberBand).duration(700).repeat(1).playOn(mImageView);
                openLogin_Sign();
            }
        });*/
    }

    public void openLogin_Sign(){
        Intent intent = new Intent(this, Login_Sign.class);
        startActivity(intent);
    }
}
