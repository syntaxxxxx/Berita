package id.co.imastudio.portalberita.activities;

import android.annotation.SuppressLint;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;

import id.co.imastudio.portalberita.R;

public class DetailActivity extends AppCompatActivity {

    // Deklarasi
    ImageView ivImageBerita;
    TextView tvTglTerbit, tvPenulis;
    WebView wvKontentBerita;

    public static final String JDL = "jdl_berita";
    public static final String TGL = "tgl_berita";
    public static final String PNS = "pns_berita";
    public static final String ISI = "isi_berita";
    public static final String FTO = "fto_berita";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inisialisasi
        ivImageBerita = findViewById(R.id.ivGambarBerita);
        tvTglTerbit = findViewById(R.id.tvTglTerbit);
        tvPenulis = findViewById(R.id.tvPenulis);
        wvKontentBerita = findViewById(R.id.wvContentBerita);

        // Menjalankan method tampilberita
        tampilBerita();
    }

    @SuppressLint({"SetJavaScriptEnabled", "SetTextI18n"})
    private void tampilBerita() {

        // Tangkap data dari intent
        String judul_berita = getIntent().getStringExtra(JDL);
        String tangal_berita = getIntent().getStringExtra(TGL);
        String penulis_berita = getIntent().getStringExtra(PNS);
        String isi_berita = getIntent().getStringExtra(ISI);
        String foto_berita = getIntent().getStringExtra(FTO);

        // Set judul action / toolbar
        getSupportActionBar().setTitle(judul_berita);

        // Set ke widget
        tvPenulis.setText("Oleh : " + penulis_berita);
        tvTglTerbit.setText(tangal_berita);

        // Untuk gambar berita
        Picasso.with(this).load(foto_berita).into(ivImageBerita);

        // Set isi berita sebagai html ke WebView
        wvKontentBerita.getSettings().setJavaScriptEnabled(true);
        wvKontentBerita.loadData(isi_berita, "text/html; charset=utf-8", "UTF-8");
    }
}