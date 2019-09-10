package com.xuanyin.payment.iu.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.xuanyin.payment.BaseActivity;
import com.xuanyin.payment.MPA.ChartItem;
import com.xuanyin.payment.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportFormActivity extends BaseActivity {


    @BindView(R.id.chart)
    LineChart chart;

    private Typeface mTf;
    List<ChartData> list = new ArrayList<>();
    @Override
    protected Activity getContext() {
        return ReportFormActivity.this;
    }

    @Override
    protected int getContentId() {
        return R.layout.actiivity_reportform;
    }

    @Override
    protected boolean getStatusBar() {
        return false;
    }

    @Override
    protected void loadViewLayout() {

        /**
         * x轴
         */
        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        xAxis.setAxisMaximum(12f);


        /**
         * 左边y轴
         */
        YAxis leftAxis = chart.getAxisLeft();

        leftAxis.setLabelCount(5, false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setAxisMaximum(1000f);

        /**
         * 右边y轴
         */
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
//        //rightAxis.setTypeface(mTf);
//        rightAxis.setLabelCount(5, false);
//        rightAxis.setDrawGridLines(false);
//        rightAxis.setAxisMinimum(0f);

        chart.setData(generateDataLine(0));
        chart.animateX(750);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return Line data
     */
    private LineData generateDataLine(int cnt) {

        ArrayList<Entry> values1 = new ArrayList<>();

        for (int i = 1; i < 13; i++) {
            values1.add(new Entry(i, (int) (Math.random() * 65) + 40));
        }
        LineDataSet d1 = new LineDataSet(values1,null);
        d1.setDrawCircleHole(false);
        d1.setDrawCircles(false);
        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(d1);

        return new LineData(sets);
    }

}
