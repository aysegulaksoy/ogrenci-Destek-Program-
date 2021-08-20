package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;

public class ayt extends AppCompatActivity {

    Button hesapla;
    EditText edebiyatd,edebiyaty,tarih1d,tarih1y,cografya1d,cografya1y,tarih2d,tarih2y,cografya2d,cografya2y;
    EditText felsefed,felsefey,dind,diny,matd,maty,fizikd,fiziky,kimyad,kimyay,biyolojid,biyolojiy,dild,dily;
    TextView sonuc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayt);

       hesapla=(Button) findViewById(R.id.hesapla);
        sonuc= (TextView)findViewById(R.id.sonuc) ;

        edebiyatd=(EditText) findViewById(R.id.edebiyatdogru);
        edebiyaty=(EditText) findViewById(R.id.edebiyatyanlis);
        tarih1d=(EditText) findViewById(R.id.tarih1dogru);
        tarih1y=(EditText) findViewById(R.id.tarih1yanlis);
        cografya1d=(EditText) findViewById(R.id.corafya1dogru);
        cografya1y=(EditText) findViewById(R.id.cografya1yanlis);
        tarih2d=(EditText) findViewById(R.id.tarih2dogru);
        tarih2y=(EditText) findViewById(R.id.tarih2yanlis);
        cografya2d=(EditText) findViewById(R.id.cografya2dogru);
        cografya2y=(EditText) findViewById(R.id.cografya2yanlis);
        felsefed=(EditText) findViewById(R.id.felsefedogru);
        felsefey=(EditText) findViewById(R.id.felsefeyanlis);
        dind=(EditText) findViewById(R.id.dindogru);
        diny=(EditText) findViewById(R.id.dinyanlis);
        matd=(EditText) findViewById(R.id.matdogru);
        maty=(EditText) findViewById(R.id.matyanlis);
        fizikd=(EditText) findViewById(R.id.fizikdogru);
        fiziky=(EditText) findViewById(R.id.fizikyanlis);
        kimyad=(EditText) findViewById(R.id.kimyadogru);
        kimyay=(EditText) findViewById(R.id.kimyayanlis);
        biyolojid=(EditText) findViewById(R.id.biyolojidogru);
        biyolojiy=(EditText) findViewById(R.id.biyolojiyanlis);
        dild=(EditText) findViewById(R.id.dildogru);
        dily=(EditText) findViewById(R.id.dilyanlis);



     hesapla.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {



              if(     !TextUtils.isEmpty(edebiyatd.getText().toString())&&!TextUtils.isEmpty(edebiyaty.getText().toString())&&
                      !TextUtils.isEmpty(tarih1d.getText().toString())&&!TextUtils.isEmpty(tarih1y.getText().toString())&&
                      !TextUtils.isEmpty(cografya1d.getText().toString())&&!TextUtils.isEmpty(cografya1y.getText().toString())&&
                      !TextUtils.isEmpty(tarih2d.getText().toString())&&!TextUtils.isEmpty(tarih2y.getText().toString())&&
                      !TextUtils.isEmpty(cografya2d.getText().toString())&&!TextUtils.isEmpty(cografya2y.getText().toString())&&
                      !TextUtils.isEmpty(felsefed.getText().toString())&&!TextUtils.isEmpty(felsefey.getText().toString())&&
                      !TextUtils.isEmpty(dind.getText().toString())&&!TextUtils.isEmpty(diny.getText().toString())&&
                      !TextUtils.isEmpty(matd.getText().toString())&&!TextUtils.isEmpty(maty.getText().toString())&&
                      !TextUtils.isEmpty(fizikd.getText().toString())&&!TextUtils.isEmpty(fiziky.getText().toString())&&
                      !TextUtils.isEmpty(kimyad.getText().toString())&&!TextUtils.isEmpty(kimyay.getText().toString())&&
                      !TextUtils.isEmpty(biyolojid.getText().toString())&&!TextUtils.isEmpty(biyolojiy.getText().toString())&&
                      !TextUtils.isEmpty(dild.getText().toString())&&!TextUtils.isEmpty(dily.getText().toString()))
              {
                  sonuc.setText(
                          "Türk dili ve Edebiyatı sonucun:"+(ed(edebiyatd)-(ey(edebiyatd)*0.25))+
                          "\n"+"Tarih-1 sonucun:"+(t1d(tarih1d)-(t1y(tarih1y)*0.25))+
                          "\n"+"Coğrafya-1 sonucun:"+(c1d(cografya1d)-(c1y(cografya1y)*0.25))+
                          "\n"+"Tarih-2 sonucun:"+(t2d(tarih2d)-(t2y(tarih2y)*0.25))+
                          "\n"+"Coğrafya-2 sonucun:"+(c2d(cografya2d)-(c2y(cografya2y)*0.25))+
                          "\n"+"Felsefe Grubu sonucun:"+(fd(felsefed)-(fy(felsefey)*0.25))+
                          "\n"+"Din Kültürü sonucun:"+(dd(dind)-(dy(diny)*0.25))+
                          "\n"+"Matematik sonucun:"+(md(matd)-(my(maty)*0.25))+
                          "\n"+"Fizik sonucun:"+(fid(fizikd)-(fiy(fiziky)*0.25))+
                          "\n"+"Kimya sonucun:"+(kd(kimyad)-(ky(kimyay)*0.25))+
                          "\n"+"Biyoloji sonucun:"+(bd(biyolojid)-(by(biyolojiy)*0.25))+
                          "\n"+"Dil sonucun:"+(did(dild)-(diy(dily)*0.25)));

              }else {

                     Toast.makeText(ayt.this,"Lütfen tüm alanları doldurunuz ",Toast.LENGTH_LONG).show();
              }
              if(
                      (ed(edebiyatd)+ey(edebiyaty)<40)||
                              (t1d(tarih1d)+t1y(tarih1y)<10)||
                              (c1d(cografya1d)+c1y(cografya1y)<6)||
                              (t2d(tarih2d)+t2y(tarih2y)<11)||
                              (c2d(cografya2d)+c2y(cografya2y)<11)||
                              (fd(felsefed)+fy(felsefey)<12)||
                              (dd(dind)+dy(diny)<6)||
                              (md(matd)+my(maty)<40)||
                              (fid(fizikd)+fiy(fiziky)<14)||
                              (kd(kimyad)+ky(kimyay)<13)||
                              (bd(biyolojid)+by(biyolojiy)<13)||
                              (did(dild)+diy(dily)<80)

              ){
                  Toast.makeText(ayt.this,"Lütfen sınav soruları toplamınadn yüksek bir değer girmeyiniz ",Toast.LENGTH_LONG).show();

              }


          }
      });




    }

    //edittexten alınan verileri float formatına çevirme
    float ed(EditText edebiyatd) {
        try {
            return NumberFormat.getInstance().parse(edebiyatd.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float ey(EditText edebiyaty) {
        try {
            return NumberFormat.getInstance().parse(edebiyaty.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float t1d(EditText tarih1d) {
        try {
            return NumberFormat.getInstance().parse(tarih1d.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float t1y(EditText tarih1y) {
        try {
            return NumberFormat.getInstance().parse(tarih1y.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float c1d(EditText cografya1d) {
        try {
            return NumberFormat.getInstance().parse(cografya1d.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float c1y(EditText cografya1y) {
        try {
            return NumberFormat.getInstance().parse(cografya1y.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float t2d(EditText tarih2d) {
        try {
            return NumberFormat.getInstance().parse(tarih2d.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float t2y(EditText tarih2y) {
        try {
            return NumberFormat.getInstance().parse(tarih2y.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    //edittexten alınan verileri float formatına çevirme
    float c2d(EditText cografya2d) {
        try {
            return NumberFormat.getInstance().parse(cografya2d.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float c2y(EditText cografya2y) {
        try {
            return NumberFormat.getInstance().parse(cografya2y.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float fd(EditText felsefed) {
        try {
            return NumberFormat.getInstance().parse(felsefed.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float fy(EditText felsefey) {
        try {
            return NumberFormat.getInstance().parse(felsefey.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float dd(EditText dind) {
        try {
            return NumberFormat.getInstance().parse(dind.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float dy(EditText diny) {
        try {
            return NumberFormat.getInstance().parse(diny.getText().toString()).floatValue();
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
    //edittexten alınan verileri float formatına çevirme
    float fid(EditText fizikd) {
        try {
            return NumberFormat.getInstance().parse(fizikd.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float fiy(EditText fiziky) {
        try {
            return NumberFormat.getInstance().parse(fiziky.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float kd(EditText kimyad) {
        try {
            return NumberFormat.getInstance().parse(kimyad.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float ky(EditText kimyay) {
        try {
            return NumberFormat.getInstance().parse(kimyay.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float bd(EditText biyolojid) {
        try {
            return NumberFormat.getInstance().parse(biyolojid.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float by(EditText biyolojiy) {
        try {
            return NumberFormat.getInstance().parse(biyolojiy.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float did(EditText dild) {
        try {
            return NumberFormat.getInstance().parse(dild.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
    float diy(EditText dily) {
        try {
            return NumberFormat.getInstance().parse(dily.getText().toString()).floatValue();
        } catch (ParseException e) {
            return 0.0f;
        }
    }
}