package com.rhino.constraintlayoutdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rhino.constraintlayoutdemo.databinding.PlaceholderBinding;


/**
 * @author LuoLin
 * @since Create on 2018/6/5.
 */
public class PlaceholderActivity extends AppCompatActivity {

    private PlaceholderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.placeholder);

        binding.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.template.templateBanner.setContentId(R.id.text);
            }
        });
    }
}
