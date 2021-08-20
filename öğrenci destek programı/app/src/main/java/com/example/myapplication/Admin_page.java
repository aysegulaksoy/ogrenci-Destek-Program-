package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.renderscript.Type;
import android.text.TextUtils;
import android.text.format.DateFormat;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Admin_page extends AppCompatActivity {

    Button kullanıcı,liste;
    TextView text;
    DocumentReference df;

    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser(); //veritabanı ilişkisi
    FirebaseFirestore firestore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);

    kullanıcı=(Button)findViewById(R.id.kullanıcılar);
    liste=(Button)findViewById(R.id.liste);
    text=(TextView) findViewById(R.id.textView7);
    firestore=FirebaseFirestore.getInstance();

        ArrayList<Type> veri = new ArrayList<>();





    //DocumentReference df= db.collection("users").document(user.getUid()); //veri tabanında motivasyonsözleri adlı koleksiyonun 1 numaralı belgesine erişim



      kullanıcı.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              /*  db.collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {

                        if(documentSnapshots.isEmpty()){

                            Toast.makeText(Admin_page.this, "bir hata ile karşılaşıldı ", Toast.LENGTH_LONG).show(); //kayıt sırasında bir hata ile karşılaşılırsa bildirim mesajı verir

                            return;
                        }
                        else  {

                           // List<Type> types = documentSnapshots.toObjects(Type.class);

                            veri.addAll(documentSnapshots.toObjects(Type.class));
                            text.setText(""+veri);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {

                        Toast.makeText(Admin_page.this, "bir hata ile karşılaşıldı ", Toast.LENGTH_LONG).show(); //kayıt sırasında bir hata ile karşılaşılırsa bildirim mesajı verir

                    }
                });

*/

           /*   db.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException error) {

                        if(documentSnapshots.isEmpty()){

                            Toast.makeText(Admin_page.this, "bir hata ile karşılaşıldı ", Toast.LENGTH_LONG).show(); //kayıt sırasında bir hata ile karşılaşılırsa bildirim mesajı verir
                        }
                        else  {

                            List<Type> types = documentSnapshots.toObjects(Type.class);


                            veri.addAll(types);

                            text.setText(""+veri);
                        }

                    }
                });*/

                df= firestore.collection("users").document(user.getUid()); //veri tabanında motivasyonsözleri adlı koleksiyonun 1 numaralı belgesine erişim
                df.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document=task.getResult();
                        if(document.exists()){

                            text.setText("" +document.getData() );
                        }else {
                            Toast.makeText(Admin_page.this,"Bir hata ile karşılaşıldı: " +task.getException(), Toast.LENGTH_LONG);

                        }
                    }
                });

            }
        });

        liste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}