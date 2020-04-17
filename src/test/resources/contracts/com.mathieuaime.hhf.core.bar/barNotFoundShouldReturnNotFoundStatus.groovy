import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "bar not found"
    request {
        method GET()
        url("/0")
    }
    response {
        status 404
    }
}