package com.thinker.vdongthinker.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.DateUtils;
import com.luck.picture.lib.tools.StringUtils;
import com.thinker.vdongthinker.R;

import java.util.ArrayList;
import java.util.List;

public class NewReleaseImgAdapter extends RecyclerView.Adapter<NewReleaseImgAdapter.ViewHolder> {
    public static final int TYPE_CAMERA = 1;
    public static final int TYPE_PICTURE = 2;
    private LayoutInflater mInflater;
    private List<LocalMedia> list = new ArrayList<>();
    private int selectMax = 9;
    private Context context;
    /**
     * 点击添加图片跳转
     */
    private onAddPicClickListener mOnAddPicClickListener;

    public interface onAddPicClickListener {
        void onAddPicClick();
    }

    public NewReleaseImgAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    public NewReleaseImgAdapter(Context context, onAddPicClickListener mOnAddPicClickListener) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.mOnAddPicClickListener = mOnAddPicClickListener;
    }

    public void setSelectMax(int selectMax) {
        this.selectMax = selectMax;
    }

    public void setList(List<LocalMedia> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
//        LinearLayout ll_del;
//        TextView tv_duration;
//        ProgressPieView mCiv;

        public ViewHolder(View view) {
            super(view);
            mImg = (ImageView) view.findViewById(R.id.iv_photo);
//            ll_del = (LinearLayout) view.findViewById(R.id.ll_del);
//            tv_duration = (TextView) view.findViewById(R.id.tv_duration);
//            mCiv = (ProgressPieView) view.findViewById(R.id.civ);
        }

//        public void refresh(UploadInfo uploadInfo) {
//            if (uploadInfo.getState() == DownloadManager.NONE) {
//                mCiv.setText("请上传");
//            } else if (uploadInfo.getState() == UploadManager.ERROR) {
//                mCiv.setText("错误");
//            } else if (uploadInfo.getState() == UploadManager.WAITING) {
//                mCiv.setText("等待");
//            } else if (uploadInfo.getState() == UploadManager.FINISH) {
//                mCiv.setText("成功");
//            } else if (uploadInfo.getState() == UploadManager.UPLOADING) {
//                mCiv.setProgress((int) (uploadInfo.getProgress() * 100));
//                mCiv.setText((Math.round(uploadInfo.getProgress() * 10000) * 1.0f / 100) + "%");
//            }
//        }
//        public void finish() {
//            mCiv.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemCount() {
        if (list.size() < selectMax) {
            return list.size()+1;
        } else {
            return list.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isShowAddItem(position)) {
            return TYPE_CAMERA;
        } else {
            return TYPE_PICTURE;
        }
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_photo, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    private boolean isShowAddItem(int position) {
        int size = list.size() == 0 ? 0 : list.size();
        return position == size;
    }


    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {

        //少于9张，显示继续添加的图标
        if (getItemViewType(position) == TYPE_CAMERA) {
            viewHolder.mImg.setImageResource(R.mipmap.img_add);
            viewHolder.mImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnAddPicClickListener.onAddPicClick();
                }
            });
            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) viewHolder.mImg.getLayoutParams();
            int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
            //获取当前控件的布局对象
            params.height=(widthPixels-20)/3;//设置当前控件布局的高度width是屏幕宽度
            viewHolder.mImg.setLayoutParams(params);//将设置好的布局参数应用到控件中
//            viewHolder.ll_del.setVisibility(View.INVISIBLE);
        } else {
//            viewHolder.ll_del.setVisibility(View.VISIBLE);
//            viewHolder.ll_del.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int index = viewHolder.getAdapterPosition();
//                    // 这里有时会返回-1造成数据下标越界,具体可参考getAdapterPosition()源码，
//                    // 通过源码分析应该是bindViewHolder()暂未绘制完成导致，知道原因的也可联系我~感谢
//                    if (index != RecyclerView.NO_POSITION) {
//                        list.remove(index);
//                        notifyItemRemoved(index);
//                        notifyItemRangeChanged(index, list.size());
//                    }
//                }
//            });
            LocalMedia media = list.get(position);
            int mimeType = media.getMimeType();
            String path = "";
            if (media.isCut() && !media.isCompressed()) {
                // 裁剪过
                path = media.getCutPath();
            } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
                // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                path = media.getCompressPath();
            } else {
                // 原图
                path = media.getPath();
            }
            // 图片
            if (media.isCompressed()) {

            }


//            int pictureType = PictureMimeType.isPictureType(media.getPictureType());
//            if (media.isCut()) {
//
//            }
//            long duration = media.getDuration();
//            viewHolder.tv_duration.setVisibility(pictureType == PictureConfig.TYPE_VIDEO
//                    ? View.VISIBLE : View.GONE);
//            if (mimeType == PictureMimeType.ofAudio()) {
//                viewHolder.tv_duration.setVisibility(View.VISIBLE);
//                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.picture_audio);
//                StringUtils.modifyTextViewDrawable(viewHolder.tv_duration, drawable, 0);
//            } else {
//                Drawable drawable = ContextCompat.getDrawable(context, R.drawable.video_icon);
//                StringUtils.modifyTextViewDrawable(viewHolder.tv_duration, drawable, 0);
//            }
//            viewHolder.tv_duration.setText(DateUtils.timeParse(duration));
//            if (mimeType == PictureMimeType.ofAudio()) {
//                viewHolder.mImg.setImageResource(R.drawable.audio_placeholder);
//            } else {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
//                        .placeholder(ContextCompat.getDrawable(context , R.color.color_f6))
                    .skipMemoryCache(true)       //不适用内存缓存
                    .diskCacheStrategy(DiskCacheStrategy.NONE);      //不使用磁盘缓存
            /*设置每张图占屏幕1/3*/
            ViewGroup.LayoutParams lp = viewHolder.mImg.getLayoutParams();
            int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
            lp.width = (widthPixels-20)/3 ;
            lp.height = (widthPixels-20)/3 ;
            viewHolder.mImg.setLayoutParams(lp);
            viewHolder.mImg.setMaxWidth(widthPixels);
            viewHolder.mImg.setMaxHeight(widthPixels);

            Glide.with(viewHolder.itemView.getContext())
                        .load(path)
                        .apply(options)
                        .into(viewHolder.mImg);
        }
        //itemView 的点击事件
            if (mItemClickListener != null) {
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int adapterPosition = viewHolder.getAdapterPosition();
                        mItemClickListener.onItemClick(adapterPosition, v);
                    }
                });
            }
        }
//    }

    protected OnItemClickListener mItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }
}
