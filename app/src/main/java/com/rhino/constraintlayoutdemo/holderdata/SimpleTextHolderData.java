package com.rhino.constraintlayoutdemo.holderdata;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.View;

import com.rhino.constraintlayoutdemo.R;
import com.rhino.constraintlayoutdemo.databinding.RecvItemSimpleTextBinding;
import com.rhino.rv.base.BaseHolder;
import com.rhino.rv.base.BaseHolderData;

/**
 * @author LuoLin
 * @since Create on 2018/6/6.
 */
public class SimpleTextHolderData extends BaseHolderData {

    public String mText;

    @Override
    public int getLayoutRes() {
        return R.layout.recv_item_simple_text;
    }

    @NonNull
    @Override
    public String getHolderClassName() {
        return SimpleTextHolder.class.getName();
    }

    public static class SimpleTextHolder extends BaseHolder<SimpleTextHolderData> {

        private RecvItemSimpleTextBinding binding;

        public SimpleTextHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void bindView(SimpleTextHolderData data, int i) {
            binding.text.setText(data.mText);
        }


    }

}
