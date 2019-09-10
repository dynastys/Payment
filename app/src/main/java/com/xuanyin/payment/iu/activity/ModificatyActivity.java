package com.xuanyin.payment.iu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xuanyin.Classificaty;
import com.xuanyin.payment.AppManager;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.adapter.ModificatyAdapter;
import com.xuanyin.payment.iu.entity.AddingCategories;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModificatyActivity extends BaseActivity {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tool_text)
    TextView toolText;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.recyc)
    RecyclerView recyc;

    ModificatyAdapter adapter;
    private List<AddingCategories> imglist = new ArrayList<>();
    private String name;
    private int images;
    private int type;

    Integer [] img  = {R.mipmap.icon_shouru_type_qita,R.mipmap.ad,R.mipmap.anjie,R.mipmap.baobao,
            R.mipmap.baojian,R.mipmap.baoxiao,R.mipmap.chashuikafei,R.mipmap.chuanpiao,R.mipmap.daoyou,R.mipmap.dapai
            ,R.mipmap.dianfei,R.mipmap.dianying,R.mipmap.fangdai,R.mipmap.fangzu,R.mipmap.fanka
            ,R.mipmap.feijipiao,R.mipmap.fuwu,R.mipmap.gonggongqiche,R.mipmap.haiwaidaigou
            ,R.mipmap.huankuan,R.mipmap.huazhuangpin,R.mipmap.huochepiao,R.mipmap.icon_add_1
            ,R.mipmap.icon_add_3,R.mipmap.icon_add_4,R.mipmap.icon_add_5,R.mipmap.icon_add_6
            ,R.mipmap.icon_add_7,R.mipmap.icon_add_8,R.mipmap.icon_add_9,R.mipmap.icon_add_10
            ,R.mipmap.icon_add_11,R.mipmap.icon_add_12,R.mipmap.icon_add_13,R.mipmap.icon_add_14
            ,R.mipmap.icon_add_15,R.mipmap.icon_add_16,R.mipmap.icon_add_17,R.mipmap.icon_add_18
            ,R.mipmap.icon_add_19,R.mipmap.icon_add_20,R.mipmap.icon_gouwu,R.mipmap.icon_jiaotongchuxing
            ,R.mipmap.icon_lingshixiaochi,R.mipmap.icon_shichanghuodong,R.mipmap.icon_shouru_type_gongzi
            ,R.mipmap.icon_shouru_type_hongbao,R.mipmap.icon_shouru_type_jiangjin
            ,R.mipmap.icon_shouru_type_jianzhiwaikuai,R.mipmap.icon_shouru_type_jieru
            ,R.mipmap.icon_shouru_type_linghuaqian,R.mipmap.icon_shouru_type_qita
            ,R.mipmap.icon_shouru_type_shenghuofei,R.mipmap.icon_shouru_type_touzishouru
            ,R.mipmap.icon_zhichu_type_canyin,R.mipmap.icon_zhichu_type_chongwu,R.mipmap.icon_zhichu_type_gouwu
            ,R.mipmap.icon_zhichu_type_jiaotong,R.mipmap.icon_zhichu_type_jiechu,R.mipmap.icon_zhichu_type_shoujitongxun
            ,R.mipmap.icon_zhichu_type_shuiguolingshi,R.mipmap.icon_zhichu_type_shuji,R.mipmap.icon_zhichu_type_taobao
            ,R.mipmap.icon_zhichu_type_yanjiuyinliao,R.mipmap.icon_zhichu_type_yiliaojiaoyu
            ,R.mipmap.icon_zhichu_type_yule,R.mipmap.jiushui,R.mipmap.lifa,R.mipmap.lixishouru,R.mipmap.lixizhichu
            ,R.mipmap.lvyoudujia,R.mipmap.maicai,R.mipmap.majiang,R.mipmap.mao,R.mipmap.naifen,R.mipmap.party
            ,R.mipmap.richangyongpin,R.mipmap.shipin,R.mipmap.shuifei,R.mipmap.shumachanpin,R.mipmap.suishouzan1
            ,R.mipmap.tuikuan,R.mipmap.wangfei,R.mipmap.wanggou,R.mipmap.wanju,R.mipmap.xianjin,R.mipmap.xiaochi
            ,R.mipmap.xiaojingjiazhang,R.mipmap.xiezi,R.mipmap.xinyongkahuankuan,R.mipmap.xizao,R.mipmap.xuefei
            ,R.mipmap.yan,R.mipmap.yaopinfei,R.mipmap.yifu,R.mipmap.yinhangshouxufei,R.mipmap.yiwaiposun
            ,R.mipmap.yiwaisuode,R.mipmap.youfei,R.mipmap.youxi,R.mipmap.yuegenghuan,R.mipmap.yundongjianshen
            ,R.mipmap.zaocan,R.mipmap.zawu,R.mipmap.zhifubao,R.mipmap.zhongfan,R.mipmap.zhuanzhang
            ,R.mipmap.zhuanzhang1,R.mipmap.zuojifei
    };
    @Override
    protected Activity getContext() {
        return ModificatyActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_modificaty;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {
        setSupportActionBar(toolBar);
        //设置是否有返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolText.setText("修改类别");
        recyc.setLayoutManager(new GridLayoutManager(this,5));
        for (int i = 0; i < img.length; i++) {
            AddingCategories categories = new AddingCategories();
            categories.setImage(img[i]);
            imglist.add(categories);
        }
        type = getIntent().getIntExtra("TYPE",0);
        images = getIntent().getIntExtra("IMAGE",R.mipmap.icon_shouru_type_qita);
        name = getIntent().getStringExtra("NAME");
        Glide.with(getContext()).load(images).into(image);
        edit.setText(name);
        setSelectionEnd(edit);

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        adapter = new ModificatyAdapter(this,imglist);
        recyc.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Glide.with(getContext()).load(img[position]).into(image);
                images = img[position];
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    //Toolbar的事件---返回
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.determine)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.determine:

                if (!TextUtils.isEmpty(edit.getText().toString())){
                    List<Classificaty> lists = LitePal.where("name =?",edit.getText().toString()).find(Classificaty.class);
                    if (lists.size() == 0){
//                        if (type == 1){
//                            Classificaty classificaty = new Classificaty();
//                            classificaty.setName(edit.getText().toString());
//                            classificaty.setImage(images);
//                            classificaty.updateAll("name =?", name);
//                        } else if (type == 2) {
//                            Classificaty classificaty = new Classificaty();
//                            classificaty.setName(edit.getText().toString());
//                            classificaty.setImage(images);
//                            classificaty.save();
//                        }
                        // 广播通知
                        Intent intent = new Intent();
                        intent.setAction("type");
                        sendBroadcast(intent);
                        AppManager.getAppManager().finishActivity();
                    }
                }


//
                break;
        }
    }

    public static void setSelectionEnd(EditText editText) {
        Editable b = editText.getText();
        editText.setSelection(b.length());
    }


}
