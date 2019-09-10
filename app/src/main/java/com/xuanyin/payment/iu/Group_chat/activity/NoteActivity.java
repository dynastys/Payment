package com.xuanyin.payment.iu.Group_chat.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xuanyin.Classificaty;
import com.xuanyin.ConsumptionDetails;
import com.xuanyin.Consumptions;
import com.xuanyin.People;
import com.xuanyin.payment.AppManager;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.Group_chat.adapter.ConsumptionAdapter;
import com.xuanyin.payment.iu.Group_chat.adapter.NoteAdapter;
import com.xuanyin.payment.iu.activity.AddingCategoriesActivity;
import com.xuanyin.payment.iu.entity.Group_detailed;
import com.xuanyin.payment.iu.entity.Note;
import com.xuanyin.payment.presenter.Presenter;
import com.xuanyin.payment.utils.All;
import com.xuanyin.payment.utils.ScrollGridLayoutManager;
import com.xuanyin.payment.utils.Utils;
import com.xuanyin.payment.view.ILogView;

import org.litepal.LitePal;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoteActivity extends BaseActivity implements ILogView {
    @BindView(R.id.edit)
    EditText edit;
    @BindView(R.id.tool_text)
    TextView toolText;
    @BindView(R.id.recyc_category)
    RecyclerView recycCategory;
    @BindView(R.id.note_image)
    ImageView noteImage;
    @BindView(R.id.note_text)
    TextView noteText;

    Presenter presenter;
    NoteAdapter adapter;
    @BindView(R.id.data)
    TextView data;
    @BindView(R.id.drawee)
    TextView drawee;
    @BindView(R.id.participants)
    TextView participants;
    @BindView(R.id.news_details_recyc)
    RecyclerView newsDetailsRecyc;
    @BindView(R.id.remarks)
    TextView remarks;

    private List<Note> notes = new ArrayList<>();
    private ConsumptionAdapter conadapter;
    private SimpleDateFormat simpleDateFormat;
    private Date date;
    public static boolean Istrue = true;

    public static List<Group_detailed> GD = new ArrayList<>();
    public static List<Group_detailed> GDs = new ArrayList<>();
    private int i = 0;

    @Override
    protected Activity getContext() {
        return NoteActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_note;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {
        Utils.setPricePoint(edit);
        presenter = new Presenter(this);

        ScrollGridLayoutManager scrollGridLayoutManager = new ScrollGridLayoutManager(this, 5);
        scrollGridLayoutManager.setScrollEnable(true);
        recycCategory.setLayoutManager(scrollGridLayoutManager);
        recycCategory.setOverScrollMode(View.OVER_SCROLL_NEVER);
        recycCategory.setNestedScrollingEnabled(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        newsDetailsRecyc.setLayoutManager(layoutManager);
        newsDetailsRecyc.setOverScrollMode(View.OVER_SCROLL_NEVER);
        newsDetailsRecyc.setNestedScrollingEnabled(false);
        simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        //获取当前时间
        date = new Date(System.currentTimeMillis());
        data.setText(simpleDateFormat.format(date));

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("type");
        registerReceiver(typeBroadcastReceiver, intentFilter);

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        presenter.information();

        noteText.setText(notes.get(0).getName());
        Glide.with(getContext()).load(notes.get(0).getBitmap()).into(noteImage);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (notes.get(position).getName().equals("编辑")) {
                    intentActivity(AddingCategoriesActivity.class);
                } else {
                    Glide.with(getContext()).load(notes.get(position).getBitmap()).into(noteImage);
                    noteText.setText(notes.get(position).getName());
                }
            }
        });

        drawee.setText(GD.get(0).getName());
        participants.setText(GD.get(0).getName() + "等" + GD.size() + "人");

        conadapter = new ConsumptionAdapter(GDs);
        newsDetailsRecyc.setAdapter(conadapter);

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //edit.removeTextChangedListener(this);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }


            @Override
            public void afterTextChanged(Editable s) {


                if (Istrue){
                    String money = "0.00";
                    for (int i = 0; i < GDs.size(); i++) {
                        if (!TextUtils.isEmpty(s.toString())){
                            money = Utils.doubleTrans(Double.parseDouble(s.toString())/GDs.size());
                        }
                        GDs.get(i).setMoney(money);
                    }
                    conadapter = new ConsumptionAdapter(GDs);
                    newsDetailsRecyc.setAdapter(conadapter);
                    conadapter.notifyDataSetChanged();
                    Istrue = true;
                }


            }
        });

    }

    private BroadcastReceiver typeBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("type")) {
                List<Classificaty> list = LitePal.findAll(Classificaty.class);
                notes.clear();
                for (int j = 0; j < list.size(); j++) {
                    Note note = new Note();
                    note.setName(list.get(j).getName());
                    note.setBitmap(list.get(j).getImage());
                    notes.add(note);
                }
                Note note = new Note();
                note.setName("编辑");
                note.setBitmap(R.mipmap.bianji);
                notes.add(note);
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void setListener() {




    }



    @Override
    public void show() {
        List<Classificaty> list = LitePal.findAll(Classificaty.class);

        for (int j = 0; j < list.size(); j++) {
            Note note = new Note();
            note.setName(list.get(j).getName());
            note.setBitmap(list.get(j).getImage());
            notes.add(note);
        }
        Note note = new Note();
        note.setName("编辑");
        note.setBitmap(R.mipmap.bianji);
        notes.add(note);
        adapter = new NoteAdapter(getContext(), notes);
        recycCategory.setAdapter(adapter);

        List<People> people = presenter.util.dataConsumption();
        for (int i = 0; i < people.size(); i++) {
            if (All.GroupName.equals(people.get(i).getGroupname())) {
                Group_detailed gd = new Group_detailed();
                gd.setName(people.get(i).getName());
                gd.setBitmap(people.get(i).getImage());
                gd.setMoney(people.get(i).getMoney());
                gd.setDrawee(people.get(i).isDrawee());
                gd.setParticipant(people.get(i).isParticipants());
                GD.add(gd);
            }
        }

        for (int i = 0; i < GD.size(); i++) {
            if (GD.get(i).isParticipant()) {
                Group_detailed gd = new Group_detailed();
                gd.setName(GD.get(i).getName());
                gd.setBitmap(GD.get(i).getBitmap());
                gd.setMoney(GD.get(i).getMoney());
                gd.setDrawee(GD.get(i).isDrawee());
                gd.setParticipant(GD.get(i).isParticipant());
                GDs.add(gd);
            }
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                for (int i = 0; i < GD.size(); i++) {
                    if (GD.get(i).isDrawee()){
                        drawee.setText(GD.get(i).getName());
                    }
                }
                break;
            case 2:
                boolean is = true;
                int size = 0;
                int num = 0;
                for (int i = 0; i < GD.size(); i++) {

                    if (GD.get(i).isParticipant()){
                        size = size+1;
                    }

                    if (GD.get(i).isParticipant() && is){
                        num = i;
                        is = false;
                    }

                }
                participants.setText(GD.get(num).getName() + "等" + size + "人");
                GDs.clear();
                for (int i = 0; i < GD.size(); i++) {
                    if (GD.get(i).isParticipant()) {
                        Group_detailed gd = new Group_detailed();
                        gd.setName(GD.get(i).getName());
                        gd.setBitmap(GD.get(i).getBitmap());
                        gd.setMoney(GD.get(i).getMoney());
                        gd.setDrawee(GD.get(i).isDrawee());
                        gd.setParticipant(GD.get(i).isParticipant());
                        GDs.add(gd);
                    }
                }
                conadapter = new ConsumptionAdapter(GDs);
                newsDetailsRecyc.setAdapter(conadapter);
                conadapter.notifyDataSetChanged();
                break;
            case 3:
                Istrue = false;
                double s = 0.00;
                for (int i = 0; i < GDs.size(); i++) {
                    s = Double.parseDouble(GDs.get(i).getMoney())+s;

                }
                edit.setText(Utils.doubleTrans(s));
                conadapter = new ConsumptionAdapter(GDs);
                newsDetailsRecyc.setAdapter(conadapter);
                Istrue = true;
                break;
        }
    }

    @OnClick({R.id.text_note_cancel, R.id.text_note_establish, R.id.change_of_data, R.id.change_of_payer,
            R.id.change_of_participants, R.id.modify_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_note_cancel:
                AppManager.getAppManager().finishActivity();
                break;
            case R.id.text_note_establish:
                if (!TextUtils.isEmpty(edit.getText().toString())) {
                    String TIME = data.getText().toString();

                    Consumptions consumptions = new Consumptions();
                    consumptions.setType(1);
                    consumptions.setData(TIME);
                    consumptions.setGroup_name(All.GroupName);
                    consumptions.setType_name(noteText.getText().toString());
                    consumptions.setType_img(noteImage.getDrawable());
                    consumptions.setMoney(edit.getText().toString());
                    consumptions.setDrawee(drawee.getText().toString());
                    if (!TextUtils.isEmpty(remarks.getText().toString())){
                        consumptions.setRemarks(remarks.getText().toString());
                    }
                    consumptions.save();

                    ConsumptionDetails details = new ConsumptionDetails();
                    details.setTime(TIME);
                    details.setGroupname(All.GroupName);
                    for (int j = 0; j < GDs.size(); j++) {
                        details.setMoney(GDs.get(j).getMoney());
                        details.setName(GDs.get(j).getName());
                    }
                    details.save();
                    AppManager.getAppManager().finishActivity();

                    // 广播通知
                    Intent intent = new Intent();
                    intent.setAction("Add");
                    sendBroadcast(intent);
                } else {
                    Toast.makeText(getContext(), "请输入消费金额", Toast.LENGTH_SHORT);
                }
                break;
            case R.id.change_of_data:
                break;
            case R.id.change_of_payer:
                Intent payer = new Intent(NoteActivity.this, AddMovableAdtivity.class);
                payer.putExtra("NUM","1");
                startActivityForResult(payer,1);
                break;
            case R.id.change_of_participants:
                Intent par = new Intent(NoteActivity.this,AddMovableAdtivity.class);
                par.putExtra("NUM","2");
                startActivityForResult(par,2);
                break;
            case R.id.modify_btn:
                Intent con = new Intent(NoteActivity.this,ConsumpActivity.class);
                startActivityForResult(con,3);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(typeBroadcastReceiver);
    }
}
