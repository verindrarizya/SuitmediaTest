package com.verindrarizya.suitmediatest.data.remote.response

import com.google.gson.annotations.SerializedName

data class Support(

	@field:SerializedName("text")
	val text: String,

	@field:SerializedName("url")
	val url: String
)