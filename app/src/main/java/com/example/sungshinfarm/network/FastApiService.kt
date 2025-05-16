package com.example.sungshinfarm.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.*
import com.example.sungshinfarm.model.PredictionResult
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient

suspend fun uploadImageToFastAPI(imageBytes: ByteArray): PredictionResult {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    val response = client.submitFormWithBinaryData(
        url = "https://sungshinfarm.onrender.com/predict", // <- 여기에 로컬 서버 주소 입력
        formData = formData {
            append("file", imageBytes, Headers.build {
                append(HttpHeaders.ContentDisposition, "filename=image.jpg")
            })
        }
    )

    // 응답에서 localResult 파싱
    val json = response.body<JsonObject>()
    val result = json["localResult"]!!.jsonObject

    val name = result["name"]!!.jsonPrimitive.content
    val grade = result["qualityGrade"]!!.jsonPrimitive.content
    val price = result["recommendedPrice"]!!.jsonPrimitive.content

    client.close()

    return PredictionResult(name, grade, price.toInt())
}
