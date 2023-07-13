package com.gopay.screens.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gopay.MainCoroutineRule
import com.gopay.data.ApiResult
import com.gopay.data.remote.MiscApiService
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class VehicleDataSourceTest {

    private lateinit var vehDataSource: VehicleDataSource

    @get: Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    var mainCoroutineRule = MainCoroutineRule()

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
        vehDataSource.showDataInAllState = false
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `network call request and success`() {
        runTest {
            enqueueResponse("config-response.json")
            val resultResponse = vehDataSource.getVehicleList()

            val request = mockWebServer.takeRequest()
            MatcherAssert.assertThat(request.path, CoreMatchers.`is`("/3/configuration"))

            assertNotNull(resultResponse)
            resultResponse.collect{
                MatcherAssert.assertThat(it.status,CoreMatchers.`is`(ApiResult.Status.ERROR))
            }
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