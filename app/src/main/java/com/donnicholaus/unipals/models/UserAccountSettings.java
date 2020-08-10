package com.donnicholaus.unipals.models;

public class UserAccountSettings {

    private String description;
    private String fullname;
    private String profile_photo;
    private long followers;
    private long following;
    private long posts;
    private String username;

    public UserAccountSettings(String description, String fullname, String profile_photo,
                               long followers, long following, long posts, String username) {
        this.description = description;
        this.fullname = fullname;
        this.profile_photo = profile_photo;
        this.followers = followers;
        this.following = following;
        this.posts = posts;
        this.username = username;
    }

    public UserAccountSettings() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public long getFollowing() {
        return following;
    }

    public void setFollowing(long following) {
        this.following = following;
    }

    public long getPosts() {
        return posts;
    }

    public void setPosts(long posts) {
        this.posts = posts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserAccountSettings{" +
                "description='" + description + '\'' +
                ", fullname='" + fullname + '\'' +
                ", profile_photo='" + profile_photo + '\'' +
                ", followers=" + followers +
                ", following=" + following +
                ", posts=" + posts +
                ", username='" + username + '\'' +
                '}';
    }
}
