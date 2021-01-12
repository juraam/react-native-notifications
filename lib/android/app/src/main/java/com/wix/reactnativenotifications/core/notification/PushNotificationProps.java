package com.wix.reactnativenotifications.core.notification;

import android.os.Bundle;

import org.json.JSONObject;

public class PushNotificationProps {

    protected Bundle mBundle;

    private JSONObject dataJson;

    public PushNotificationProps(Bundle bundle) {
        mBundle = bundle;
        String json = mBundle.getString("data");
        try {
            dataJson = new JSONObject(json);
        } catch (Exception exception) { }
    }

    public String getTitle() {
        return getBundleStringFirstNotNull("gcm.notification.title", "title");
    }

    public String getBody() {
        return getBundleStringFirstNotNull("gcm.notification.body", "body");
    }

    public String getSound() {
        return getBundleStringFirstNotNull("gcm.notification.sound", "sound");
    }

    public Bundle asBundle() {
        return (Bundle) mBundle.clone();
    }

    public boolean isFirebaseBackgroundPayload() {
        return mBundle.containsKey("google.message_id") && !mBundle.containsKey("title");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(1024);
        for (String key : mBundle.keySet()) {
            sb.append(key).append("=").append(mBundle.get(key)).append(", ");
        }
        return sb.toString();
    }

    protected PushNotificationProps copy() {
        return new PushNotificationProps((Bundle) mBundle.clone());
    }

    private String getBundleStringFirstNotNull(String key1, String key2) {
        String result = mBundle.getString(key1);
        return result == null ? mBundle.getString(key2) : result;
    }
}
