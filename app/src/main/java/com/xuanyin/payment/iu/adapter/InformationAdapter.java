package com.xuanyin.payment.iu.adapter;

import android.graphics.Bitmap;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.othershe.combinebitmap.CombineBitmap;
import com.othershe.combinebitmap.layout.DingLayoutManager;
import com.othershe.combinebitmap.listener.OnProgressListener;
import com.othershe.library.NiceImageView;
import com.xuanyin.payment.R;
import com.xuanyin.payment.intefaceGroup.Click;
import com.xuanyin.payment.iu.entity.Consumption;
import com.xuanyin.payment.iu.entity.Information;

import java.util.List;

public class InformationAdapter extends BaseQuickAdapter<Information,BaseViewHolder> {

    private Click click;

    public InformationAdapter(List<Information> data) {
        super(R.layout.adapter_information, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Information item) {
        CombineBitmap.init(mContext)
                .setLayoutManager(new DingLayoutManager())
                .setSize(50) // 必选，组合后Bitmap的尺寸，单位dp
                .setGap(2) // 单个Bitmap之间的距离，单位dp，默认0dp
//                .setGapColor() // 单个Bitmap间距的颜色，默认白色
                .setPlaceholder(R.mipmap.icon) // 单个Bitmap加载失败的默认显示图片
//                .setUrls(item.getBitmaps()[4]) // 要加载的图片url数组
                .setBitmaps(item.getImage()) // 要加载的图片bitmap数组
//                .setResourceIds(R.mipmap.timg) // 要加载的图片资源id数组
//                .setImageView(niceIv1) // 直接设置要显示图片的ImageView
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onComplete(Bitmap bitmap) {
                        ((NiceImageView)helper.getView(R.id.nice_image)).setImageBitmap(bitmap);
                        //Glide.with(mcontext).load(bitmap).into((ImageView) helper.getView(R.id.nice_img));
                    }
                }).build();
        helper.setText(R.id.information_text,item.getName());
        helper.setText(R.id.information_text1,item.getName());
        helper.getView(R.id.inform_rela).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onClick(helper.getLayoutPosition());
            }
        });
    }

    public void spotButton(Click click){
        this.click = click;
    }
}
