package com.xuanyin.easy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.camnter.easyrecyclerview.widget.EasyRecyclerView;
import com.camnter.easyrecyclerviewsidebar.EasyFloatingImageView;
import com.camnter.easyrecyclerviewsidebar.EasyRecyclerViewSidebar;
import com.camnter.easyrecyclerviewsidebar.sections.EasyImageSection;
import com.camnter.easyrecyclerviewsidebar.sections.EasySection;
import com.xuanyin.payment.BaseFragment;
import com.xuanyin.payment.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment0 extends BaseFragment implements EasyRecyclerViewSidebar.OnTouchSectionListener {

    @BindView(R.id.section_rv)
    EasyRecyclerView sectionRv;
    @BindView(R.id.section_sidebar)
    EasyRecyclerViewSidebar sectionSidebar;
    @BindView(R.id.section_floating_iv)
    EasyFloatingImageView sectionFloatingIv;
    @BindView(R.id.section_floating_tv)
    TextView sectionFloatingTv;
    @BindView(R.id.section_floating_rl)
    RelativeLayout sectionFloatingRl;
    Unbinder unbinder;

    private EasyRecyclerView imageSectionRv;
    public SectionAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_0;
    }

    @Override
    protected void initListener() {
        adapter = new CircleImageSectionAdapter();
        if (imageSectionRv != null) {
            imageSectionRv.setAdapter(this.adapter);
        }
        sectionSidebar.setFloatView(sectionFloatingRl);
        sectionSidebar.setOnTouchSectionListener(this);
    }

    @Override
    protected void init() {
        //adapter.setList(this.getData());
        adapter.notifyDataSetChanged();
        sectionSidebar.setSections(this.adapter.getSections());
    }

    @Override
    public void onTouchImageSection(int sectionIndex, EasyImageSection imageSection) {
        sectionFloatingTv.setVisibility(View.INVISIBLE);
        sectionFloatingIv.setVisibility(View.VISIBLE);
        switch (imageSection.imageType) {
            case EasyImageSection.CIRCLE:
                sectionFloatingIv.setImageType(EasyFloatingImageView.CIRCLE);
                break;
            case EasyImageSection.ROUND:
                sectionFloatingIv.setImageType(EasyFloatingImageView.ROUND);
                break;
        }
        GlideUtils.displayNative(sectionFloatingIv, imageSection.resId);
        this.scrollToPosition(this.adapter.getPositionForSection(sectionIndex));
    }

    @Override
    public void onTouchLetterSection(int sectionIndex, EasySection letterSection) {
        sectionFloatingTv.setVisibility(View.VISIBLE);
        sectionFloatingIv.setVisibility(View.INVISIBLE);
        sectionFloatingTv.setText(letterSection.letter);
        this.scrollToPosition(adapter.getPositionForSection(sectionIndex));
    }

    private void scrollToPosition(int position) {
        imageSectionRv.getLinearLayoutManager().scrollToPositionWithOffset(position, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
