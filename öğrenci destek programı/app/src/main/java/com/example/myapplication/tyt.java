package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;

public class tyt extends AppCompatActivity {
    Button hesapla;
    EditText turkced,turkcey,fend,feny,matd,maty,sosyald,sosyaly;
    TextView sonuc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tyt);

        hesapla=(Button) findViewById(R.id.hesapla);
        sonuc= (TextView)findViewById(R.id.sonuc) ;

        turkced=(EditText)findViewById(R.id.turkcedogru);
        turkcey=(EditText) findViewById(R.id.turkceyanlis);
        fend=(EditText) findViewById(R.id.fendogru);
        feny=(EditText) findViewById(R.id.fenyanlis);
        matd=(EditText) findViewById(R.id.matdogru);
        maty=(EditText) findViewById(R.id.matyanlis);
        sosyald=(EditText) findViewById(R.id.sosyaldogru);
        sosyaly=(EditText) findViewById(R.id.sosyalyanlis);


        hesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(
                        !TextUtils.isEmpty(turkced.getText().toString())&&
                        !TextUtils.isEmpty(turkcey.getText().toString())&&
                        !TextUtils.isEmpty(fend.getText().toString())&&
                        !TextUtils.isEmpty(feny.getText().toString())&&
                        !TextUtils.isEmpty(matd.getText().toString())&&
                        !TextUtils.isEmpty(maty.getText().toString())&&
                        !TextUtils.isEmpty(sosyald.getText().toString())&&
                        !TextUtils.isEmpty(sosyaly.getText().toString())
                ){

                    sonuc.setText(
                            "Türkçe sonucun:"  +(td(turkced)-(ty(turkcey)*0.25))+
                             "\n"+" Fen Bilgisi sonucun:"+(fd(fend)-(fy(feny)*0.25))+
                              "\n"+"Temel Matematik sonucun:"+(md(matd)-(my(maty)*0.25))+
                              "\n"+"Sosyal Bilgiler sonucun:"+(sd(sosyald)-(sy(sosyaly)*0.25)));

                }  else {

                    Toast.makeText(tyt.this,"Lütfen tüm alanları doldurunuz ",Toast.LENGTH_LONG).show();
                }

               if(
                        (td(turkced)+ty(turkcey)<40)||
                        (fd(fend)+fy(feny)<20)||
                        (md(matd)+my(maty)<40)||
                        (sd(sosyald)+sy(sosyaly)<20)
                ){
                    Toast.makeText(tyt.this,"Lütfen sınav soruları toplamınadn yüksek bir değer girmeyiniz ",Toast.LENGTH_LONG).show();

                }



            }
        });

    }

//edittexten alınan verileri float formatına çevirme
    float td(EditText turkced) {
        try {
            return NumberFormat.getInstance().parse(turkced.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float ty(EditText turkcey) {
        try {
            return NumberFormat.getInstance().parse(turkcey.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float fd(EditText fend) {
        try {
            return NumberFormat.getInstance().parse(fend.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float fy(EditText feny) {
        try {
            return NumberFormat.getInstance().parse(feny.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float md(EditText matd) {
        try {
            return NumberFormat.getInstance().parse(matd.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float my(EditText maty) {
        try {
            return NumberFormat.getInstance().parse(maty.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float sd(EditText sosyald) {
        try {
            return NumberFormat.getInstance().parse(sosyald.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float sy(EditText sosyaly) {
        try {
            return NumberFormat.getInstance().parse(sosyaly.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }

}