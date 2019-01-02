package com.thinker.vdongthinker.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.thinker.vdongthinker.R;
import com.thinker.vdongthinker.adapter.NewReleaseImgAdapter;
import com.thinker.vdongthinker.base.BasePresenterActivity;
import com.thinker.vdongthinker.presenter.NewReleasePresenter;
import com.thinker.vdongthinker.tool.PostPermission;
import com.thinker.vdongthinker.ui.dialog.ActionSheetDialog;
import com.thinker.vdongthinker.view.NewReleaseView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by zt on 2018/5/8.
 */

public class NewReleaseActivity extends BasePresenterActivity<NewReleasePresenter> implements NewReleaseView,NewReleaseImgAdapter.onAddPicClickListener ,View.OnClickListener {

    private TextView tv_title,tv_function ;
    private ImageView iv_back,iv_search;
    private LinearLayout ll_pic ;
    private LinearLayout ll_video ;
    private EditText et_content ;
    private RecyclerView rv_img ,rv_hot,rv_join;

//    private UploadManager uploadManager;
    private List<LocalMedia> selectList ;
    private int themeId;
    private int maxPicSelectNum = 9;       //图片最大数量选择
    private int maxVideoSelectNum = 1;     //视频最大数量选择
    private int aspect_ratio_x = 16 ;
//    private int aspect_ratio_y = 9 ;   //默认剪裁0：0  ，也可以设置成1：1 ；3：4 ；3：2 ；16：9
    private NewReleaseImgAdapter adapter;
    private String gambit_name  ;
    private String gambit_id ;
    /*帖子类型  1是图片类型 ，2是视频类型*/
    private static int TIETYPE;

    private String picType = "file" ;
    private String videoType = "video" ;
    private final static int REQUEST_CODE = 1005 ;
    private GridLayoutManager manager;


    @Override
    public void initData() {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("发布");
        tv_function = findViewById(R.id.tv_function);
        tv_function.setVisibility(View.VISIBLE);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setVisibility(View.VISIBLE);
        iv_search = findViewById(R.id.iv_search);
        iv_search.setVisibility(View.VISIBLE);
        ll_pic = findViewById(R.id.ll_pic);
        ll_video = findViewById(R.id.ll_video);
        tv_title = findViewById(R.id.tv_title);
        et_content = findViewById(R.id.et_content);
        rv_img = findViewById(R.id.rv_img);
        rv_hot = findViewById(R.id.rv_hot);
        rv_join = findViewById(R.id.rv_join);

        iv_back.setOnClickListener(onClick);
        iv_search.setOnClickListener(onClick);
        tv_function.setOnClickListener(onClick);
        ll_pic.setOnClickListener(onClick);
        ll_video.setOnClickListener(onClick);


        selectList = new ArrayList<>() ;
        /*初始化recy*/
        manager = new GridLayoutManager(this, 3);
        rv_img.setLayoutManager(manager);

        adapter = new NewReleaseImgAdapter(this,this);
        adapter.setList(selectList);
        adapter.setSelectMax(maxPicSelectNum);
        rv_img.setAdapter(adapter);
        rv_img.setVisibility(View.GONE);

        /*上传文件管理*/
//        uploadManager = UploadManager.getInstance();
//        UploadManager.getInstance().getThreadPool().setCorePoolSize(3);    //设置线程数量
//        uploadManager.getThreadPool().getExecutor().addOnAllTaskEndListener(this);

        themeId = R.style.picture_default_style;

        adapter.setOnItemClickListener(new NewReleaseImgAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).themeStyle(themeId).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(NewReleaseActivity.this).themeStyle(themeId).openExternalPreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
//                            PictureSelector.create(this).externalPictureVideo(media.getPath());
                            break;
                    }
                }
            }
        });

//        /*字数限制监听*/
//        et_content.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//                int num = 300 ;
//                int num1 = num - charSequence.length() ;
//                String str_num = String.valueOf(num1);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
    }

    @Override
    public void initPresenter() {
        mPresenter = new NewReleasePresenter();
        mPresenter.init(this ,this ,this);
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_release;
    }
    /*线程上传管理结束*/
//    @Override
//    public void onAllTaskEnd() {
//
//    }
//    /*录制完成*/
//    @Override
//    public void onRecordFinished(String path) {
//        LocalMedia bean = new LocalMedia() ;
//        bean.setPath(path);
//        bean.setPictureType("video/mp4");
//        selectList.add(bean) ;
//        adapter.setList(selectList);
//        adapter.notifyDataSetChanged();
//    }

    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.iv_back :
                    finish();
                    break;
                /*拍照*/
                case R.id.ll_pic :
                    TIETYPE = 1;
                    showWindows();
                    break;
                /*录像*/
                case R.id.ll_video :
                    /*底部弹窗选择本地或者拍摄*/
                    TIETYPE = 2;
                    showWindows();
                    break;
                /*跳转话题列表*/
