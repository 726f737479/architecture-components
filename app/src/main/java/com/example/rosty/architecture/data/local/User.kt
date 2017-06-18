package com.example.rosty.architecture.data.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

import com.example.rosty.architecture.data.remote.model.UserResponse

@Entity(tableName = "user")
class User {

    @PrimaryKey @ColumnInfo(name = "id") var id: Int? = null

    @ColumnInfo(name = "login")         var login: String? = null
    @ColumnInfo(name = "avatar_url")    var avatarUrl: String? = null
    @ColumnInfo(name = "url")           var url: String? = null
    @ColumnInfo(name = "html_url")      var htmlUrl: String? = null
    @ColumnInfo(name = "name")          var name: String? = null
    @ColumnInfo(name = "company")       var company: String? = null
    @ColumnInfo(name = "blog")          var blog: String? = null
    @ColumnInfo(name = "location")      var location: String? = null
    @ColumnInfo(name = "email")         var email: String? = null
    @ColumnInfo(name = "bio")           var bio: String? = null
    @ColumnInfo(name = "public_repos")  var publicRepos: Int? = null
    @ColumnInfo(name = "public_gists")  var publicGists: Int? = null
    @ColumnInfo(name = "followers")     var followers: Int? = null
    @ColumnInfo(name = "following")     var following: Int? = null
    @ColumnInfo(name = "createdAt")     var createdAt: String? = null

    var isSaved: Boolean = false

    constructor() {}

    constructor(login: String) {
        this.login = login
    }

    constructor(userResponse: UserResponse) {

        login       = userResponse.login
        id          = userResponse.id
        avatarUrl   = userResponse.avatarUrl
        url         = userResponse.url
        htmlUrl     = userResponse.htmlUrl
        name        = userResponse.name
        company     = userResponse.company
        blog        = userResponse.blog
        location    = userResponse.location
        email       = userResponse.email
        bio         = userResponse.bio
        publicRepos = userResponse.publicRepos
        publicGists = userResponse.publicGists
        followers   = userResponse.followers
        following   = userResponse.following
        createdAt   = userResponse.createdAt
    }

    override fun toString(): String {

        return "User(id=$id, login=$login, avatarUrl=$avatarUrl, " +
                "url=$url, htmlUrl=$htmlUrl, name=$name, " +
                "company=$company, blog=$blog, location=$location, " +
                "email=$email, bio=$bio, publicRepos=$publicRepos, " +
                "publicGists=$publicGists, followers=$followers, following=$following, " +
                "createdAt=$createdAt, isSaved=$isSaved)"
    }
}
