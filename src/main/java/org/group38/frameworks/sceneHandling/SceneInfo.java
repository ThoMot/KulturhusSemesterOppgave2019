package org.group38.frameworks.sceneHandling;

import org.group38.frameworks.Strings;

public class SceneInfo {
    private final String sceneTitle;
    private final String viewpath;

    /** SceneInfo method sets the title and the path to the new scenes */
    public SceneInfo(String title, String viewpath){
        this.sceneTitle = title;
        this.viewpath = Strings.requireNonNullAndNotEmpty(viewpath);
    }

    /** getter for sceneTitle */
    public String getSceneTitle() {
        return sceneTitle;
    }

    /** getter for viewPath*/
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

