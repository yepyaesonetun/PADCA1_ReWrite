package com.primeyz.padca1_rewrite.mvp.views;

import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;
import com.primeyz.padca1_rewrite.data.vo.ProgramVO;

/**
 * Created by yepyaesonetun on 6/19/18.
 **/

public interface ProgramDetailView extends BaseView {

    void displayCurrentProgramData(CurrentProgramVO currentProgramVO);

    void displayProgramData(ProgramVO programVO);
}
