package com.togrul.ms.gatewayserver.filters;


/*@Order(1)
@Component*/
public class RequestTraceFilter {


 /*   @Autowired
    FilterUtility filterUtility;

    private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
         if(isCorrelationIdPresent(requestHeaders)) {
             logger.debug("togrul-correlation-id found in tracing filter : {}",filterUtility.getCorrelationId(requestHeaders));
         } else {
             String correlationId = generateCorrelationId();
             exchange=filterUtility.setCorrelationId(exchange,correlationId);
             logger.debug("togrul-correlation-id generated in tracing filter : {}.",correlationId);
         }
        return chain.filter(exchange);
    }


    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        if (filterUtility.getCorrelationId(requestHeaders) != null) {
            return true;
        } else {
            return false;
        }
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }*/

}
