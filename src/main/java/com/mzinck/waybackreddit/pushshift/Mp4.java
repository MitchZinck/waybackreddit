
package com.mzinck.waybackreddit.pushshift;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mp4 {

    @SerializedName("resolutions")
    @Expose
    private List<Resolution__> resolutions = null;
    @SerializedName("source")
    @Expose
    private Source__ source;

    public List<Resolution__> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<Resolution__> resolutions) {
        this.resolutions = resolutions;
    }

    public Source__ getSource() {
        return source;
    }

    public void setSource(Source__ source) {
        this.source = source;
    }

}
