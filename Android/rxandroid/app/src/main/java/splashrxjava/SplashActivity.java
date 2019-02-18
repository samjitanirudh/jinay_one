package splashrxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.chrisarriola.githubrxjava.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private Subscription subscription;
    private ImageView imgSplash;
    private ProgressBar imgLoading;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_ui);

        imgSplash = (ImageView) findViewById(R.id.imgRandom);
        imgLoading= (ProgressBar)findViewById(R.id.imgLoading);
        imgLoading.setVisibility(View.GONE);
        Button btnRandom = (Button) findViewById(R.id.btnRandomImage);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRandomImage();
            }
        });
        getRandomImage();
    }

    @Override
    protected void onDestroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    private void getRandomImage() {
        subscription = SplashRandomClient.getInstance()
                .getRandomImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SplashRandomImage>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SplashRandomImage splashRandomImage) {
                        imgLoading.setVisibility(View.VISIBLE);
                        Picasso.get().load(splashRandomImage.url).placeholder(imgSplash.getDrawable()).into(imgSplash, new Callback() {
                            @Override
                            public void onSuccess() {
                                imgLoading.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });
                    }
                });

    }
}