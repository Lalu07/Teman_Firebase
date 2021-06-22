package firdaus.rizkika.teman_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button tambahBTN, lihatBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tambahBTN = findViewById(R.id.btninsert);
        lihatBTN = findViewById(R.id.btnshow);

        tambahBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TambahTeman.class);
                startActivity(intent);
            }
        });

        lihatBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Lihat_Teman.class);
                startActivity(intent);
            }
        });

    }
}