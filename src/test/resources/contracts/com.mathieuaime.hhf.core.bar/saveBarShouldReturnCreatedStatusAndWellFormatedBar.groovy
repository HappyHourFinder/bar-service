import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "delete bar"
    request {
        method POST()
        url("/")
        body(
                name: 'Bar',
                latitude: '1.0',
                longitude: '2.0'
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 201
        headers {
            contentType(applicationJson())
        }
        body(
                uuid: anyNonBlankString(),
                name: 'Bar',
                latitude: '1.0',
                longitude: '2.0'
        )
    }
}