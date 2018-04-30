package id.co.imastudio.portalberita.network;

import id.co.imastudio.portalberita.response.ResponseBerita;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by syntax on 15/02/18.
 */

public interface ApiService {
    //@TIPEMETHOD("END_POINT")
    @GET("tampil_berita.php")
    Call<ResponseBerita> request_show_all_berita();
    // <ModelData> nama_method()
}
