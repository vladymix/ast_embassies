package com.altamirano.fabricio.embassies.commons;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EmbajadasConsulados {

    @SerializedName("@context")
    com.altamirano.fabricio.embassies.commons.Context context;

    @SerializedName("@graph")
    public ArrayList<Result> graph = new ArrayList<Result>();

    // Getter Methods
    public Context getContext() {
        return context;
    }

    // Setter Methods
    public void setcontext(Context contextObject) {
        this.context = contextObject;
    }
}
