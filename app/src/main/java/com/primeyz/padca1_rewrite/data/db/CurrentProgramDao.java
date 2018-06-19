package com.primeyz.padca1_rewrite.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;

import java.util.List;

/**
 * Created by yepyaesonetun on 6/9/18.
 **/

@Dao
public abstract class CurrentProgramDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insertCurrentProgram(CurrentProgramVO currentProgramsVO);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long[] insertCurrentPrograms(List<CurrentProgramVO> currentProgramsVOs);

    @Query("SELECT * FROM currentprogram")
    public abstract LiveData<List<CurrentProgramVO>> getAllCurrentPrograms();

    @Query("DELETE FROM currentprogram")
    public abstract void deleteAll();
}
