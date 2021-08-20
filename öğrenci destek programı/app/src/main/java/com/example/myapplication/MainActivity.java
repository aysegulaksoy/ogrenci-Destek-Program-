package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.TextUtilsCompat;

import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText name, surname, email, password,gun,ay,yıl;
    String txtname, txtsurname,txtmail, txtpassword,tgun,tyıl,tay;
    FirebaseAuth mAut; //google ile işlem yapma
    FirebaseFirestore fstore; // bulut tabanlı veritabanına ağlamak için
    DocumentReference df;
    Button admin;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText) findViewById(R.id.name);
        surname=(EditText) findViewById(R.id.surname);
        email= (EditText) findViewById(R.id.editTextTextEmailAddress);
        password=(EditText) findViewById(R.id.editTextTextPassword);
        gun=(EditText) findViewById(R.id.gun);
        ay=(EditText) findViewById(R.id.ay);
        yıl=(EditText) findViewById(R.id.yıl);
        admin=(Button) findViewById(R.id.admin);


        fstore= FirebaseFirestore.getInstance(); //bulut veritabanı bağlantısı oluşturma
        mAut= FirebaseAuth.getInstance(); //kimlik doğrulama ekranı bağlantısı oluşturma

        //firebasee gitme
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Object http;
                Uri firebase=Uri.parse("https://console.firebase.google.com/u/0/project/users-84a02/firestore/data/~2Fusers~2FEV1Ja2EUcqceKjbNP2DcR6SL3Vr1"); //butona vermek istediğimiz linki buraya yazıyoruz.
                Intent i =new Intent(Intent.ACTION_VIEW,firebase);
                startActivity(i);
            }
        });

    }
    public void save(View v){

        //edittext verilerini stringe çevirme
        txtmail= email.getText().toString();
        txtpassword= password.getText().toString();
        txtname= name.getText().toString();
        txtsurname= surname.getText().toString();
        tgun= gun.getText().toString();
        tyıl= yıl.getText().toString();
        tay= ay.getText().toString();


        if(!TextUtils.isEmpty(txtname)&& !TextUtils.isEmpty(txtsurname) &&!TextUtils.isEmpty(txtmail)&& !TextUtils.isEmpty(txtpassword)) { //edittextlerin boş olup olmadığını kontrol etme

            mAut.createUserWithEmailAndPassword(txtmail, txtpassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {  //mail ve şifre ile bir veritabanı oluşturma
                @Override
                public void onSuccess(AuthResult authResult) {


                    FirebaseUser user = mAut.getCurrentUser();

                    Toast.makeText(MainActivity.this, "başarılı", Toast.LENGTH_LONG).show(); //kayıt işlemi başarılı ise bildirim gönderir

                    df = fstore.collection("users").document(user.getUid()); //veritabanında users adında bir alan oluşturur

                    Map<String, Object> userinfo = new HashMap<>(); // verilerin users alanına kaydedilebilmesi için gereklidir
                    userinfo.put("Ad", txtname); //veri tabanında ad adında bir alan oluşturup txtname edittextine yazılanı kaydeder
                    userinfo.put("Soyad", txtsurname);//veri tabanında soyad adında bir alan oluşturup txtsurname edittextine yazılanı kaydeder
                    userinfo.put("Mail", txtmail);//veri tabanında email adında bir alan oluşturup txtmail edittextine yazılanı kaydeder
                    userinfo.put("Şifre", txtpassword);//veri tabanında şifre adında bir alan oluşturup txtpassword edittextine yazılanı kaydeder
                    userinfo.put("Gün", tgun);//veri tabanında gün adında bir alan oluşturup txtpassword edittextine yazılanı kaydeder
                    userinfo.put("Ay", tay);//veri tabanında ay adında bir alan oluşturup txtpassword edittextine yazılanı kaydeder
                    userinfo.put("Yıl", tyıl);//veri tabanında yıl adında bir alan oluşturup txtpassword edittextine yazılanı kaydeder
                    df.set(userinfo);

                    Intent i = new Intent(getApplicationContext(), giris.class); //giris ekranına yönlendirir
                    startActivity(i);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(MainActivity.this, "bir hata ile karşılaşıldı ", Toast.LENGTH_LONG).show(); //kayıt sırasında bir hata ile karşılaşılırsa bildirim mesajı verir

                }
            });


       }
        else{
            Toast.makeText(MainActivity.this, "Lütfen tüm alanları doldurunuz", Toast.LENGTH_LONG).show(); //kayıt sırasında bir hata ile karşılaşılırsa bildirim mesajı verir

        }

    }

    public void giris(View v){

        Intent i= new Intent(getApplicationContext(),giris.class); //giris ekranına yönlendirir
        startActivity(i);

    }
}



