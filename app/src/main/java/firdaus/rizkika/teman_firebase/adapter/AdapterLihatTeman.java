package firdaus.rizkika.teman_firebase.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import firdaus.rizkika.teman_firebase.MainActivity;
import firdaus.rizkika.teman_firebase.R;
import firdaus.rizkika.teman_firebase.TemanEdit;
import firdaus.rizkika.teman_firebase.database.Teman;

public class AdapterLihatTeman extends RecyclerView.Adapter<AdapterLihatTeman.ViewHolder>{

    private DatabaseReference databaseReference;
    private ArrayList<Teman> daftarTeman;
    private Context context;

    public AdapterLihatTeman(ArrayList<Teman> daftarTeman, Context context) {
        this.daftarTeman = daftarTeman;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teman,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
//push project
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String kode, nama, telpon;
        nama = daftarTeman.get(position).getNama();
        kode = daftarTeman.get(position).getKode();
        telpon = daftarTeman.get(position).getTelpon();

        holder.tvNama.setText(nama);

        holder.tvNama.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu pm = new PopupMenu(view.getContext(),view);
                pm.inflate(R.menu.popupmenu);

                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.nmEdit:
                                Bundle bundle = new Bundle();
                                bundle.putString("kunci1",kode);
                                bundle.putString("kunci2",nama);
                                bundle.putString("kunci3",telpon);

                                Intent intent = new Intent(view.getContext(),TemanEdit.class);
                                intent.putExtras(intent);
                                view.getContext().startActivity(intent);
                                break;
                            case R.id.nmHapus:
                                AlertDialog.Builder alertdialog = new AlertDialog.Builder(view.getContext());
                                alertdialog.setTitle("Yakin data" + nama + "akan dihapus?");
                                alertdialog.setMessage("Tekan 'Ya' untuk menghapus").
                                        setCancelable(false).
                                        setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                DeleteData(kode);
                                                Toast.makeText(view.getContext(),"Data" + nama + "berhasil dihapus",Toast.LENGTH_SHORT).show();
//                                                view.getContext().startActivity(new Intent(view.getContext(),MainActivity.class));
                                                Intent intent1 = new Intent(view.getContext(), MainActivity.class);
                                                view.getContext().startActivity(intent1);
                                            }
                                        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });
                                AlertDialog aDlg = alertdialog.create();
                                aDlg.show();
                                break;
                        }
                        return true;
                    }
                });
                return true;
            }
        });
    }

    private void DeleteData(String kode) {
        if (databaseReference !=null){
            databaseReference.child("Teman").child(kode).removeValue();
        }
    }

    @Override
    public int getItemCount() {
        return daftarTeman.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNama;

        ViewHolder(View v){
            super(v);
            tvNama = v.findViewById(R.id.txtNama);

            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
    }
}
