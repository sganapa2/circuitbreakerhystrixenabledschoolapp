This is a hystrix eneabled springboot project which monitors continuously on the student application in "circuitbreakermicroservicesapp".
As soon as the service "circuitbreakermicroservicesapp" goes down,this breaks the circuit and returns predefined response provided/handled in fallback method in the "circuitbreakermicroservicesapp" service.
