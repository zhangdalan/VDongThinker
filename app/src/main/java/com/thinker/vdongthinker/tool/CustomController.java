package com.thinker.vdongthinker.tool;

import android.content.Context;
import android.widget.ImageView;

import com.xiao.nicevideoplayer.NiceVideoPlayerController;

public class CustomController extends NiceVideoPlayerController {
    public CustomController(Context context) {
        super(context);
    }

    @Override
    public void setTitle(String s) {

    }

    @Override
    public void setImage(int i) {

    }

    @Override
    public ImageView imageView() {
        return null;
    }

    @Override
    public void setLenght(long l) {

    }

    @Override
    protected void onPlayStateChanged(int i) {

    }

    @Override
    protected void onPlayModeChanged(int i) {

    }

    @Override
    protected void reset() {

    }

    @Override
    protected void updateProgress() {

    }

    @Override
    protected void showChangePosition(long l, int i) {

    }

    @Override
    protected void hideChangePosition() {

    }

    @Override
    protected void showChangeVolume(int i) {

    }

    @Override
    protected void hideChangeVolume() {

    }

    @Override
    protected void showChangeBrightness(int i) {

    }

    @Override
    protected void hideChangeBrightness() {

    }
}
