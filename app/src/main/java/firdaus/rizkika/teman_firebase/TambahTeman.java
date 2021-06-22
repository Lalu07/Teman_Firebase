package firdaus.rizkika.teman_firebase;

import androidx.appcompat.app.AppCompatActivity;
import firdaus.rizkika.teman_firebase.database.Teman;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahTeman extends AppCompatActivity {
    private EditText EdNama,EdTelpon;
    private Button bttnSubmit;
    private DatabaseReference database;
    String nm,tlp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_teman);

        EdNama = findViewById(R.id.editNama);
        EdTelpon = findViewById(R.id.editTelpon);
        bttnSubmit = findViewById(R.id.btnSubmit);

        database = FirebaseDatabase.getInstance().getReference();

        bttnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!EdNama.getText().toString().isEmpty() && !EdTelpon.getText().toString().isEmpty()){
                  //simpan data dari textbox ke variable nm dan tlp
                    nm = EdNama.getText().toString();
                    tlp = EdTelpon.getText().toString();
                    //memasukkan data nm dan tlp ke dalam method submitTeman
                    submitTeman(new Teman(nm,tlp));
                }
                else {
                    Toast.makeText(TambahTeman.this,"Data tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void submitTeman(Teman tmn){
        database.child("Teman").push().setValue(tmn).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                EdNama.setText("");
                EdTelpon.setText("");
                Toast.makeText(TambahTeman.this,"Data berhasil di tambahkan",Toast.LENGTH_LONG).show();
            }
        });
    }
}