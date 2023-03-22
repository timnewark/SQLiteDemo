package com.example.sqlitedemo;

public class BridgeModel{
//declarations go here
    private int asset_id;
    private String asset_ELR;
    private String asset_routeName;
    private int asset_spans;
    private String asset_type;
    private String asset_status;
    private String asset_OS;
    private String asset_county;
    private boolean isActive;



    //CONSTRUCTORS GO HERE
    // int asset_id, String asset_ELR, String asset_routeName, String int asset_spans, String asset_type, String asset_status, String asset_OS, String asset_county, boolean isActive
    public BridgeModel(int asset_id, String asset_ELR, String asset_routeName, int asset_spans, String asset_type, String asset_status, String asset_OS, String asset_county, boolean isActive) {
        this.asset_id = asset_id;
        this.asset_ELR = asset_ELR;
        this.asset_routeName = asset_routeName;
        this.asset_spans = asset_spans;
        this.asset_type = asset_type;
        this.asset_status = asset_status;
        this.asset_OS = asset_OS;
        this.asset_county = asset_county;
        this.isActive = isActive;
    }
    //NON PARAMETERISED CONSTRUCTOR, NOT SURE IF WE WILL USE IT OR NOT
    public BridgeModel(int id) {

    }

    // toString is necessary for printing the contents of a class object. its a very common method.
    @Override
    public String toString() {
        return "BridgeModel{" +
                "ASSET=" + asset_id +
                ", ELR=" + asset_ELR +
                ", ROUTE=" + asset_routeName +
                ", SPANS=" + asset_spans +
                ", TYPE=" + asset_type +
                ", STATUS=" + asset_status +
                ", OS=" + asset_OS +
                ", COUNTY=" + asset_county +
                ", isActive=" + isActive +
                '}';
    }



    //GETTERS AND SETTERS BELOW - four of each
    public int getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(int asset_id) {
        this.asset_id = asset_id;
    }

    public String getAsset_ELR() {
        return asset_ELR;
    }

    public void setAsset_ELR(String asset_ELR) {
        this.asset_ELR = asset_ELR;
    }

    public String getAsset_routeName() {
        return asset_routeName;
    }

    public void setAsset_routeName(String asset_routeName) {
        this.asset_routeName = asset_routeName;
    }

    public int getAsset_spans() {
        return asset_spans;
    }

    public void setAsset_spans(int asset_spans) {
        this.asset_spans = asset_spans;
    }

    public String getAsset_type() {
        return asset_type;
    }

    public void setAsset_type(String asset_type) {
        this.asset_type = asset_type;
    }

    public String getAsset_status() {
        return asset_status;
    }

    public void setAsset_status(String asset_status) {
        this.asset_status = asset_status;
    }

    public String getAsset_OS() {
        return asset_OS;
    }

    public void setAsset_OS(String asset_OS) {
        this.asset_OS = asset_OS;
    }

    public String getAsset_county() {
        return asset_county;
    }

    public void setAsset_county(String asset_county) {
        this.asset_county = asset_county;
    }
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
