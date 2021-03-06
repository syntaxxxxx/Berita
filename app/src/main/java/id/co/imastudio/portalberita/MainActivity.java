package id.co.imastudio.portalberita;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import id.co.imastudio.portalberita.activities.ProfileActivity;
import id.co.imastudio.portalberita.adapter.AdapterBerita;
import id.co.imastudio.portalberita.network.ApiService;
import id.co.imastudio.portalberita.network.InitRetrofit;
import id.co.imastudio.portalberita.responseAPI.BeritaItem;
import id.co.imastudio.portalberita.responseAPI.ResponseBerita;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // Deklarasi Widget
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi Widget
        recyclerView = (RecyclerView) findViewById(R.id.rvListBerita);

        // RecyclerView harus pakai layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Eksekusi method
        tampilBerita();
    }

    private void tampilBerita() {
        ApiService api = InitRetrofit.getInstance();

        // Siapkan request
        Call<ResponseBerita> beritaCall = api.request_show_all_berita();

        // Kirim request
        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {

                // Pastikan response sukses
                if (response.isSuccessful()) {
                    Log.d("response api", response.body().toString());

                    // Tampung data response ke variable
                    List<BeritaItem> data_berita = response.body().getBerita();

                    boolean status = response.body().isStatus();
                    // Kali response statusnya true
                    if (status) {

                        // Buat adapter recyclerview
                        AdapterBerita adapter = new AdapterBerita(MainActivity.this, data_berita);
                        recyclerView.setAdapter(adapter);

                    } else {

                        // Kalau tidak true
                        Toast.makeText(MainActivity.this, "Tidak Ada berita",
                                Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {

                // Print ke log jika error
                t.printStackTrace();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_nav, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.profile) {

            // pindah halaman yang dituju
            startActivity(new Intent(this, ProfileActivity.class));

        } else if (id == R.id.logout) {

            // exit
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);

    }
}
