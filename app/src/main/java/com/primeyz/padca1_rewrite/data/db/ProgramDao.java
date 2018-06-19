package com.primeyz.padca1_rewrite.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.primeyz.padca1_rewrite.data.vo.ProgramVO;

import java.util.List;

/**
 * Created by yepyaesonetun on 6/9/18.
 **/

@Dao
public abstract class ProgramDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insertProgram(ProgramVO programsVO);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long[] insertPrograms(List<ProgramVO> programsVOs);

    @Query("SELECT * FROM program")
    public abstract LiveData<List<ProgramVO>> getAllPrograms();

    @Query("SELECT * FROM program where programId = :programId")
    public abstract List<ProgramVO> getProgramsById(String programId);

    @Query("DELETE FROM program")
    public abstract void deleteAll();
}
