package com.mathieuaime.hhf.core.bar

import com.mathieuaime.hhf.core.bar.controller.BarController
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
class BarRestBase {
    @Autowired
    private lateinit var barController: BarController

    @BeforeEach
    fun setup() {
        val builder = MockMvcBuilders.standaloneSetup(barController)
        RestAssuredMockMvc.standaloneSetup(builder)
    }
}