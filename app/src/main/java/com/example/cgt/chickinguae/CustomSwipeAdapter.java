package com.example.cgt.chickinguae;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomSwipeAdapter extends PagerAdapter {
    private int[] image_res = {R.drawable.img4, R.drawable.download, R.drawable.downloadq, R.drawable.dwnld};
    private Context context;
    private LayoutInflater layoutInflater;
    int i;
 static  int length;
private  int customposition =0;
    public CustomSwipeAdapter(Context ctx) {
        this.context = ctx;
    }

    @Override
    public int getCount() {
        length = image_res.length;
        return image_res.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (LinearLayout) o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);
        ImageView imageView = item_view.findViewById(R.id.imageview);

                imageView.setImageResource(image_res[position]);

                container.addView(item_view);



        return item_view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
