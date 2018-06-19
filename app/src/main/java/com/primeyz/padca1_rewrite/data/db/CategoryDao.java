package com.primeyz.padca1_rewrite.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.primeyz.padca1_rewrite.data.vo.CategoryVO;

import java.util.List;

/**
 * Created by yepyaesonetun on 6/9/18.
 **/

@Dao
public abstract class CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insertCategory(CategoryVO categoriesVO);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long[] insertCategories(List<CategoryVO> categoriesVOs);

    @Query("SELECT * FROM categories")
    public abstract LiveData<List<CategoryVO>> getAllCategories();

    @Query("DELETE FROM categories")
    public abstract void deleteAll();
}
