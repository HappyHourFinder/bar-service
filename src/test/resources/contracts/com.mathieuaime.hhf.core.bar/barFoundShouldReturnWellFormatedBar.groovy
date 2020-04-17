import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "bar found"
    request {
        method GET()
        url("/1")
    }
    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body(
                uuid: '1',
                name: 'Bar',
                latitude: '1.0',
                longitude: '2.0'
        )
    }
}