package com.rhino.constraintlayoutdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rhino.constraintlayoutdemo.databinding.GroupBinding;


/**
 * @author LuoLin
 * @since Create on 2018/6/5.
 */
public class GroupActivity extends AppCompatActivity {

    private GroupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group);
        binding = DataBindingUtil.setContentView(this, R.layout.group);

        binding.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.group.setVisibility(View.VISIBLE == binding.group.getVisibility() ? View.GONE : View.VISIBLE);
            }
        });
    }
}
