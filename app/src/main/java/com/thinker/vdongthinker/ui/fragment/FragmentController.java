package com.thinker.vdongthinker.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

public class FragmentController {

    private static boolean isReload;      //避免重复加载
    public static FragmentController controller ;
    private final FragmentManager fm;
    private int contentId;
    public static ArrayList<Fragment> fgList;

    public static FragmentController getInstance (FragmentActivity mActivity , int contentId , boolean isReload,ArrayList<Fragment> fgList){
        FragmentController.isReload = isReload;
        FragmentController.fgList = fgList;
        if(controller == null){
            controller = new FragmentController(mActivity ,contentId) ;
        }
        return controller ;
    }
    public FragmentController(FragmentActivity mActivity, int contentId) {
        this.contentId = contentId ;
        fm = mActivity.getSupportFragmentManager();
        initFragment();
    }
    /*加载四个模块*/
    private void initFragment() {
//        fgList = new ArrayList<>();
        if(!isReload){
//            fgList.add(new FragmentIndex()) ;
//            fgList.add(new FragmentCourse()) ;
//            fgList.add(new FragmentAgency()) ;
//            fgList.add(new DFragment()) ;

            FragmentTransaction ft = fm.beginTransaction();
            for(int i = 0 ;i<fgList.size() ;i++){
                ft.add(contentId ,fgList.get(i) ,String.valueOf(i)) ;
            }
            ft.commit() ;
        }else {
            for(int i = 0 ;i<5 ;i++){
                fgList.add(fm.findFragmentByTag(String.valueOf(i))) ;
            }
        }
    }

    /*展示某个模块*/
    public void showFragment(int position){
        hideFragment() ;
        Fragment fragment = fgList.get(position);
        FragmentTransaction ft = fm.beginTransaction();
        ft.show(fragment) ;
        ft.commitAllowingStateLoss() ;
    }
    /*隐藏某一个模块*/
    public void hideFragment(){
        FragmentTransaction ft = fm.beginTransaction();
        for(Fragment fragment : fgList){
            if(fragment!=null){
                ft.hide(fragment) ;
            }
        }
        ft.commitAllowingStateLoss() ;
    }
}