package com.khyati.practical.network

import android.accounts.NetworkErrorException
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/** Common class for API calling */

open class BaseRepository {

    /**
     * This is the Base suspended method which is used for making the
     * call of an Api and Manage the Response with response code to
     * display specific response message or code.
     *
     * @param call ApiInterface method defination to make a call and get
     *     response from generic Area.
     */
    suspend fun <T : Any> makeAPICall(call: suspend () -> Response<T>): ResponseHandler<T?> {
        try {
            val response = call.invoke()
            when {
                response.code() in (200..300) -> {
                    return ResponseHandler.OnSuccessResponse(response.body())
                }
                else -> {
                    return ResponseHandler.OnFailed(
                        response.code(),
                        "",
                        response.message().toString()
                    )
                }
            }
        } catch (e: Exception) {
            return if (
                e is UnknownHostException ||
                e is ConnectionShutdownException
            ) {
                ResponseHandler.OnFailed(1003, "", "")
            } else if (e is SocketTimeoutException || e is IOException ||
                e is NetworkErrorException
            ) {
                ResponseHandler.OnFailed(500, "", "")
            } else {
                ResponseHandler.OnFailed(500, "", "")
            }
        }
    }

}
