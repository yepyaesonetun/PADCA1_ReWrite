package com.primeyz.padca1_rewrite.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.primeyz.padca1_rewrite.data.vo.CategoryVO;
import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;
import com.primeyz.padca1_rewrite.data.vo.ProgramVO;
import com.primeyz.padca1_rewrite.data.vo.SessionVO;
import com.primeyz.padca1_rewrite.data.vo.TopicVO;

/**
 * Created by yepyaesonetun on 6/9/18.
 **/

@Database(entities = {
        CategoryVO.class, CurrentProgramVO.class, ProgramVO.class,
        SessionVO.class, TopicVO.class
        }, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "PADC-SH-AC.DB";

    private static AppDatabase INSTANCE;

    // define DAOs
    public abstract CategoryDao categoriesDao();

    public abstract CurrentProgramDao currentProgramDao();

    public abstract ProgramDao programDao();

    public abstract SessionDao sessionDao();

    public abstract TopicDao topicDao();


    public static AppDatabase getNewsDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                            .allowMainThreadQueries() //Remove this after testing. Access to DB should always be from background thread.
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
