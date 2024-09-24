package model;

import jdk.jfr.Enabled;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public final class Product {
    @Id
    private UUID uuid;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String name;

    private BigDecimal price;

    private boolean sale;

}
