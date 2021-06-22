package firdaus.rizkika.teman_firebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import firdaus.rizkika.teman_firebase.R;
import firdaus.rizkika.teman_firebase.database.Teman;

public class AdapterLihatTeman extends RecyclerView.Adapter<AdapterLihatTeman.ViewHolder>{
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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nama = daftarTeman.get(position).getNama();
        holder.tvNama.setText(nama);
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
        }
    }
}
