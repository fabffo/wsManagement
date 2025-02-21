package com.ws.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {

	private int id;
    private String title;
    private String url;
    private List<MenuItem> children;

    public MenuItem(String title, String url) {
        this.title = title;
        this.url = url;
        this.children = new ArrayList<>();
    }

    public MenuItem(int id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.children = new ArrayList<>();
    }

	public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    public void addChild(MenuItem child) {
        this.children.add(child);
    }
}
