package com.nhbp.felsefesozlugum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText editTextSearch;
    Button   btnSearch;
    TextView txtResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextSearch = (EditText)findViewById(R.id.EditTxtSearch);
        btnSearch = (Button) findViewById(R.id.btnSubmit);
        txtResults = (TextView) findViewById(R.id.txtViewResults);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextSearch.getText().toString()))
                {
                    Toast.makeText(MainActivity.this, "Kelimeyi girin", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("meaning");
                    mRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String SearchKeyword  = editTextSearch.getText().toString();
                            if (dataSnapshot.child(SearchKeyword).exists())
                            {
                                txtResults.setText(dataSnapshot.child(SearchKeyword).getValue().toString());

                            }else
                            {
                                Toast.makeText(MainActivity.this, "Sonuç Bulunamadı", Toast.LENGTH_SHORT).show();

                            }




                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }

        });

        }
    }




