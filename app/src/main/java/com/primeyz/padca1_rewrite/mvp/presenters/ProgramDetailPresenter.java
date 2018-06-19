package com.primeyz.padca1_rewrite.mvp.presenters;

import com.primeyz.padca1_rewrite.data.model.SeriesModal;
import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;
import com.primeyz.padca1_rewrite.data.vo.ProgramVO;
import com.primeyz.padca1_rewrite.mvp.views.ProgramDetailView;

/**
 * Created by yepyaesonetun on 6/19/18.
 **/

public class ProgramDetailPresenter extends BasePresenter<ProgramDetailView> {

    private boolean check = true;
    private ProgramVO programVO;
    private CurrentProgramVO currentProgramVO;

    public ProgramDetailPresenter(ProgramDetailView mView) {
        super(mView);
    }

    public void onFinishUISetUp(String programData, String categoryId) {
        checkID(programData, categoryId);
        if (!check) mView.displayCurrentProgramData(currentProgramVO);
        else mView.displayProgramData(programVO);
    }

    private void checkID(String programData, String categoryId) {
        check = programData.equalsIgnoreCase("CATEGORY");
        SeriesModal model = SeriesModal.getObjInstance();
        if (check && categoryId != null) {
            programVO = model.getProgramVO(categoryId);
        } else {
            currentProgramVO = model.getCurrentProgramVO();
        }
    }
}
