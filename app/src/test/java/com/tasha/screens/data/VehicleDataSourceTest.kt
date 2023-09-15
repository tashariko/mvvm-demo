package com.tasha.screens.data

import com.tasha.data.ApiResult
import com.tasha.data.remote.MiscApiService
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class VehicleDataSourceTest {

    private lateinit var vehDataSource: VehicleDataSource


    private lateinit var service: MiscApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MiscApiService::class.java)
        vehDataSource = VehicleDataSource(service )
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `network call request and success`() {
        runBlocking {
            enqueueResponse("config-response.json")
            val resultResponse = vehDataSource.getVehicleList()

            val request = mockWebServer.takeRequest()
            Assert.assertThat(request.path, CoreMatchers.`is`("/api/starships"))

            assertNotNull(resultResponse)
            MatcherAssert.assertThat(resultResponse.status,CoreMatchers.`is`(ApiResult.Status.SUCCESS))
        }
    }
//
//    @Test
//    fun `network call failure`() {
//        runBlocking {
//            enqueueResponse("config-response.json", responseCode = 400)
//            val resultResponse = landingRemoteDataSource.getData()
//
//            assertNotNull(resultResponse)
//            MatcherAssert.assertThat(resultResponse.status,CoreMatchers.`is`(ApiResult.Status.ERROR))
//        }
//    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap(), responseCode: Int = 200) {
        val inputStream = javaClass.classLoader
            .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse.setBody(
                source.readString(Charsets.UTF_8)
            ).setResponseCode(responseCode)
        )
    }
}