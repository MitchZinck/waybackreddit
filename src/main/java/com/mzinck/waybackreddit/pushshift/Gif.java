
package com.mzinck.waybackreddit.pushshift;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gif {

    @SerializedName("resolutions")
    @Expose
    private List<Resolution_> resolutions = null;
    @SerializedName("source")
    @Expose
    private Source_ source;

    public List<Resolution_> getResolutions() {
        return resolutions;
    }

    public void setResolutions(List<Resolution_> resolutions) {
        this.resolutions = resolutions;
    }

    public Source_ getSource() {
        return source;
    }

    public void setSource(Source_ source) {
        this.source = source;
    }

}
