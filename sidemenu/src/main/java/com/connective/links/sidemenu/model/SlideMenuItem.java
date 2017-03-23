package com.connective.links.sidemenu.model;

import com.connective.links.sidemenu.interfaces.Resourceble;

/**
 * Created by Konstantin on 23.12.2014.
 */
public class SlideMenuItem implements Resourceble {
    private String name,menu_name;
    private int imageRes;

    public SlideMenuItem(String name, int imageRes,String menu_name) {
        this.name = name;
        this.imageRes = imageRes;
        this.menu_name=menu_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageRes() {
        return imageRes;
    }
    public String getMenu_name(){
        return menu_name;
    }
    public void setMenu_name(String menu_name){
        this.menu_name=menu_name;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
