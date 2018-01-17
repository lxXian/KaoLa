package com.duhui.xian.kaola.uiframe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.duhui.xian.kaola.R;

public class MainActivity extends FragmentActivity {
    private RadioButton homeItemRadioButton;
    private RadioButton bookshelfItemRadioButton;
    private RadioButton findItemRadioButton;
    private RadioButton mineItemRadioButton;
    private homeFragment fragmentHome;
    private bookshelfFragment fragmentBookshelf;
    private findFragment fragmentFind;
    private mineFragment fragmentMine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeItemRadioButton=(RadioButton)findViewById(R.id.menuHome);
        bookshelfItemRadioButton=(RadioButton)findViewById(R.id.menuBookshelf);
        findItemRadioButton=(RadioButton)findViewById(R.id.menuFind);
        mineItemRadioButton=(RadioButton)findViewById(R.id.menuMine);

        fragmentHome=new homeFragment();
        fragmentBookshelf =new bookshelfFragment();
        fragmentFind = new findFragment();
        fragmentMine = new mineFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.child_content,fragmentHome).commit();

        homeItemRadioButton.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        bookshelfItemRadioButton.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        findItemRadioButton.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        mineItemRadioButton.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
    }

    private class MyOnCheckedChangeListener implements OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
            FragmentTransaction f=getSupportFragmentManager().beginTransaction();
            Fragment fragment=fragmentHome;
            switch (buttonView.getId()){
                case R.id.menuHome:
                    fragment=fragmentHome;
                    break;
                case R.id.menuBookshelf:
                    fragment=fragmentBookshelf;
                    break;
                case R.id.menuFind:
                    fragment=fragmentFind;
                    break;
                case R.id.menuMine:
                    fragment=fragmentMine;
                    break;
            }
            if(isChecked){
                buttonView.setTextColor(getResources().getColor(R.color.myGreen));
                f.add(R.id.child_content,fragment);
            }else{
                buttonView.setTextColor(getResources().getColor(R.color.myTextGray));
                f.remove(fragment);
            }
            f.commit();

        }
    }








}


