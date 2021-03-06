package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.SyncStateContract;
import android.text.Layout;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class Main extends AppCompatActivity {

    TextView ttarih, motivasyon;
    FirebaseFirestore fstore; // bulut tabanl?? veritaban??na a??lamak i??in
    Button tyt, ayt,datepicker,show;
    FirebaseAuth mAut; //google ile i??lem yapma
    int saat,dakika;
    EditText etkinlik;
    String event;
    LinearLayout linearLayout;
    TextView text;
    DocumentReference df;

    public int say??;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        //tarih ekleme
        datepicker=(Button) findViewById(R.id.datepicker);

        fstore= FirebaseFirestore.getInstance(); //bulut veritaban?? ba??lant??s?? olu??turma
        mAut= FirebaseAuth.getInstance(); //kimlik do??rulama ekran?? ba??lant??s?? olu??turma

        etkinlik=(EditText) findViewById(R.id.etkinlik);
        show=(Button)findViewById(R.id.button2);
        text=(TextView) findViewById(R.id.textView7);

        Calendar calendar= Calendar.getInstance();
        int gun= calendar.get(Calendar.DAY_OF_MONTH);
        int ay= calendar.get(Calendar.MONTH);
        int yil= calendar.get(Calendar.YEAR);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser(); //veritaban?? ili??kisi

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if( !TextUtils.isEmpty(etkinlik.getText().toString())){ //edittextin bo?? olup olmad??????n?? kontrol etme

                    DatePickerDialog datePickerDialog=new DatePickerDialog(Main.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                            TimePickerDialog timePickerDialog=new TimePickerDialog(Main.this , new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    say??++;

                                    saat=hourOfDay;
                                    dakika=minute;

                                    Calendar calendar= Calendar.getInstance();
                                    calendar.set(0,0,0,saat,dakika);

                                    event=etkinlik.getText().toString();

                                    String[] textArray = {(dayOfMonth + "/" + month + "/" + year+" "+ DateFormat.format("HH:mm aa",calendar)+" "+event)};
                                    linearLayout=(LinearLayout)findViewById(R.id.lay2);


                                    for( int i = 0; i <textArray.length;i++)
                                    {
                                        Toast.makeText(Main.this, "Tarih:"+textArray[i], Toast.LENGTH_SHORT).show();
                                        //text.setText(textArray[i]);

                                        DocumentReference docRef= fstore.collection("users").document(user.getUid()); //user koleksiyonundan kullan??c?? idlerimi al??r

                                        docRef.update(""+say??,textArray[i]).addOnSuccessListener(Main.this,new OnSuccessListener<Void>() { //kullan??c?? verilerine random say?? ile kaydedilen tarihleri ekler
                                            @Override
                                            public void onSuccess(Void unused) {

                                                Toast.makeText(Main.this, "Y??kleme Ba??ar??l??", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    }

                                }


                            },24,0,true);

                            timePickerDialog.updateTime(saat,dakika);
                            timePickerDialog.show();

                        }
                    },gun,ay,yil);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()); //??imdiki zaman?? g??steriyor
                    datePickerDialog.show();
                } else {
                    Toast.makeText(Main.this,"L??tfen bo?? alan b??rakmay??n??z ",Toast.LENGTH_LONG).show(); //kay??t s??ras??nda bir hata ile kar????la????l??rsa bildirim mesaj?? verir
                }
            }

        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                df= fstore.collection("users").document(user.getUid()); //veri taban??nda motivasyons??zleri adl?? koleksiyonun 1 numaral?? belgesine eri??im
                df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();

                        if (document.exists()) {

                            ArrayList<String> dates = new ArrayList<String>();

                            for (int i = 1; i <3; i++) {

                                dates.add("\n"+document.get(""+i));
                                text.setText(""+dates);

                            }

                        }


                    }
                });
            }
        });



        //s??nav sayac??

        df= fstore.collection("users").document(user.getUid());
        df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {

                    String day=document.getString("G??n");
                    String month=document.getString("Ay");
                    String year=document.getString("Y??l");

                    Integer gun=Integer.parseInt(day);
                    Integer ay=Integer.parseInt(month);
                    Integer y??l=Integer.parseInt(year);

                    ttarih= (TextView) findViewById(R.id.textView);
                    Calendar today= Calendar.getInstance(); //bug??n??n tarihini olu??turma
                    Calendar sinav = Calendar.getInstance(); //s??nav tarihini  olu??turma
                    sinav.set(Calendar.YEAR,y??l); // s??navin y??l??
                    sinav.set(Calendar.MONTH,ay); // s??nav??n yap??laca???? ay 0-11 aras?? sad?????? i??in 1 ay eksik yaz??yoruz
                    sinav.set(Calendar.DAY_OF_MONTH,gun); // s??nav??n yap??laca???? g??n
                    long fark = sinav.getTimeInMillis() - today.getTimeInMillis();
                    long kalan = fark / (24 * 60 * 60 * 1000);
                    ttarih.setText(" "+kalan+" ");

                }
            }
        });


        //motivasyon s??z??  i??lemleri
        motivasyon= (TextView) findViewById(R.id.textView4);
        fstore= FirebaseFirestore.getInstance();
        readsoz();

        //net hesaplama

        tyt= (Button) findViewById(R.id.tyt);
        ayt= (Button) findViewById(R.id.ayt);

    }
    //net hesaplama
    public void hesapla(View v){ //onclick metodu ile iki butonu da bir s??n??fta kontrol edilebilinir

        Button cliked= (Button)v;

        if(cliked.getId()==R.id.tyt){
            Intent i= new Intent(Main.this,tyt.class); //tyt hesaplama sayfas??na y??nlendirir
            startActivity(i);
        }
      else if(cliked.getId()==R.id.ayt){
            Intent i= new Intent(Main.this,ayt.class); //ayt hesaplama sayfas??na y??nlendirir
            startActivity(i);
        }
    }



    // motivasyon s??zleri
    private void readsoz() {

       int number;
        Random random = new Random();
        number = random.nextInt(20) + 1;//+1 sebebi 1-100 aras??nda istemeniz 0-100 isterseniz 101 yazman??z yeterli.


        df= fstore.collection("motivasyonsozleri").document(""+number); //veri taban??nda motivasyons??zleri adl?? koleksiyonun 1 numaral?? belgesine eri??im
        df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document=task.getResult();
                if(document.exists()){

                    motivasyon.setText("" +document.getData() );
                }else {
                    Toast.makeText(Main.this,"Bir hata ile kar????la????ld??: " +task.getException(), Toast.LENGTH_LONG);

                }
            }
        });

    }

    // kullan??c?? ad??n??n yazd?????? k??s??m
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

       MenuInflater menuInflater= getMenuInflater();
       menuInflater.inflate(R.menu.menu,menu); //men?? klas??r??ndeki menu.xml dosyas??na ula??ma

         MenuItem item = menu.findItem(R.id.account); //account adl?? iteme ula??ma
         if (item.getTitle().equals("isim")) { // e??er itemin i??erisinde isim yaz??yorsa isim yaz??s??n?? ??u ??ekilde de??i??tir

             item.setTitle(user.getEmail()); // ki??inin mail adresi yazar

        } else {
            item.setTitle("isim");

        }
        return true;

    }


}
