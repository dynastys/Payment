package com.xuanyin.payment.iu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.othershe.combinebitmap.CombineBitmap;
import com.othershe.combinebitmap.layout.DingLayoutManager;
import com.othershe.combinebitmap.listener.OnProgressListener;
import com.othershe.library.NiceImageView;
import com.xuanyin.payment.R;
import com.xuanyin.payment.intefaceGroup.Click;
import com.xuanyin.payment.iu.entity.GroupChat;
import java.util.List;

public class GroupChatAdapter extends RecyclerView.Adapter<GroupChatAdapter.ViewHolder>{
    private Context mContext;
    private List<GroupChat> list;
    private Click click;
    private int bit;
    public GroupChatAdapter(Context context,List<GroupChat> list){
        this.mContext = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_groupchat,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.time.setText(list.get(i).getTime());
        if (list.get(i).getPeopleGCS().size()>4){
            bit = 4;
        }else {
            bit = list.get(i).getPeopleGCS().size();
        }

        Bitmap [] bitmaps = new Bitmap[bit];
        for (int j = 0; j < bit; j++) {
            bitmaps [j] = BitmapFactory.decodeResource(mContext.getResources(), list.get(i).getPeopleGCS().get(i).getImage());
        }

        CombineBitmap.init(mContext)
                .setLayoutManager(new DingLayoutManager())
                .setSize(50) // 必选，组合后Bitmap的尺寸，单位dp
                .setGap(2) // 单个Bitmap之间的距离，单位dp，默认0dp
//                .setGapColor() // 单个Bitmap间距的颜色，默认白色
                .setPlaceholder(R.mipmap.icon) // 单个Bitmap加载失败的默认显示图片
//                .setUrls(item.getBitmaps()[4]) // 要加载的图片url数组
                .setBitmaps(bitmaps) // 要加载的图片bitmap数组
//                .setResourceIds(R.mipmap.timg) // 要加载的图片资源id数组
//                .setImageView(niceIv1) // 直接设置要显示图片的ImageView
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onComplete(Bitmap bitmap) {
                        viewHolder.nice.setImageBitmap(bitmap);
                    }
                }).build();
        viewHolder.groupchat_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClick(i);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private NiceImageView nice;
        private TextView title;
        private TextView time;
        private LinearLayout groupchat_line;

        public ViewHolder(View itemView) {
            super(itemView);
            nice = itemView.findViewById(R.id.nice_img);
            title = itemView.findViewById(R.id.text_title);
            time = itemView.findViewById(R.id.text_time);
            groupchat_line = itemView.findViewById(R.id.groupchat_line);

        }
    }


    public void spotButton(Click click){
        this.click = click;
    }



}
