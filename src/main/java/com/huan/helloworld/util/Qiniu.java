package com.huan.helloworld.util;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;
import org.json.JSONException;

import java.io.File;

public class Qiniu {
    public static final String QINIU_IMAGE_URL = "http://7xnojy.com1.z0.glb.clouddn.com/";
    public static final String ACCESS_KEY = "0uIMPAgD2AdDnc6I4wvZv44mOzKif4RKUBvI5kvZ";
    public static final String SECRET_KEY = "yJ7d41vbv9OdGCdAOb7zTDu3Sthjkw5RzldWnTo9";
    public static final String BUCKET_NAME = "pointhinker";


    public static PutRet uploadFile(File file, String key) {
        Mac mac = new Mac(ACCESS_KEY, SECRET_KEY);
        PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
        try {
            String upToken = putPolicy.token(mac);
            PutExtra putExtra = new PutExtra();
            PutRet putRet = IoApi.putFile(upToken, key, file, putExtra);
            return putRet;
        } catch (AuthException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;


    }
    public static void main(String[] args){
        String fileName="/Users/happy/Documents/slides/2014-analyst-meeting-financial-presentation-0.jpg";
        File file=new File(fileName);
        PutRet putRet = Qiniu.uploadFile(file, "2014-analyst-meeting-financial-presentation-0.jpg");
    }

}