//                case R.id.rel_click_hua :
//                    Intent intent = new Intent(NewEditSendActivity.this ,GambitActivity.class);
//                    startActivityForResult(intent ,REQUEST_CODE);
//                    break;
                /*发布*/
                case R.id.tv_send :

                    break;
                case R.id.iv_search:
                    startActivity(new Intent(NewReleaseActivity.this,SearchActivity.class));
                    break;
            }
        }
    };

    private void showWindows() {
        new ActionSheetDialog(this)
                .builder()
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .addSheetItem("拍摄", ActionSheetDialog.SheetItemColor.Black,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                takePermission("camera") ;
                            }
                        })
                .addSheetItem("从手机中选择", ActionSheetDialog.SheetItemColor.Black,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                takePermission("local") ;
                            }
                        }).show();
    }

    private void takePermission(final String type) {
        PostPermission.checkSignPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, new PostPermission.OnPermissionCallBack() {
            @Override
            public void onDeny(String permission, int position) {
                android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(NewReleaseActivity.this)
                        .setTitle("温馨提示：")
                        .setMessage("您拒绝了存储权限，部分功能受限，请您根据提示打开或者在设置中手动打开权限")
                        .setCancelable(true)
                        .create();
                alertDialog.show();
            }

            @Override
            public void onGuarantee(String permission, int position) {
                postCreame(type);
            }
        });
    }
    private void postCreame(final String type) {
        PostPermission.checkSignPermission(this, Manifest.permission.CAMERA, new PostPermission.OnPermissionCallBack() {
            @Override
            public void onDeny(String permission, int position) {
                android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(NewReleaseActivity.this)
                        .setTitle("温馨提示：")
                        .setMessage("您拒绝了相机权限，会影响部分功能使用，请您根据提示打开或者在设置中手动打开权限")
                        .setCancelable(true)
                        .create();
                alertDialog.show();
            }

            @Override
            public void onGuarantee(String permission, int position) {
                if(type.equals("local")) {
                    if(TIETYPE == 1){
                        takePic(type);
                    }else{
                        jionSelectionFile(PictureMimeType.ofVideo() ,1);
                    }
                }else {
//                    postAUDIO(type);
                    if(TIETYPE == 1){
                        PictureSelector.create(NewReleaseActivity.this)
                                .openCamera(PictureMimeType.ofImage())
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                    }else{
                        PictureSelector.create(NewReleaseActivity.this)
                                .openCamera(PictureMimeType.ofVideo())
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                    }

                }
            }
        });
    }

//    /*拍视频申请权限*/
//    public void takeVideo(){
//        if(TIETYPE==3){
//            TIETYPE = 3 ;
//            /*保证视频只有一个*/
//            if(selectList!=null&&selectList.size()>0){
//                selectList.clear();
//            }
//            goRecordVideo();
//        }else{
//            if(selectList!=null&&selectList.size()>0) {
//                new AlertDialog(NewEditSendActivity.this).builder().setTitle("提示")
//                        .setMsg("切换视频，已选择的图片会被删除")
//                        .setPositiveButton("确认", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                            /*切换*/
//                                selectList.clear();
//                                TIETYPE = 3 ;
//                                goRecordVideo();
//                            }
//                        }).setNegativeButton("取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                }).show();
//            }else {
//                TIETYPE = 3 ;
//                /*保证视频只有一个*/
//                if(selectList!=null&&selectList.size()>0){
//                    selectList.clear();
//                }
//                goRecordVideo();
//            }
//        }
//    }
//    /*视频拍摄*/
//    private void goRecordVideo() {
//        VideoRecordActivity.startToActivity(this, this);
//    }

    /*拍照片*/
    public void takePic(String type){
        /*等于7表示他选的一直是图片，一直可以选*/
//        if(TIETYPE==2){
            jionSelectionFile(PictureMimeType.ofImage() ,maxPicSelectNum);
            TIETYPE = 2 ;
//        }else{
//            if(selectList!=null&&selectList.size()>0){
//                new AlertDialog(this).builder().setTitle("提示")
//                        .setMsg("切换图片，已选择的视频会被删除")
//                        .setPositiveButton("确认", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                /*切换*/
//                                selectList.clear();
//                                TIETYPE = 2 ;
//                                jionSelectionFile(PictureMimeType.ofImage() ,maxPicSelectNum);
//                            }
//                        }).setNegativeButton("取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                }).show();
//            }else {
//                jionSelectionFile(PictureMimeType.ofImage() ,maxPicSelectNum);
//                TIETYPE = 2 ;
//            }
//        }
    }

    /*根据类型进入对应的选择器*/
    private void jionSelectionFile(int fileType ,int maxSelectNum){
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                .openGallery(fileType)// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(themeId)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(maxSelectNum)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(3)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .previewVideo(true)// 是否可预览视频
                .isCamera(false)// 是否显示拍照按钮
                .isZoomAnim(false)// 图片列表点击 缩放效果 默认true
                //.imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(false)// 是否裁剪
                .compress(false)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                //.compressSavePath(getPath())//压缩图片保存地址
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(false)// 是否圆形裁剪
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
                .selectionMedia(selectList)// 是否传入已选图片
                //.isDragFrame(false)// 是否可拖动裁剪框(固定)
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled(true) // 裁剪是否可旋转图片
                //.scaleEnabled(true)// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    for (LocalMedia media : selectList) {
                        Log.i("图片-----》", media.getPath());
                    }
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    rv_img.setVisibility(View.VISIBLE);
                    break;

                case REQUEST_CODE :
                    if(data!=null){
                        gambit_name = data.getStringExtra("gambit_name");
                        gambit_id = data.getStringExtra("gambit_id");
                    }
                    break;
            }
        }
    }

    @Override
    public void onAddPicClick() {
        takePic("local");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search:
                Intent intent = new Intent(this,SearchActivity.class);
                intent.putExtra("PAGE_TYPE",0);
                startActivity(intent);
                break;
        }
    }

//    @Override
//    public void sendMessage(Observable<String> sendObservable) {
//        sendObservable.compose(compose(this.<String>bindToLifecycle())).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(@NonNull String json) throws Exception {
//                Log.e("dongnao", "sdwwaccept: ====" + json );
//                JsonParser.getCodeCallBack(NewEditSendActivity.this ,json, new JsonParser.CallBack() {
//                    @Override
//                    public void accept(String data) {
//                        NewEditSendActivity.this.finish();
//                    }
//
//                    @Override
//                    public void reLogin() {
//
//                    }
//                });
//            }
//        });
//    }
}
