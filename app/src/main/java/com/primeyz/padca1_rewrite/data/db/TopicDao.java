package com.primeyz.padca1_rewrite.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.primeyz.padca1_rewrite.data.vo.TopicVO;

import java.util.List;

/**
 * Created by yepyaesonetun on 6/9/18.
 **/

@Dao
public abstract class TopicDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insertTopic(TopicVO topicsVO);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long[] insertTopics(List<TopicVO> topicsVOs);

    @Query("SELECT * FROM topic")
    public abstract LiveData<List<TopicVO>> getAllTopics();

    @Query("DELETE FROM topic")
    public abstract void deleteAll();

}
