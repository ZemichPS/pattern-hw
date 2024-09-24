import dao.impl.CustomerPersistenceRepository;
import dao.impl.OrderPersistenceRepository;
import dao.impl.ProductPersistenceRepository;
import model.*;
import service.api.CustomerNotificationService;
import service.api.EmailService;
import service.api.crud.CustomerService;
import service.api.crud.OrderDetailsService;
import service.api.crud.ProductService;
import service.impl.*;
import service.impl.notificationHanddlers.EmailNotificationHandler;
import service.impl.notificationHanddlers.NotificationHandler;
import service.impl.notificationHanddlers.SmsNotificationHandlerImpl;
import service.impl.validation.Validator;
import service.impl.validation.order.OrderActiveValidator;
import service.impl.validation.order.OrderQuantityValidator;
import service.impl.validation.order.OrderUserUuidNotNullValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {


        OrderDetailsService orderDetailsService = new OrderDetailsServiceImpl(new OrderPersistenceRepository());
        OrderDetailsService proxyOrderDetailsService = new ProxyOrderDetailsService(orderDetailsService);

        CustomerService customerService = new CustomerServiceImpl(new CustomerPersistenceRepository());
        EventManager eventManager = new EventManager();
        ProductService productService = new ProductServiceImpl(new ProductPersistenceRepository(), eventManager);
        EmailService<Notification> emailService = new EmailServiceImpl();

        List<NotificationHandler<Customer, Notification>> notificationHandlers = List.of(
                new EmailNotificationHandler(emailService),
                new SmsNotificationHandlerImpl()
        );
        CustomerNotificationService customerNotificationService = new CustomerNotificationServiceImpl(notificationHandlers, customerService);

        OrderTracker orderTracker = new OrderTracker("zemich", emailService);
        eventManager.subscribe("OrderCreated", orderTracker);

        Validator<OrderDetails> validator = new OrderActiveValidator();
        validator.linkWith(new OrderQuantityValidator())
                .linkWith(new OrderUserUuidNotNullValidator());

        OrderServiceFacade orderServiceFacade = new OrderServiceFacade(
                proxyOrderDetailsService,
                eventManager,
                customerNotificationService,
                validator
        );

        Customer customer = new Customer(
                UUID.randomUUID(),
                List.of(),
                "Bob",
                "Mitchell",
                "boby@mail.ru",
                "+375297441235"
        );

        Product product = Product.builder()
                .uuid(UUID.randomUUID())
                .price(new BigDecimal(15))
                .category(Category.BOOKS)
                .name("JAVA Persistence API. Best practices")
                .sale(true)
                .build();

        customerService.save(customer);
        productService.save(product);

        OrderItem orderItem = new OrderItem();
        orderItem.setId(UUID.randomUUID());
        orderItem.setQuantity(2);
        orderItem.addProduct(product);

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setUuid(UUID.randomUUID());
        orderDetails.addCustomer(customer);
        orderDetails.setStatus(Status.NEW);
        orderDetails.addOrderItem(orderItem);

        OrderDetails savedOrderDetails = orderServiceFacade.createAndNotify(orderDetails);

        final List<OrderDetails> all = orderServiceFacade.getAll();
        orderServiceFacade.delete(savedOrderDetails);
    }
}
