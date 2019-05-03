package org.group38.frameworks.sceneHandling;

import org.group38.frameworks.Strings;

public class SceneInfo {
    private final String sceneTitle;
    private final String viewpath;



    public SceneInfo(String title, String viewpath){
        this.sceneTitle = title;
        this.viewpath = Strings.requireNonNullAndNotEmpty(viewpath);
    }

    public String getSceneTitle() {
        return sceneTitle;
    }

    public String getViewpath() {
        return viewpath;
    }




    @Override
    public String toString() {
        return "SceneInfo{" +
                "title='" + sceneTitle + '\'' +
                ", viewpath='" + viewpath + '\'' +
                '}';
    }
}

