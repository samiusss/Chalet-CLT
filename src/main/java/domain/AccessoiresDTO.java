package domain;

import java.util.UUID;

public class AccessoiresDTO {
    private final UUID AID;

    public AccessoiresDTO(Accessoires bi){
        AID = bi.getIdAccessoire();
    }
}
