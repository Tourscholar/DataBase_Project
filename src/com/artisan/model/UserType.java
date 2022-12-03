package com.artisan.model;

public enum UserType {
    ADMIN("시스템 관리자",0),TEACHER("선생님",1),STUDENT("학생",2);
    private String name;
    private int index;
    private UserType(String name,int index){
        this.name = name;
        this.index = index;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
