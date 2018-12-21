package com.thinker.vdongthinker.tool;

import android.content.Context;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;

/**
 * Created by zjw on 2018/12/19
 */

public class PostPermission {

    public interface OnPermissionCallBack{
        void onDeny(String permission, int position) ;
        void onGuarantee(String permission, int position) ;
    }

    public static void checkSignPermission(Context context, String permissionItems , final OnPermissionCallBack callBack){
        HiPermission.create(context).checkSinglePermission(permissionItems, new PermissionCallback() {
            @Override
            public void onClose() {
            }

            @Override
            public void onFinish() {
            }

            @Override
            public void onDeny(String permission, int position) {
                callBack.onDeny(permission ,position);
            }

            @Override
            public void onGuarantee(String permission, int position) {
                callBack.onGuarantee(permission ,position);
            }
        });
    }
}
