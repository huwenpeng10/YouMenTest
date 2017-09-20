package com.example.merxu.youmentest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //app key 59c08b96766613275e0000ca
    private Button share_btn ,share_btn2 ,share_lianjie_btn ,login_btn;
    public Context context;
    private UMShareAPI umShareAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        share_btn = (Button) findViewById(R.id.share_btn);
        share_btn2 = (Button) findViewById(R.id.share_btn2);
        share_lianjie_btn = (Button) findViewById(R.id.share_lianjie_btn);
        login_btn = (Button) findViewById(R.id.login_btn);
        share_btn.setOnClickListener(this);
        share_btn2.setOnClickListener(this);
        share_lianjie_btn.setOnClickListener(this);
        login_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            /*
            UMImage image = new UMImage(ShareActivity.this, "imageurl");//网络图片
            UMImage image = new UMImage(ShareActivity.this, file);//本地文件
            UMImage image = new UMImage(ShareActivity.this, R.drawable.xxx);//资源文件
            UMImage image = new UMImage(ShareActivity.this, bitmap);//bitmap文件
            UMImage image = new UMImage(ShareActivity.this, byte[]);//字节流
             */

            case R.id.share_btn://带面板
                UMImage image = new UMImage(MainActivity.this, R.drawable.com_facebook_close);
                new ShareAction(MainActivity.this)
                        .withText("hello")
                        .withMedia(image)
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.SMS)
                        .setCallback(shareListener)
                        .open();
                break;
            case R.id.share_btn2://不带面板
                UMImage image1 = new UMImage(MainActivity.this, R.drawable.ic_ab_app);
                new ShareAction(MainActivity.this)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withText("hello")//分享内容
                        .withMedia(image1)//分享图片
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.share_lianjie_btn:
                Toast.makeText(MainActivity.this,"share_lianjie_btn",Toast.LENGTH_LONG).show();
                UMImage thumb = new UMImage(MainActivity.this, R.drawable.umeng_socialize_alipay);
                UMWeb web = new UMWeb("http://dev.umeng.com/social/android/quick-integration#2_3_2");
                web.setTitle("This is music title");//标题
                web.setThumb(thumb);  //缩略图
                web.setDescription("my description");//描述
                //然后调用将这个参数设置到ShareAction中：
                new ShareAction(MainActivity.this)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withMedia(web)
                        .setCallback(shareListener)//回调监听器
                        .share();
                break;
            case R.id.login_btn:
//                Tencent.createInstance("1106115293", this).login(this,"all", (j) authListener);
                umShareAPI = UMShareAPI.get(this);
//                umShareAPI.init(context,"1105853513");
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    /**
                     * @desc 授权开始的回调
                     * @param platform 平台名称
                     */
                    @Override
                    public void onStart(SHARE_MEDIA platform) {

                    }
                    /**
                     * @desc 授权成功的回调
                    //         * @param platform 平台名称
                    //         * @param action 行为序号，开发者用不上
                    //         * @param data 用户资料返回
                     */

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
                        String uid =  map.get("uid");
                        String name =  map.get("name");
                        String gender =  map.get("gender");
                        String iconurl = map.get("iconurl");

                        Log.e("TAG","uid"+uid);
                        Log.e("TAG","name"+name);
                        Log.e("TAG","gender"+gender);
                        Log.e("TAG","iconurl"+iconurl);
                    }
                    /**
                     * @desc 授权失败的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     * @param t 错误原因
                     */
                    @Override
                    public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                        Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @desc 授权取消的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA platform, int action) {
                        Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
                    }
                });
                break;

        }
    }


    private UMAuthListener authListener =new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }
        /**
         * @desc 授权成功的回调
        //         * @param platform 平台名称
        //         * @param action 行为序号，开发者用不上
        //         * @param data 用户资料返回
         */

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }
        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(MainActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

}
