package com.touch.player.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    @SerializedName("tokenType") var tokenType: String,
    @SerializedName("accessToken") var accessToken: String,
    @SerializedName("userDetails") var userDetails: UserDetails,
) : Serializable {
    data class UserDetails(
        @SerializedName("id") var id: Int,
        @SerializedName("externalId") var externalId: String,
        @SerializedName("firstName") var firstName: String?,
        @SerializedName("lastName") var lastName: String?,
        @SerializedName("emailAddress") var emailAddress: String,
        @SerializedName("changePasswordCode") var changePasswordCode: String,
        @SerializedName("organizations") var organizations: List<Organization>,
        @SerializedName("roles") var roles: List<Role>,
        @SerializedName("phoneNumber") var phoneNumber: String?,
        @SerializedName("sex") var sex: String,
        @SerializedName("dateOfBirth") var dateOfBirth: String?,
        @SerializedName("marketplaceId") var marketplaceId: String?,
        @SerializedName("imageUrl") var imageUrl: String?,
        @SerializedName("language") var language: String,
        @SerializedName("currency") var currency: String,
        @SerializedName("addresses") var addresses: String?,
        @SerializedName("createdBy") var createdBy: String?,
        @SerializedName("created") var created: String,
    ) : Serializable

    data class Organization(
        @SerializedName("id") var id: Int,
        @SerializedName("externalId") var externalId: String,
        @SerializedName("name") var name: String,
        @SerializedName("children") var children: List<Any>,
    ) : Serializable

    data class Role(
        @SerializedName("id") var id: Int,
        @SerializedName("name") var name: String,
        @SerializedName("label") var label: String,
        @SerializedName("priority") var priority: Int,
        @SerializedName("permissions") var permissions: List<Permission>,
    ) : Serializable

    data class Permission(
        @SerializedName("id") var id: Int,
        @SerializedName("name") var name: String,
        @SerializedName("code") var code: String,
        @SerializedName("operationType") var operationType: Int,
    ) : Serializable
}