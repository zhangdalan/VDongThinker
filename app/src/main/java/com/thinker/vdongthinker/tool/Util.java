package com.thinker.vdongthinker.tool;

import android.annotation.SuppressLint;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;


import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.UUID;
import java.util.regex.Pattern;

public final class Util {
    public static int getWindowHeigh(Context paramContext) {
        WindowManager localWindowManager = (WindowManager) paramContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.heightPixels;
    }

    public static int getWindowWidth(Context paramContext) {
        WindowManager localWindowManager = (WindowManager) paramContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.widthPixels;
    }

    public static float getScreenMetrics(Context paramContext) {
        WindowManager localWindowManager = (WindowManager) paramContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.density;// 屏幕密度（0.75 / 1.0 / 1.5）
    }

    public static int getWindowDensityDpi(Context paramContext) {
        WindowManager localWindowManager = (WindowManager) paramContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
        return localDisplayMetrics.densityDpi;// 屏幕密度DPI（120 / 160 / 240）
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public static String getStamp() {
        return System.currentTimeMillis() + "";
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     * @throws NameNotFoundException
     */
    public static int getVersionCode(Context context) throws NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        return packInfo.versionCode;
    }

    /**
     * 获取版本名称
     *
     * @param context
     * @return
     * @throws NameNotFoundException
     */
    public static String getVersionId(Context context) throws NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    public static String getDeviceId(Context context) {
        android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context.getSystemService(Context
                .TELEPHONY_SERVICE);

        return tm.getDeviceId();
    }

    /**
     * 获得独一无二的Psuedo ID
     */
    public static String getUniquePsuedoID() {
        String serial = null;

        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +

                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +

                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +

                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +

                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +

                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +

                Build.USER.length() % 10; //13 位

        try {
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();
            //API>=9 使用serial号
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = "serial"; // 随便一个初始化
        }
        //使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context.getSystemService
                    (Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();

            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context
                    .WIFI_SERVICE);

            String mac = wifi.getConnectionInfo().getMacAddress();

            String versionId = getVersionId(context);

            String stamp = getStamp();

            if (TextUtils.isEmpty(versionId)) {
                versionId = "1.0";
            }

            json.put("version_id", versionId);

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider
                        .Settings.Secure.ANDROID_ID);
            }

            json.put("device_id", device_id);

            if (TextUtils.isEmpty(mac)) {
                mac = "00-00-00-00-00";
            }

            json.put("mac", mac);

            if (TextUtils.isEmpty(stamp)) {
                stamp = "123";
            }

            json.put("stamp", stamp);

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDeviceAndStamp(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context.getSystemService
                    (Context.TELEPHONY_SERVICE);

            String device_id = tm.getDeviceId();
            String stamp = getStamp();

            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider
                        .Settings.Secure.ANDROID_ID);
            }
            json.put("device_id", device_id);

            if (TextUtils.isEmpty(stamp)) {
                stamp = "123";
            }
            json.put("stamp", stamp);

            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isEmail(String paramString) {
        return Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+", Pattern.CASE_INSENSITIVE).matcher
                (paramString).matches();
    }

    public static boolean isPhone(String paramString) {
        return Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$").matcher(paramString).matches();
    }

    public static boolean isPwd(String paramString) {
        return Pattern.compile("^[a-zA-Z0-9_]{6,20}$", Pattern.CASE_INSENSITIVE).matcher(paramString).matches();
    }

    public static float dip2px(Context paramContext, float paramFloat) {
        return 0.5F + paramFloat * paramContext.getResources().getDisplayMetrics().density;
    }

    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    public static String getMD5(String str) {
        String MD5str = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] results = md5.digest(str.getBytes("UTF-8"));
            MD5str = toHexString(results);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MD5str;
    }

    private static String toHexString(byte[] b) {
        char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static boolean isBasicType(Field paramField) {
        // boolean ret = true;
        // String[] arrayOfString = new String[8];
        // arrayOfString[0] = "int";
        // arrayOfString[1] = "char";
        // arrayOfString[2] = "boolean";
        // arrayOfString[3] = "long";
        // arrayOfString[4] = "double";
        // arrayOfString[5] = "float";
        // arrayOfString[6] = "byte";
        // arrayOfString[7] = "short";
        // String str = paramField.getName();
        // int k = 0;
        // for (int i = 0; ; i++)
        // {
        // if ((i >= arrayOfString.length) || (str.equals(arrayOfString[i])))
        // {
        // if (k > 7)
        // ret = false;
        // return ret;
        // }
        // k++;
        // }
        return true;
    }

    public static boolean IsEmpty(String paramString) {
        boolean ret = true;
        if ((paramString != null) && (paramString.length() > 0))
            ret = false;

        return ret;
    }

    public static int calculateInSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2) {
        int j = paramOptions.outHeight;
        int k = paramOptions.outWidth;
        int i = 1;
        if ((j > paramInt2) || (k > paramInt1)) {
            j /= 2;
            k /= 2;
            while ((j / i > paramInt2) && (k / i > paramInt1))
                i *= 2;
        }
        return i;
    }

    public static Bitmap decodeSampledBitmapFromFilePath(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = calculateInSampleSize(options, width, height);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * ��û��http://��URL��ǰ�����http://
     *
     * @param str
     * @return
     */
    public static String checkUrl(String str) {
        if (str.contains("http://"))
            return str;
        return "http://" + str;
    }

    /**
     * �ж��Ƿ�������
     *
     * @param value
     * @return
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     * 网络连接的状态״
     *
     * @return 链接正常true断开连接false
     */
//	public boolean GetIsConnected()
//	{
//		boolean ret = false;
//		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService("connectivity");
//		if (connectivityManager != null)
//		{
//			NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//			if ((networkInfo != null) && networkInfo.isAvailable())
//				ret = true;
//		}
//		return ret;
//	}

    /**
     * 判断当前是否在wifi状态下
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }


    public static void hideSoftInputFromWindow(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * install apk
     *
     * @param path apk路径
     */
    public static void installAPK(Context context, String path) {
        Intent intent = new Intent();
        // 安装APK所要配置的内容
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(new File(path)), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * 获取系统通知权限是否开启
     */
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    @SuppressLint("NewApi")
    public static boolean isNotificationEnabled(Context context) {

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);

        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;
        Class appOpsClass = null;

        /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());

            Method checkOpNoThrowMethod =  appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class);

            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (Integer) opPostNotificationValue.get(Integer.class);

            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void buttonBeyondKeyboardLayout(final View root, final View button) {
        // 监听根布局的视图变化
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect rect = new Rect();
                        // 获取内容布局在窗体的可视区域
                        root.getWindowVisibleDisplayFrame(rect);
                        // 获取内容布局在窗体的不可视区域高度(被其他View遮挡的区域高度)
                        int rootInvisibleHeight = root.getHeight() - rect.bottom;
                        // 若不可视区域高度大于100，则键盘显示
                        if (rootInvisibleHeight > 100) {
                            int[] location = new int[2];
                            // 获取须顶上去的控件在窗体的坐标
                            button.getLocationInWindow(location);
                            // 计算内容滚动高度，使button在可见区域
                            int buttonHeight = (location[1]
                                    + button.getHeight()) - rect.bottom;
                            root.scrollTo(0,buttonHeight);
                        } else {
                            // 键盘隐藏
                            root.scrollTo(0, 0);
                        }
                    }
                });
    }
}