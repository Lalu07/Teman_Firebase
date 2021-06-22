package firdaus.rizkika.teman_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import firdaus.rizkika.teman_firebase.adapter.AdapterLihatTeman;
import firdaus.rizkika.teman_firebase.database.Teman;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Lihat_Teman extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private RecyclerView ShowMe;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager Lym;
    private ArrayList<Teman> dataTeman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat__teman);

        //inisialisasi utuk recycler view
        ShowMe = findViewById(R.id.SeeFrend);
        ShowMe.setHasFixedSize(true);
        Lym = new LinearLayoutManager(this);
        ShowMe.setLayoutManager(Lym);

        //inisialisasi dan membaca firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

        //mengambil data dan membaca data dalam firebase
        databaseReference.child("Teman").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataTeman = new ArrayList<>();
                for (DataSnapshot data:snapshot.getChildren()){
                    Teman T = data.getValue(Teman.class);
                    T.setKode(data.getKey());

                    dataTeman.add(T);
                }

                adapter = new AdapterLihatTeman(dataTeman,Lihat_Teman.this);
                ShowMe.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println(error.getDetails()+ "" + error.getMessage());
            }
        });

    }
}