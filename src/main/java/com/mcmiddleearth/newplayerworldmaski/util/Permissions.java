package com.mcmiddleearth.newplayerworldmaski.util;

/**
 *
 * @author Jubo
 */
public enum Permissions {

    STAFF       ("npw.staff");

    private final String permissionNode;

    Permissions(String permissionNode){
        this.permissionNode = permissionNode;
    }

    public String getPermissionNode(){
        return permissionNode;
    }
}
