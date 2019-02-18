package splashrxjava;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class SplashRandomClient {

    private static final String SPLASH_BASE_URL = "http://www.splashbase.co/api/v1/";

    private static SplashRandomClient instance;
    private SplashRandomService splashService;

    private SplashRandomClient() {
        final Gson gson =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(SPLASH_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        splashService = retrofit.create(SplashRandomService.class);
    }

    public static SplashRandomClient getInstance() {
        if (instance == null) {
            instance = new SplashRandomClient();
        }
        return instance;
    }

    public Observable<SplashRandomImage> getRandomImage() {
        return splashService.getRandomImages();
    }

    public Observable<List<SplashRandomImage>> getLatestImage() {
        return splashService.getLatestImages();
    }

    public Observable<List<SplashRandomImage>> getSearchImages(String search) {
        return splashService.getSearchImage(search);
    }

}
