package com.primeyz.padca1_rewrite.mvp.views;

import com.primeyz.padca1_rewrite.data.vo.BaseVO;

import java.util.List;

/**
 * Created by yepyaesonetun on 6/19/18.
 **/

public interface SeriesListFragView extends BaseView{

    void displayData(List<BaseVO> baseVOList);

    void launchCurrentProgram();

    void launchProgram(String id);

}
