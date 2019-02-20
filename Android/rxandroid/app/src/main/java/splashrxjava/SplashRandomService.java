package splashrxjava;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


    public interface SplashRandomService {

        @GET("images/random")
        Observable<SplashRandomImage> getRandomImages();

        @GET("images/latest")
        Observable<SplashRandomImageList> getLatestImages();

        @GET("images/search?query={search}")
        Observable<List<SplashRandomImage>> getSearchImage(@Path("search") String searchTerm);
    }
