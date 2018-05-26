package com.primeyz.padca1_rewrite.network;

/**
 * Created by yepyaesonetun on 5/25/18.
 **/

public interface SimpleHabitsDataAgent {

    void loadCurrentPrograms(String token, int pageNo);

    void loadCategories(String token, int pageNo);

    void loadTopic(String token, int pageNo);
}
