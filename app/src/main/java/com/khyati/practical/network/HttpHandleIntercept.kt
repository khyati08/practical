package com.khyati.practical.network

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull


/** Http handle intercept */
class HttpHandleIntercept : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().headers(getJsonHeader()).build()
        val response: Response?

        response = chain.proceed(request)
        if (response.code == 200 && response.body?.contentLength() == 0L) {
            val body = ResponseBody.create(
                "application/json".toMediaTypeOrNull(),
                true.toString()
            )
            return Response.Builder()
                .code(200)
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .body(body)
                .message("")
                .build()
        } else if (response.code == 401) {
            return ApiClient.generateCustomResponse(
                401, "",
                chain.request()
            )!!
        } else if (response.code == 500) {
            return ApiClient.generateCustomResponse(
                500, "",
                chain.request()
            )!!
        }

        return response
    }

    private fun getJsonHeader(): Headers {
        val builder = Headers.Builder()
        builder.add("Content-Type", "application/json")
        builder.add("Accept", "application/json")
        return builder.build()
    }
}
