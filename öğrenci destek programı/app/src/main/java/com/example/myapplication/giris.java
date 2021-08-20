package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class giris extends AppCompatActivity {

    EditText mail, password;
    String txtmail, txtpassword;
    FirebaseAuth mAut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);

        mail= (EditText) findViewById(R.id.editTextTextEmailAddress);
        password=(EditText) findViewById(R.id.editTextTextPassword);
        mAut= FirebaseAuth.getInstance(); //kimlik doğrulama ekranı bağlantısı oluşturma


    }

    public void login(View v){

        txtmail= mail.getText().toString();
        txtpassword= password.getText().toString();

        if(!TextUtils.isEmpty(txtmail)&&!TextUtils.isEmpty(txtpassword)) { //edittextlerin boş olup olmadığını kontrol etme

            loginFunc(); // kullanıcı girişi için oluşturulan class

       }
        else{
            Toast.makeText(giris.this,"Lütfen tüm alanları doldurunuz ",Toast.LENGTH_LONG).show();
        }

        }

    private void loginFunc() {

        mAut.signInWithEmailAndPassword(txtmail,txtpassword).addOnCompleteListener(giris.this, new OnCompleteListener<AuthResult>() { // kullanıcının girişi mail ve şifre ile olacak ve addonCompliteListener ile sorgulama yapılacak
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){ //eğer giriş işlemi başarılı olduysa

                            Intent i= new Intent(getApplicationContext(),Main.class); //giris ekranına yönlendirir
                            Toast.makeText(giris.this,"Giriş İşlemi Başarılı",Toast.LENGTH_LONG).show(); //giriş işlemi başarılı oldu bildirimi
                            startActivity(i);

                        }
                        else{
                            // hata
                            Toast.makeText(giris.this,"Böyle bir kullanıcı bulunamadı bilgilerinizi kontrol edip tekrar deneyin",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}