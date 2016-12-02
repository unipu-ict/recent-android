package iip.hr.recent.model;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author Mihovil
 */
public interface ApiService {

    String APIARY_URL = "https://private-2a1fd-recent.apiary-mock.com";

    @POST("login")
    Observable<User> login(@Body User user);

    @GET("record")
    Observable<List<Record>> getCurrentMonthRecord();

    @POST("record/{id}")
    Observable<RecordDetail> getRecordDetail(@Path("id") Integer id);

}
