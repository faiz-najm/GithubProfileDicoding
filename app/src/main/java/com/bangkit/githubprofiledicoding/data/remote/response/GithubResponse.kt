package com.bangkit.githubprofiledicoding.data.remote.response

import com.squareup.moshi.Json

data class GithubResponse(

	@Json(name="total_count")
	val totalCount: Int,

	@Json(name="incomplete_results")
	val incompleteResults: Boolean,

	@Json(name="items")
	val items: List<User>
)

data class User(

	@Json(name="gists_url")
	val gistsUrl: String,

	@Json(name="repos_url")
	val reposUrl: String,

	@Json(name="following_url")
	val followingUrl: String,

	@Json(name="starred_url")
	val starredUrl: String,

	@Json(name="login")
	val login: String,

	@Json(name="followers_url")
	val followersUrl: String,

	@Json(name="type")
	val type: String,

	@Json(name="url")
	val url: String,

	@Json(name="subscriptions_url")
	val subscriptionsUrl: String,

	@Json(name="score")
	val score: Any,

	@Json(name="received_events_url")
	val receivedEventsUrl: String,

	@Json(name="avatar_url")
	val avatarUrl: String,

	@Json(name="events_url")
	val eventsUrl: String,

	@Json(name="html_url")
	val htmlUrl: String,

	@Json(name="site_admin")
	val siteAdmin: Boolean,

	@Json(name="id")
	val id: Int,

	@Json(name="gravatar_id")
	val gravatarId: String,

	@Json(name="node_id")
	val nodeId: String,

	@Json(name="organizations_url")
	val organizationsUrl: String
)
