package com.example.q.drawer_example;

public class list_item {
    private String id;
    private String title;
    private String content;
    private String dep;
    private String time;

    public list_item(String id, String title, String content, String dep,String time) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dep=dep;
        this.time=time;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
