package com.primeyz.padca1_rewrite.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.primeyz.padca1_rewrite.data.vo.SessionVO;

import java.util.List;

/**
 * Created by yepyaesonetun on 6/9/18.
 **/

@Dao
public abstract class SessionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insertSession(SessionVO sessionsVO);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long[] insertSessions(List<SessionVO> sessionsVOs);

    @Query("SELECT * FROM session")
    public abstract LiveData<List<SessionVO>> getAllSessions();

    @Query("DELETE FROM session")
    public abstract void deleteAll();

}
