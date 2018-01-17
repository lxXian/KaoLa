package com.duhui.xian.kaola.uiframe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duhui.xian.kaola.R;

/**
 * Created by min on 2017/10/31.
 *(1)android.view.GestureDetector.OnGestureListener接口
 * 
 */

public class homeFragment extends Fragment{


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v =inflater.inflate(R.layout.fragement_home,container,false);
        return v;
    }


}
