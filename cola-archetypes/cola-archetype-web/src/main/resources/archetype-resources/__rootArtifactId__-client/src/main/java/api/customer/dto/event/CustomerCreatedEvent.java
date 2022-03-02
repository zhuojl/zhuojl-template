#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.customer.dto.event;

import static ${package}.api.customer.dto.event.DomainEventConstant.CUSTOMER_CREATED_TOPIC;

/**
 * CustomerCreatedEvent
 *
 * @author Frank Zhang
 * @date 2019-01-04 10:32 AM
 */
public class CustomerCreatedEvent{

    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

}
