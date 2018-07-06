package com.rhino.constraintlayoutdemo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.rhino.constraintlayoutdemo.databinding.ActivityMainBinding;
import com.rhino.constraintlayoutdemo.holderdata.SimpleTextHolderData;
import com.rhino.rv.SimpleRecyclerAdapter;
import com.rhino.rv.impl.IOnClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * @author LuoLin
 * @since Create on 2018/6/5.
 */
public class MainActivity extends AppCompatActivity {

    private List<SimpleTextHolderData> mHolderDataList = new ArrayList<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        SimpleRecyclerAdapter adapter = new SimpleRecyclerAdapter();
        binding.recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        binding.recyclerView.setLayoutManager(manager);


        mHolderDataList.add(buildHolderData("Normal", NormalActivity.class));
        mHolderDataList.add(buildHolderData("Barrier", BarrierActivity.class));
        mHolderDataList.add(buildHolderData("Guideline", GuidelineActivity.class));
        mHolderDataList.add(buildHolderData("Chains", ChainsActivity.class));
        mHolderDataList.add(buildHolderData("Group", GroupActivity.class));
        mHolderDataList.add(buildHolderData("Placeholder", PlaceholderActivity.class));
        mHolderDataList.add(buildHolderData("CircularPositioning", CircularPositioningActivity.class));
        mHolderDataList.add(buildHolderData("ConstraintSet", ConstraintSetActivity.class));
        adapter.updateDataAndNotify(mHolderDataList);
    }

    private SimpleTextHolderData buildHolderData(String text, Class<?> cls) {
        SimpleTextHolderData data = new SimpleTextHolderData();
        data.mText = text;
        data.mExtraData = cls;
        data.mItemClickListener = new IOnClickListener<SimpleTextHolderData>() {
            @Override
            public void onClick(View view, SimpleTextHolderData data, int i) {
                showPage((Class<?>) data.mExtraData);
            }
        };
        return data;
    }

    private void showPage(Class<?> cls) {
        Intent intent = new Intent(getApplicationContext(), cls);
        startActivity(intent);
    }

}
