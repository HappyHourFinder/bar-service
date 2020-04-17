import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "delete bar"
    request {
        method DELETE()
        url("/1")
    }
    response {
        status 204
    }
}