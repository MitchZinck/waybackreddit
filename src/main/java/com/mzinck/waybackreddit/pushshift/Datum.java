
package com.mzinck.waybackreddit.pushshift;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("author_flair_css_class")
    @Expose
    private Object authorFlairCssClass;
    @SerializedName("author_flair_text")
    @Expose
    private Object authorFlairText;
    @SerializedName("brand_safe")
    @Expose
    private Boolean brandSafe;
    @SerializedName("can_mod_post")
    @Expose
    private Boolean canModPost;
    @SerializedName("contest_mode")
    @Expose
    private Boolean contestMode;
    @SerializedName("created_utc")
    @Expose
    private Integer createdUtc;
    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("full_link")
    @Expose
    private String fullLink;
    @SerializedName("gilded")
    @Expose
    private Integer gilded;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("is_crosspostable")
    @Expose
    private Boolean isCrosspostable;
    @SerializedName("is_self")
    @Expose
    private Boolean isSelf;
    @SerializedName("is_video")
    @Expose
    private Boolean isVideo;
    @SerializedName("locked")
    @Expose
    private Boolean locked;
    @SerializedName("num_comments")
    @Expose
    private Integer numComments;
    @SerializedName("num_crossposts")
    @Expose
    private Integer numCrossposts;
    @SerializedName("over_18")
    @Expose
    private Boolean over18;
    @SerializedName("parent_whitelist_status")
    @Expose
    private String parentWhitelistStatus;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("pinned")
    @Expose
    private Boolean pinned;
    @SerializedName("retrieved_on")
    @Expose
    private Integer retrievedOn;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("selftext")
    @Expose
    private String selftext;
    @SerializedName("spoiler")
    @Expose
    private Boolean spoiler;
    @SerializedName("stickied")
    @Expose
    private Boolean stickied;
    @SerializedName("subreddit")
    @Expose
    private String subreddit;
    @SerializedName("subreddit_id")
    @Expose
    private String subredditId;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("thumbnail_height")
    @Expose
    private Integer thumbnailHeight;
    @SerializedName("thumbnail_width")
    @Expose
    private Integer thumbnailWidth;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("whitelist_status")
    @Expose
    private String whitelistStatus;
    @SerializedName("post_hint")
    @Expose
    private String postHint;
    @SerializedName("preview")
    @Expose
    private Preview preview;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Object getAuthorFlairCssClass() {
        return authorFlairCssClass;
    }

    public void setAuthorFlairCssClass(Object authorFlairCssClass) {
        this.authorFlairCssClass = authorFlairCssClass;
    }

    public Object getAuthorFlairText() {
        return authorFlairText;
    }

    public void setAuthorFlairText(Object authorFlairText) {
        this.authorFlairText = authorFlairText;
    }

    public Boolean getBrandSafe() {
        return brandSafe;
    }

    public void setBrandSafe(Boolean brandSafe) {
        this.brandSafe = brandSafe;
    }

    public Boolean getCanModPost() {
        return canModPost;
    }

    public void setCanModPost(Boolean canModPost) {
        this.canModPost = canModPost;
    }

    public Boolean getContestMode() {
        return contestMode;
    }

    public void setContestMode(Boolean contestMode) {
        this.contestMode = contestMode;
    }

    public Integer getCreatedUtc() {
        return createdUtc;
    }

    public void setCreatedUtc(Integer createdUtc) {
        this.createdUtc = createdUtc;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getFullLink() {
        return fullLink;
    }

    public void setFullLink(String fullLink) {
        this.fullLink = fullLink;
    }

    public Integer getGilded() {
        return gilded;
    }

    public void setGilded(Integer gilded) {
        this.gilded = gilded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsCrosspostable() {
        return isCrosspostable;
    }

    public void setIsCrosspostable(Boolean isCrosspostable) {
        this.isCrosspostable = isCrosspostable;
    }

    public Boolean getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(Boolean isSelf) {
        this.isSelf = isSelf;
    }

    public Boolean getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(Boolean isVideo) {
        this.isVideo = isVideo;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Integer getNumComments() {
        return numComments;
    }

    public void setNumComments(Integer numComments) {
        this.numComments = numComments;
    }

    public Integer getNumCrossposts() {
        return numCrossposts;
    }

    public void setNumCrossposts(Integer numCrossposts) {
        this.numCrossposts = numCrossposts;
    }

    public Boolean getOver18() {
        return over18;
    }

    public void setOver18(Boolean over18) {
        this.over18 = over18;
    }

    public String getParentWhitelistStatus() {
        return parentWhitelistStatus;
    }

    public void setParentWhitelistStatus(String parentWhitelistStatus) {
        this.parentWhitelistStatus = parentWhitelistStatus;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Boolean getPinned() {
        return pinned;
    }

    public void setPinned(Boolean pinned) {
        this.pinned = pinned;
    }

    public Integer getRetrievedOn() {
        return retrievedOn;
    }

    public void setRetrievedOn(Integer retrievedOn) {
        this.retrievedOn = retrievedOn;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSelftext() {
        return selftext;
    }

    public void setSelftext(String selftext) {
        this.selftext = selftext;
    }

    public Boolean getSpoiler() {
        return spoiler;
    }

    public void setSpoiler(Boolean spoiler) {
        this.spoiler = spoiler;
    }

    public Boolean getStickied() {
        return stickied;
    }

    public void setStickied(Boolean stickied) {
        this.stickied = stickied;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getSubredditId() {
        return subredditId;
    }

    public void setSubredditId(String subredditId) {
        this.subredditId = subredditId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getThumbnailHeight() {
        return thumbnailHeight;
    }

    public void setThumbnailHeight(Integer thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    public Integer getThumbnailWidth() {
        return thumbnailWidth;
    }

    public void setThumbnailWidth(Integer thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWhitelistStatus() {
        return whitelistStatus;
    }

    public void setWhitelistStatus(String whitelistStatus) {
        this.whitelistStatus = whitelistStatus;
    }

    public String getPostHint() {
        return postHint;
    }

    public void setPostHint(String postHint) {
        this.postHint = postHint;
    }

    public Preview getPreview() {
        return preview;
    }

    public void setPreview(Preview preview) {
        this.preview = preview;
    }

}
