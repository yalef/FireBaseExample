package com.example.user.firebaseexample;

import android.nfc.Tag;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference myRef;
    private ListView lv;
    TextView text;
    private List<String> list;

    private static final String TAG = "MyApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView2);
        //lv = (ListView) findViewById(R.id.lv);
        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<>();
                String value = dataSnapshot.child("Рецепты").child("Салат").child("Имя").getValue(String.class);
                text.setText(value);
                Log.d(TAG,"Value is: " + value);
                //Recept recept = dataSnapshot.child("Рецепты").getValue(Recept.class);

                //updateUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void updateUI(){
        ArrayAdapter<Recept> adapter = new ArrayAdapter<Recept>(getApplicationContext(),R.layout.item_list);

        lv.setAdapter(adapter);
    }
}
