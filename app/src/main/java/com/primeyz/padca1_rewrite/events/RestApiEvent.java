package com.primeyz.padca1_rewrite.events;

import com.primeyz.padca1_rewrite.data.vo.CategoryVO;
import com.primeyz.padca1_rewrite.data.vo.CurrentProgramVO;
import com.primeyz.padca1_rewrite.data.vo.TopicVO;

import java.util.List;

/**
 * Created by yepyaesonetun on 5/25/18.
 **/

public class RestApiEvent {

    public static class ErrorInvokingAPIEvent{
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class currentProgramDataLoadedEvent{
        private CurrentProgramVO currentProgramVO;

        public currentProgramDataLoadedEvent(CurrentProgramVO currentProgramVO) {
            this.currentProgramVO = currentProgramVO;
        }
        public CurrentProgramVO getLoadedCurrentProgram(){return currentProgramVO;}
    }

    public static class CategoriesDataLoadedEvent {
        private List<CategoryVO> loadCategories;

        public CategoriesDataLoadedEvent(List<CategoryVO> loadCategories) {
            this.loadCategories = loadCategories;
        }

        public List<CategoryVO> getLoadCategories() {
            return loadCategories;
        }
    }

    public static class TopicsDataLoadedEvent {
        private List<TopicVO> loadTopics;

        public TopicsDataLoadedEvent(List<TopicVO> loadTopics) {
            this.loadTopics = loadTopics;
        }

        public List<TopicVO> getLoadTopics() {
            return loadTopics;
        }
    }
}
