package com.primeyz.padca1_rewrite.data.vo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yepyaesonetun on 5/25/18.
 **/

@Entity(tableName = "Topic")
public class TopicVO implements BaseVO{

    @PrimaryKey
    @SerializedName("topic-name")
    private @NonNull String topicName;
    @SerializedName("topic-desc")
    private String topicDesc;
    @SerializedName("icon")
    private String icon;
    @SerializedName("background")
    private String background;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
