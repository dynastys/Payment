package com.xuanyin.payment.iu.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xuanyin.Classificaty;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.adapter.AddingCategoriesAdapter;
import com.xuanyin.payment.iu.entity.AddingCategories;
import org.litepal.LitePal;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddingCategoriesActivity extends BaseActivity {
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tool_text)
    TextView toolText;
    @BindView(R.id.recyc)
    RecyclerView recyc;
    AddingCategoriesAdapter adapter;

    private List<AddingCategories> imglist = new ArrayList<>();


    @Override
    protected Activity getContext() {
        return AddingCategoriesActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_add_categories;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("type");
        registerReceiver(typeBroadcastReceiver, intentFilter);

        setSupportActionBar(toolBar);
        //设置是否有返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolText.setText("编辑分类");
        recyc.setLayoutManager(new LinearLayoutManager(this));
        List<Classificaty> list = LitePal.findAll(Classificaty.class);
        for (int i = 0; i < list.size(); i++) {
            AddingCategories categories = new AddingCategories();
            categories.setName(list.get(i).getName());
            categories.setImage(list.get(i).getImage());
            imglist.add(categories);
        }

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        adapter = new AddingCategoriesAdapter(this,imglist);
        recyc.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                intoActivity(1,imglist.get(position).getImage(),imglist.get(position).getName());
            }
        });
    }

    @Override
    protected void setListener() {

    }

    private BroadcastReceiver typeBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("type")) {
                List<Classificaty> list = LitePal.findAll(Classificaty.class);
                imglist.clear();
                for (int i = 0; i < list.size(); i++) {
                    AddingCategories categories = new AddingCategories();
                    categories.setName(list.get(i).getName());
                    categories.setImage(list.get(i).getImage());
                    imglist.add(categories);
                }
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.add)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.add:
                intoActivity(2,R.mipmap.icon_shouru_type_qita,null);
                break;
        }
    }

    //Toolbar的事件---返回
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void intoActivity(int type, int image, String name){
        Intent intent = new Intent(this,ModificatyActivity.class);
        intent.putExtra("TYPE",type);
        intent.putExtra("IMAGE",image);
        intent.putExtra("NAME",name);
        startActivity(intent);

    }
}
