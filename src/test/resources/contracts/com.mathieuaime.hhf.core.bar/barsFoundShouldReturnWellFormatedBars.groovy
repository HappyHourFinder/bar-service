import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "bar found"
    request {
        method GET()
        url("/")
    }
    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body("""[{"uuid":"1","name":"Bar","latitude":1.0,"longitude":2.0},{"uuid":"2","name":"Other bar","latitude":2.0,"longitude":3.0}]""")
    }
}