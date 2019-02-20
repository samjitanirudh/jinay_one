package splashrxjava;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.chrisarriola.githubrxjava.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 6/1/16.
 */
public class ImageAdapter extends BaseAdapter {

    private List<SplashRandomImage> splashImageList = new ArrayList<>();

    @Override public int getCount() {
        return splashImageList.size();
    }

    @Override public SplashRandomImage getItem(int position) {
        if (position < 0 || position >= splashImageList.size()) {
            return null;
        } else {
            return splashImageList.get(position);
        }
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        final View view = (convertView != null ? convertView : createView(parent));
        final SplashListImageViewHolder viewHolder = (SplashListImageViewHolder) view.getTag();
        viewHolder.setGitHubRepo(getItem(position));
        return view;
    }

    public void setSplashImageList(@Nullable List<SplashRandomImage> repos) {
        if (repos == null) {
            return;
        }
        splashImageList.clear();
        splashImageList.addAll(repos);
        notifyDataSetChanged();
    }

    private View createView(ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.item_splash_list, parent, false);
        final SplashListImageViewHolder viewHolder = new SplashListImageViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    public void add(SplashRandomImage splashImage) {
        splashImageList.add(splashImage);
        notifyDataSetChanged();
    }

    private static class SplashListImageViewHolder {

        private ImageView imgView;

        public SplashListImageViewHolder(View view) {
            imgView = (ImageView) view.findViewById(R.id.listimage_item);
        }

        public void setGitHubRepo(SplashRandomImage splashRandomImage) {
            Picasso.get().load(splashRandomImage.url).into(imgView);
        }
    }
}
