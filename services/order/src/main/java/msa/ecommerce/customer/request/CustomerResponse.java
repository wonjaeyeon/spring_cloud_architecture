package msa.ecommerce.customer.request;


// 반드시 cusotmer.CustomerResponse랑 변수명까지 전부!! 동일해야 한다. -> 아니면 인식을 못해서 그냥 정상적으로 Receive 해도 null로 받아진다.
public record CustomerResponse (
        String id,
        String firstName,
        String lastName,
        String email
){
}
