package com.xuanyin.payment.iu.Group_chat.adapter;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xuanyin.payment.R;
import com.xuanyin.payment.iu.entity.Note;
import java.util.List;

public class NoteAdapter extends BaseQuickAdapter<Note,BaseViewHolder> {

    private Context context;
    public NoteAdapter(Context context,List<Note> data) {
        super(R.layout.adapter_note, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Note item) {
        helper.setText(R.id.adapter_note_name,item.getName());
        Glide.with(context).load(item.getBitmap()).into((ImageView) helper.getView(R.id.adapter_note_image));
    }
}
