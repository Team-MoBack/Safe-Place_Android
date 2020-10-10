package org.techtown.moback.model;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

public class ChartModel {

    private int rank_number;
    private Drawable profile_image;
    private String user_name;
    private String user_level;
    private int user_point;


    public ChartModel()
    {

    }

    public ChartModel(int rank_number, Drawable profile_image, String user_name, String user_level, int user_point) {
        this.rank_number = rank_number;
        this.profile_image = profile_image;
        this.user_name = user_name;
        this.user_level = user_level;
        this.user_point = user_point;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        ChartModel other = (ChartModel) obj;
        return user_name.equals(other.user_name);
    }

    public static DiffUtil.ItemCallback<ChartModel> DIFF_CALLBACK = new  DiffUtil.ItemCallback<ChartModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull ChartModel oldItem, @NonNull ChartModel newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull ChartModel oldItem, @NonNull ChartModel newItem) {
            return oldItem.equals(newItem);
        }

    };


    public String getUser_point() {
        return user_point+"";
    }

    public void setUser_point(int user_point) {
        this.user_point = user_point;
    }

    public String getRank_number() {
        return rank_number+"";
    }

    public void setRank_number(int rank_number) {
        this.rank_number = rank_number;
    }

    public Drawable getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(Drawable profile_image) {
        this.profile_image = profile_image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_level() {
        return user_level;
    }

    public void setUser_level(String user_level) {
        this.user_level = user_level;
    }
}
