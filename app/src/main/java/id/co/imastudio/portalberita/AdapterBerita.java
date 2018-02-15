package id.co.imastudio.portalberita;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

import id.co.imastudio.portalberita.response.BeritaItem;

/**
 * Created by syntax on 15/02/18.
 */

class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.MyViewHolder> {
    // Buat variable global untuk menampung contextnya
    Context context;
    List<BeritaItem> berita;
    public AdapterBerita(Context context, List<BeritaItem> data_berita) {
        // Inisialisasi
        this.context = context;
        this.berita = data_berita;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Layout Inflater
        View view = LayoutInflater.from(context).inflate(R.layout.berita_item, parent, false);
        // Hubungkan dengan MyViewholder
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // Set Widget
        holder.tvJudul.setText(berita.get(position).getJudulBerita());
        holder.tvTglTerbit.setText(berita.get(position).getTanggalPosting());

        // Mendapatkan URL Image
        final String urlGambarBerita = "http://192.168.95.77/portal_berita/images/" + berita.get(position).getFoto();
        // Set images ke widget dengan library picaso
        // Imagesnya dari internet
        Picasso.with(context).load(urlGambarBerita).into(holder.ivGambarBerita);

        // Event klik ketika item listnya diklik
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mulai activity detail
                Intent intent = new Intent(context, DetailActivity.class);
                // Sisipkan data ke intent
                intent.putExtra("JDL_BERITA", berita.get(position).getJudulBerita());
                intent.putExtra("TGL_BERITA", berita.get(position).getTanggalPosting());
                intent.putExtra("PNS_BERITA", berita.get(position).getPenulis());
                intent.putExtra("FTO_BERITA", urlGambarBerita);
                intent.putExtra("ISI_BERITA", berita.get(position).getIsiBerita());

                context.startActivity(intent);
            }
        });

    }

    // Menentukan jumlah item yang tampil
    @Override
    public int getItemCount() {
        return berita.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Deklarasi widget
        ImageView ivGambarBerita;
        TextView tvJudul, tvTglTerbit, tvPenulis;
        public MyViewHolder(View itemView) {
            super(itemView);
            // Inisialisasi widget
            ivGambarBerita = (ImageView) itemView.findViewById(R.id.ivGambarBerita);
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudulBerita);
            tvTglTerbit = (TextView) itemView.findViewById(R.id.tvTglTerbit);
            tvPenulis = (TextView) itemView.findViewById(R.id.tvPenulis);
        }
    }
}
