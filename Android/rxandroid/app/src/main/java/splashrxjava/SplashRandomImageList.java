package splashrxjava;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SplashRandomImageList {

    @SerializedName("images")
    private List<SplashRandomImage> splashRandomImages;

    public List<SplashRandomImage> getSplashRandomImages() {
        return splashRandomImages;
    }

    public void setSplashRandomImages(List<SplashRandomImage> splashRandomImages) {
        this.splashRandomImages = splashRandomImages;
    }
}
