package org.group38.kulturhus.sceneHandling;

import org.group38.frameworks.Strings;

public class SceneInfo {
    private final String title;
    private final String viewpath;



    public SceneInfo(String title, String viewpath){
        this.title = title;
        this.viewpath = Strings.requireNonNullAndNotEmpty(viewpath);
    }

    public String getTitle() {
        return title;
    }

    public String getViewpath() {
        return viewpath;
    }


    @Override
    public String toString() {
        return "SceneInfo{" +
                "title='" + title + '\'' +
                ", viewpath='" + viewpath + '\'' +
                '}';
    }
}

