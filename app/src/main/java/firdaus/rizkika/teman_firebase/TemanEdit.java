package firdaus.rizkika.teman_firebase;

import androidx.appcompat.app.AppCompatActivity;
import firdaus.rizkika.teman_firebase.database.Teman;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TemanEdit extends AppCompatActivity {
    TextView tv_key;
    EditText ednama,edtelpon;
    Button edit;
    DatabaseReference databaseReference;
    String kode,nama,telepon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teman_edit);

        tv_key = findViewById(R.id.tv_Key);
        ednama = findViewById(R.id.ed_Nama);
        edtelpon = findViewById(R.id.ed_Telpon);
        edit = findViewById(R.id.bttnEdit);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = this.getIntent().getExtras();
        kode = bundle.getString("kunci1");
        nama = bundle.getString("kunci2");
        telepon = bundle.getString("kunci3");

        tv_key.setText(kode);
        ednama.setText(nama);
        edtelpon.setText(telepon);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTeman(new Teman(ednama.getText().toString(),edtelpon.getText().toString()));
            }
        });
    }

    public void editTeman(Teman teman){
        databaseReference.child("Teman").child(kode).setValue(teman).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(TemanEdit.this,"Data Berhasil di Update",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}