
package com.study.ilmaz.patternmvp.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable
{

    @SerializedName("results")
    @Expose
    private List<User> results = null;
    @SerializedName("info")
    @Expose
    private Info info;
    private final static long serialVersionUID = -6800743023299003122L;

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

}
