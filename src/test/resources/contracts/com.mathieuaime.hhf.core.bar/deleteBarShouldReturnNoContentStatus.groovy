import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "delete bar"
    request {
        method DELETE()
        url("/bars/1")
    }
    response {
        status 204
    }
}