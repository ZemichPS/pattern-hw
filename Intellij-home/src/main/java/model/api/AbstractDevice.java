package model.api;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class AbstractDevice{
    private UUID id;
    private String name;
    private String address;
    private Connectivity connectivity;

    public AbstractDevice(UUID id,
                          String name,
                          String address,
                          Connectivity connectivity
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.connectivity = connectivity;
    }

}
