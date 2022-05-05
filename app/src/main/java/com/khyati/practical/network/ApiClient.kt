package com.khyati.practical.network

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient {

    companion object {

        private var okHttpClient: OkHttpClient? = null
        private var retrofits: Retrofit? = null
        private var myApiInterface: ApiInterface? = null

        /**
         * This is the generic method which will create retrofit object
         * as singleton.
         */
        fun initRetrofit() {
            if (retrofits == null) {
                retrofits = getRetrofit()
                myApiInterface = retrofits?.create(ApiInterface::class.java)!!
            }
        }

        /** Return API interface */
        fun getApiInterface(): ApiInterface {
            if (myApiInterface != null) {
                return myApiInterface!!
            }
            myApiInterface = retrofits?.create(ApiInterface::class.java)!!
            return myApiInterface as ApiInterface
        }

        /** Generate Retrofit Client */
        private fun getRetrofit(): Retrofit {
            val builder = Retrofit.Builder()
            builder.baseUrl("https://www.themealdb.com/api/json/v1/1/")
            builder.addConverterFactory(JacksonConverterFactory.create())
            builder.client(getOkHttpClient())
            return builder.build()
        }

        /** generate OK-http client */
        private fun getOkHttpClient(): OkHttpClient {
            if (okHttpClient == null) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                val builder = OkHttpClient.Builder()
                builder.addInterceptor(logging)
                builder.hostnameVerifier { _, _ ->
                    return@hostnameVerifier true
                }
                builder.readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(HttpHandleIntercept())
                    .build()
                okHttpClient = builder.build()

            }
            return okHttpClient!!
        }

        /**
         * generate custom response for exception
         *
         * @param code Int
         * @param message String
         * @param request Request
         * @return Response?
         */
        fun generateCustomResponse(code: Int, message: String, request: Request): Response? {
            return try {
                val body = getJSONObjectForException(message, code).toString()
                    .toResponseBody("application/json".toMediaTypeOrNull())
                Response.Builder()
                    .code(code)
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .body(body)
                    .message(message)
                    .build()
            } catch (ex: Exception) {
                null
            }
        }

        /** generate JSON object for error case */
        private fun getJSONObjectForException(message: String, code: Int): JSONObject {

            try {
                val jsonMainObject = JSONObject()

                val `object` = JSONObject()
                `object`.put("status", false)
                `object`.put("message", message)
                `object`.put("message_code", code)
                `object`.put("statusCode", code)

                jsonMainObject.put("meta", `object`)

                val obj = JSONObject()
                obj.put("error", JSONArray().put(message))

                jsonMainObject.put("errors", obj)

                return jsonMainObject
            } catch (e: JSONException) {
                return JSONObject()
            }
        }
    }
}
