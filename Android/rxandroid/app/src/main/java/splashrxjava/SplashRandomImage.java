package splashrxjava;

public class SplashRandomImage {

    public final int id;
    public final String url;
    public final String larger_url;
    public final String source_id;
    public final String copyright;
    public final String site;

    public SplashRandomImage(int id,String url,String larger_url,String source_id,String copyright,String site){
            this.id=id;
            this.url=url;
            this.larger_url=larger_url;
            this.source_id=source_id;
            this.copyright=copyright;
            this.site=site;
    }
}
